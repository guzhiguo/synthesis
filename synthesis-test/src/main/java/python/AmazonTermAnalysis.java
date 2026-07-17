package python;

import com.example.synthesis.pojo.AmazonAdReport;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Date;

/**
 * 多文件合并入库工具：
 * 1. 扫描指定目录下所有同格式 Excel 文件
 * 2. 按「日期 + 客户搜索词 + 广告活动名称 + 广告组名称」去重
 * 3. 重复行以「展示量」较大的为准（应对不同批次导出的数据延迟）
 * 4. 先清空数据表，再批量插入合并后的数据
 *
 * @author bill
 * @create 2026/07/18
 */
public class AmazonTermAnalysis {

    // ==================== Excel 列名常量 ====================
    private static final String COL_DATE = "日期";
    private static final String COL_PORTFOLIO = "广告组合名称";
    private static final String COL_CURRENCY = "货币";
    private static final String COL_CAMPAIGN = "广告活动名称";
    private static final String COL_AD_GROUP = "广告组名称";
    private static final String COL_RETAILER = "零售商";
    private static final String COL_COUNTRY = "国家/地区";
    private static final String COL_PLACEMENT = "投放";
    private static final String COL_MATCH_TYPE = "匹配类型";
    private static final String COL_SEARCH_TERM = "客户搜索词";
    private static final String COL_IMPRESSIONS = "展示量";
    private static final String COL_CLICKS = "点击量";
    private static final String COL_CTR = "点击率";
    private static final String COL_CPC = "点击成本";
    private static final String COL_SPEND = "花费";
    private static final String COL_SALES_7D = "总销售额";
    private static final String COL_ACOS = "ACOS";
    private static final String COL_ROAS = "ROAS";
    private static final String COL_ORDERS_7D = "总订单数";
    private static final String COL_UNITS_7D = "总销售量";
    private static final String COL_CONV_RATE_7D = "转化率";
    private static final String COL_AD_SKU_UNITS_7D = "广告SKU销售量";
    private static final String COL_OTHER_SKU_UNITS_7D = "其他SKU销售量";
    private static final String COL_AD_SKU_SALES_7D = "广告SKU销售额";
    private static final String COL_OTHER_SKU_SALES_7D = "其他SKU销售额";

    // ==================== 数据库配置 ====================
    private static final String DB_URL = "jdbc:mysql://localhost:3306/synthesis?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "rootroot";

    /**
     * 批量插入每批次大小
     */
    private static final int BATCH_SIZE = 500;

    public static void main(String[] args) throws Exception {
        // ========== 配置区 ==========
        // Excel 文件所在目录（会扫描目录下所有 .xlsx 文件）
        String scanDir = "/Users/bill/Downloads/广告数据存档/截止于20260707/termSearch";
        // 可选：指定具体文件列表（如果不扫描目录）
        // String[] filePaths = {"/Users/bill/Downloads/文件1.xlsx", "/Users/bill/Downloads/文件2.xlsx"};
        // ===========================

        // 1. 收集并合并 Excel 数据
        Map<String, AmazonAdReport> mergedMap = new LinkedHashMap<>();
        int totalRows = 0;
        int fileCount = 0;

        File dir = new File(scanDir);
        File[] xlsxFiles = dir.listFiles((d, name) -> name.endsWith(".xlsx") || name.endsWith(".XLSX"));

        if (xlsxFiles == null || xlsxFiles.length == 0) {
            System.err.println("目录下未找到 .xlsx 文件: " + scanDir);
            return;
        }

        // 按文件名排序，保证处理顺序一致
        Arrays.sort(xlsxFiles, Comparator.comparing(File::getName));

        for (File file : xlsxFiles) {
            System.out.println("\n>>> 读取文件: " + file.getName());
            int cnt = readExcel(file, mergedMap);
            totalRows += cnt;
            fileCount++;
            System.out.println("  本文件行数: " + cnt + ", 累计唯一行: " + mergedMap.size());
        }

        int dedupCount = totalRows - mergedMap.size();
        System.out.println("\n========================================");
        System.out.println("扫描文件数: " + fileCount);
        System.out.println("总读取行数: " + totalRows);
        System.out.println("合并后唯一行数: " + mergedMap.size());
        System.out.println("去重合并(取展示量MAX): " + dedupCount + " 行");
        System.out.println("========================================");

        // 2. 写入数据库
        List<AmazonAdReport> reportList = new ArrayList<>(mergedMap.values());
        insertToDatabase(reportList);
    }

    /**
     * 读取一个 Excel 文件，按日期+搜索词+活动+广告组去重合并到 mergedMap
     * 重复时保留展示量更大的行
     */
    private static int readExcel(File file, Map<String, AmazonAdReport> mergedMap) throws Exception {
        int rowCount = 0;
        try (FileInputStream fis = new FileInputStream(file);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) return 0;

            // 解析表头
            Map<String, Integer> colMap = new HashMap<>();
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    colMap.put(cell.getStringCellValue().trim(), i);
                }
            }

            // 查找列索引
            int[] idx = new int[25];
            idx[0] = findColumn(colMap, COL_DATE);
            idx[1] = findColumn(colMap, COL_PORTFOLIO);
            idx[2] = findColumn(colMap, COL_CURRENCY);
            idx[3] = findColumn(colMap, COL_CAMPAIGN);
            idx[4] = findColumn(colMap, COL_AD_GROUP);
            idx[5] = findColumn(colMap, COL_RETAILER);
            idx[6] = findColumn(colMap, COL_COUNTRY);
            idx[7] = findColumn(colMap, COL_PLACEMENT);
            idx[8] = findColumn(colMap, COL_MATCH_TYPE);
            idx[9] = findColumn(colMap, COL_SEARCH_TERM);
            idx[10] = findColumn(colMap, COL_IMPRESSIONS);
            idx[11] = findColumn(colMap, COL_CLICKS);
            idx[12] = findColumn(colMap, COL_CTR);
            idx[13] = findColumn(colMap, COL_CPC);
            idx[14] = findColumn(colMap, COL_SPEND);
            idx[15] = findColumn(colMap, COL_SALES_7D);
            idx[16] = findColumn(colMap, COL_ACOS);
            idx[17] = findColumn(colMap, COL_ROAS);
            idx[18] = findColumn(colMap, COL_ORDERS_7D);
            idx[19] = findColumn(colMap, COL_UNITS_7D);
            idx[20] = findColumn(colMap, COL_CONV_RATE_7D);
            idx[21] = findColumn(colMap, COL_AD_SKU_UNITS_7D);
            idx[22] = findColumn(colMap, COL_OTHER_SKU_UNITS_7D);
            idx[23] = findColumn(colMap, COL_AD_SKU_SALES_7D);
            idx[24] = findColumn(colMap, COL_OTHER_SKU_SALES_7D);

            // 遍历数据行
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                AmazonAdReport report = new AmazonAdReport();
                report.setReportDate(parseDate(getCellStr(row, idx[0])));
                report.setPortfolioName(getCellStr(row, idx[1]));
                report.setCurrency(getCellStr(row, idx[2]));
                report.setCampaignName(getCellStr(row, idx[3]));
                report.setAdGroupName(getCellStr(row, idx[4]));
                report.setRetailer(getCellStr(row, idx[5]));
                report.setCountry(getCellStr(row, idx[6]));
                report.setPlacement(getCellStr(row, idx[7]));
                report.setMatchType(getCellStr(row, idx[8]));
                report.setSearchTerm(getCellStr(row, idx[9]));
                report.setImpressions(getCellInt(row, idx[10]));
                report.setClicks(getCellInt(row, idx[11]));
                report.setCtr(getCellStr(row, idx[12]));
                report.setCpc(getCellDouble(row, idx[13]));
                report.setSpend(getCellDouble(row, idx[14]));
                report.setSales7d(getCellDouble(row, idx[15]));
                report.setAcos(getCellStr(row, idx[16]));
                report.setRoas(getCellStr(row, idx[17]));
                report.setOrders7d(getCellInt(row, idx[18]));
                report.setUnits7d(getCellInt(row, idx[19]));
                report.setConversionRate7d(getCellStr(row, idx[20]));
                report.setAdSkuUnits7d(getCellInt(row, idx[21]));
                report.setOtherSkuUnits7d(getCellInt(row, idx[22]));
                report.setAdSkuSales7d(getCellDouble(row, idx[23]));
                report.setOtherSkuSales7d(getCellDouble(row, idx[24]));

                // 构建去重键
                String dedupKey = buildDedupKey(report);

                // 同键去重：展示量大的保留
                AmazonAdReport existing = mergedMap.get(dedupKey);
                if (existing != null) {
                    if (report.getImpressions() != null &&
                            (existing.getImpressions() == null ||
                                    report.getImpressions() > existing.getImpressions())) {
                        mergedMap.put(dedupKey, report); // 替换为展示量更大的
                    }
                } else {
                    mergedMap.put(dedupKey, report);
                }
                rowCount++;
            }
        }
        return rowCount;
    }

    /**
     * 构建去重键：日期|搜索词|活动名|广告组名
     */
    private static String buildDedupKey(AmazonAdReport r) {
        return String.join("|",
                r.getReportDate() != null ? fmtDate(r.getReportDate()) : "",
                nvl(r.getSearchTerm()),
                nvl(r.getCampaignName()),
                nvl(r.getAdGroupName()));
    }

    /**
     * 批量插入数据库：先清空表，再批量 INSERT
     */
    private static void insertToDatabase(List<AmazonAdReport> list) {
        if (list.isEmpty()) {
            System.out.println("无数据需要入库");
            return;
        }

        String insertSql = "INSERT INTO amazon_ad_report (" +
                "report_date, portfolio_name, currency, campaign_name, ad_group_name, " +
                "retailer, country, placement, match_type, search_term, " +
                "impressions, clicks, ctr, cpc, spend, " +
                "sales_7d, acos, roas, orders_7d, units_7d, " +
                "conversion_rate_7d, ad_sku_units_7d, other_sku_units_7d, " +
                "ad_sku_sales_7d, other_sku_sales_7d" +
                ") VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            conn.setAutoCommit(false);

            // 1. 清空表
            try (Statement stmt = conn.createStatement()) {
                int deleted = stmt.executeUpdate("DELETE FROM amazon_ad_report");
                System.out.println("\n已清空数据表，删除 " + deleted + " 条旧数据");
            }

            // 2. 批量插入
            try (PreparedStatement ps = conn.prepareStatement(insertSql)) {
                int batchCount = 0;
                for (int i = 0; i < list.size(); i++) {
                    AmazonAdReport r = list.get(i);
                    setPsParams(ps, r);
                    ps.addBatch();

                    if (++batchCount >= BATCH_SIZE || i == list.size() - 1) {
                        ps.executeBatch();
                        conn.commit();
                        System.out.println("  已入库: " + (i + 1) + " / " + list.size());
                        batchCount = 0;
                    }
                }
            }

            System.out.println("\n入库完成！共插入 " + list.size() + " 条记录到 amazon_ad_report 表");
        } catch (SQLException e) {
            System.err.println("数据库操作失败: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 设置 PreparedStatement 参数
     */
    private static void setPsParams(PreparedStatement ps, AmazonAdReport r) throws SQLException {
        if (r.getReportDate() != null) {
            ps.setDate(1, new java.sql.Date(r.getReportDate().getTime()));
        } else {
            ps.setNull(1, Types.DATE);
        }
        ps.setString(2, r.getPortfolioName());
        ps.setString(3, r.getCurrency());
        ps.setString(4, r.getCampaignName());
        ps.setString(5, r.getAdGroupName());
        ps.setString(6, r.getRetailer());
        ps.setString(7, r.getCountry());
        ps.setString(8, r.getPlacement());
        ps.setString(9, r.getMatchType());
        ps.setString(10, r.getSearchTerm());
        setInt(ps, 11, r.getImpressions());
        setInt(ps, 12, r.getClicks());
        ps.setString(13, r.getCtr());
        setDouble(ps, 14, r.getCpc());
        setDouble(ps, 15, r.getSpend());
        setDouble(ps, 16, r.getSales7d());
        ps.setString(17, r.getAcos());
        ps.setString(18, r.getRoas());
        setInt(ps, 19, r.getOrders7d());
        setInt(ps, 20, r.getUnits7d());
        ps.setString(21, r.getConversionRate7d());
        setInt(ps, 22, r.getAdSkuUnits7d());
        setInt(ps, 23, r.getOtherSkuUnits7d());
        setDouble(ps, 24, r.getAdSkuSales7d());
        setDouble(ps, 25, r.getOtherSkuSales7d());
    }

    // ==================== 工具方法 ====================

    private static String getCellStr(Row row, int colIdx) {
        if (colIdx < 0) return null;
        Cell cell = row.getCell(colIdx);
        if (cell == null) return null;
        if (cell.getCellType() == CellType.NUMERIC) {
            // 方法一：使用 DateUtil.getJavaDate()（自动处理日期序列号）
            Date date = DateUtil.getJavaDate(cell.getNumericCellValue());
            if (date != null) {
                // 转换为 LocalDate（使用系统默认时区）
                LocalDate localDate = date.toInstant()
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();
                // 格式化为国内常用 "yyyy-MM-dd"
                return localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
            }
        }
        if (cell.getCellType() == CellType.STRING) return cell.getStringCellValue();
        cell.setCellType(CellType.STRING);
        String v = cell.getStringCellValue().trim();
        return v.isEmpty() ? null : v;
    }

    private static Integer getCellInt(Row row, int colIdx) {
        if (colIdx < 0) return 0;
        Cell cell = row.getCell(colIdx);
        if (cell == null) return 0;
        try {
            if (cell.getCellType() == CellType.NUMERIC) return (int) cell.getNumericCellValue();
            if (cell.getCellType() == CellType.STRING) {
                String v = cell.getStringCellValue().trim();
                return v.isEmpty() ? 0 : (int) Double.parseDouble(v);
            }
        } catch (Exception ignored) {
        }
        return 0;
    }

    private static Double getCellDouble(Row row, int colIdx) {
        if (colIdx < 0) return 0.0;
        Cell cell = row.getCell(colIdx);
        if (cell == null) return 0.0;
        try {
            if (cell.getCellType() == CellType.NUMERIC) return cell.getNumericCellValue();
            if (cell.getCellType() == CellType.STRING) {
                String v = cell.getStringCellValue().trim();
                return v.isEmpty() ? 0.0 : Double.parseDouble(v);
            }
        } catch (Exception ignored) {
        }
        return 0.0;
    }

    private static void setInt(PreparedStatement ps, int idx, Integer val) throws SQLException {
        if (val == null) ps.setNull(idx, Types.INTEGER);
        else ps.setInt(idx, val);
    }

    private static void setDouble(PreparedStatement ps, int idx, Double val) throws SQLException {
        if (val == null) ps.setNull(idx, Types.DOUBLE);
        else ps.setDouble(idx, val);
    }

    /**
     * 模糊匹配列名（兼容全角/半角括号、前后空格）
     */
    private static int findColumn(Map<String, Integer> colMap, String keyword) {
        for (Map.Entry<String, Integer> e : colMap.entrySet()) {
            if (e.getKey().equals(keyword)) return e.getValue();
        }
        String kw = keyword.replace('（', '(').replace('）', ')');
        for (Map.Entry<String, Integer> e : colMap.entrySet()) {
            if (e.getKey().replace('（', '(').replace('）', ')').contains(kw)) {
                return e.getValue();
            }
        }
        return -1;
    }

    private static String nvl(String s) {
        return s == null ? "" : s;
    }

    // ==================== 日期处理 ====================

    /**
     * 常见日期格式
     */
    private static final String[] DATE_FORMATS = {
            "yyyy/MM/dd", "yyyy-MM-dd", "yyyy.MM.dd", "MM/dd/yyyy", "M/d/yyyy"
    };

    /**
     * 解析 Excel 中的日期字符串，支持多种常见格式
     */
    private static Date parseDate(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) return null;
        for (String fmt : DATE_FORMATS) {
            try {
                return new SimpleDateFormat(fmt).parse(dateStr);
            } catch (Exception ignored) {
            }
        }
        System.err.println("日期解析失败: " + dateStr);
        return null;
    }

    private static String fmtDate(Date d) {
        return new SimpleDateFormat("yyyy-MM-dd").format(d);
    }
}
