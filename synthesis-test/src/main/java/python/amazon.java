package python;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.*;

/**
 * 读取多个亚马逊广告Excel报告，按客户搜索词维度统计（支持按日期+关键词去重取MAX）：
 * 总展示量、总点击量、总花费、总订单数、总销售额
 * 结果按总花费降序排列，输出为CSV文件
 *
 * @author bill
 * @create 2026/07/17
 */
public class amazon {

    // ==================== Excel 列名常量 ====================
    private static final String COL_DATE        = "日期";
    private static final String COL_KEYWORD     = "客户搜索词";
    private static final String COL_IMPRESSIONS = "展示量";
    private static final String COL_CLICKS      = "点击量";
    private static final String COL_SPEND       = "花费";
    private static final String COL_ORDERS      = "7天总销售量(#)";
    private static final String COL_SALES       = "7天总销售额";

    // ==================== CSV 输出列表头 ====================
    private static final String OUT_KEYWORD     = "客户搜索词";
    private static final String OUT_IMPRESSIONS = "总展示量";
    private static final String OUT_CLICKS      = "总点击量";
    private static final String OUT_SPEND       = "总花费";
    private static final String OUT_ORDERS      = "总订单数";
    private static final String OUT_SALES       = "总销售额";

    /** 单行原始数据 */
    private static class RowData {
        String date;
        String keyword;
        double impressions;
        double clicks;
        double spend;
        double orders;
        double sales;

        RowData() {}

        RowData(String date, String keyword) {
            this.date = date;
            this.keyword = keyword;
        }

        /** 合并另一行数据到当前行，各项指标取较大值（应对不同批次导出的数据延迟） */
        void mergeMax(RowData other) {
            this.impressions = Math.max(this.impressions, other.impressions);
            this.clicks      = Math.max(this.clicks, other.clicks);
            this.spend       = Math.max(this.spend, other.spend);
            this.orders      = Math.max(this.orders, other.orders);
            this.sales       = Math.max(this.sales, other.sales);
        }
    }

    /** 关键词汇总数据 */
    private static class KeywordStat {
        String keyword;
        double impressions;
        double clicks;
        double spend;
        double orders;
        double sales;

        KeywordStat(String keyword) {
            this.keyword = keyword;
        }

        void add(RowData row) {
            this.impressions += row.impressions;
            this.clicks      += row.clicks;
            this.spend       += row.spend;
            this.orders      += row.orders;
            this.sales       += row.sales;
        }
    }

    public static void main(String[] args) throws Exception {
        // ========== 配置区 ==========
        // 多文件列表：{文件路径, Sheet名称（null表示自动用第一个Sheet）}
        String[][] fileConfigs = {
                {"/Users/bill/Downloads/广告数据存档/截止于20260707/商品推广_搜索词_报告30.xlsx", "商品推广_搜索词_报告30"},
                {"/Users/bill/Downloads/商品推广_搜索词_报告716.xlsx", null},
        };
        String outputPath = "关键词汇总.csv";
        // ===========================

        // date+keyword → RowData，用于跨文件合并去重（取 MAX）
        Map<String, RowData> mergedMap = new LinkedHashMap<>();
        int totalRows = 0;

        // 逐个读取文件
        for (String[] cfg : fileConfigs) {
            String filePath = cfg[0];
            String sheetName = cfg[1];

            File file = new File(filePath);
            if (!file.exists()) {
                System.out.println("文件不存在，跳过: " + filePath);
                continue;
            }
            System.out.println("\n>>> 读取文件: " + filePath);

            int cnt = readExcel(filePath, sheetName, mergedMap);
            totalRows += cnt;
            System.out.println("  本文件行数: " + cnt + ", 累计唯一(date+关键词): " + mergedMap.size());
        }

        // 统计合并次数（被 mergeMax 更新的次数）
        int mergedCount = totalRows - mergedMap.size();
        System.out.println("\n总读取行数: " + totalRows
                + ", 唯一(date+关键词)组合: " + mergedMap.size()
                + ", 合并取MAX: " + mergedCount + " 行");

        // 按关键词汇总
        Map<String, KeywordStat> statMap = new LinkedHashMap<>();
        for (RowData row : mergedMap.values()) {
            statMap.computeIfAbsent(row.keyword, KeywordStat::new).add(row);
        }

        // 按总花费降序排序
        List<KeywordStat> stats = new ArrayList<>(statMap.values());
        stats.sort((a, b) -> Double.compare(b.spend, a.spend));

        // 输出 CSV
        try (PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(new FileOutputStream(outputPath), "UTF-8"))) {
            writer.print('\ufeff');
            writer.println(String.join(",",
                    OUT_KEYWORD, OUT_IMPRESSIONS, OUT_CLICKS, OUT_SPEND, OUT_ORDERS, OUT_SALES));
            for (KeywordStat s : stats) {
                writer.printf("%s,%.0f,%.0f,%.2f,%.0f,%.2f%n",
                        escapeCsv(s.keyword),
                        s.impressions,
                        s.clicks,
                        s.spend,
                        s.orders,
                        s.sales);
            }
        }

        System.out.println("汇总完成，共 " + stats.size() + " 个关键词，已生成 " + outputPath);
    }

    /**
     * 读取一个 Excel 文件，按 date+keyword 合并到 mergedMap
     * 同一 date+keyword 出现多次时，各项指标取 MAX（应对数据延迟）
     * @return 本文件读取的有效行数
     */
    private static int readExcel(String filePath, String sheetName,
                                  Map<String, RowData> mergedMap) throws Exception {
        int rowCount = 0;
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = getSheet(workbook, sheetName);
            if (sheet == null) return 0;

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                System.err.println("  Excel 文件无表头行");
                return 0;
            }

            // 解析表头
            Map<String, Integer> colMap = new HashMap<>();
            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = headerRow.getCell(i);
                if (cell != null) {
                    colMap.put(cell.getStringCellValue().trim(), i);
                }
            }

            // 查找列索引
            int idxDate        = findColumn(colMap, COL_DATE);
            int idxKeyword     = findColumn(colMap, COL_KEYWORD);
            int idxImpressions = findColumn(colMap, COL_IMPRESSIONS);
            int idxClicks      = findColumn(colMap, COL_CLICKS);
            int idxSpend       = findColumn(colMap, COL_SPEND);
            int idxOrders      = findColumn(colMap, COL_ORDERS);
            int idxSales       = findColumn(colMap, COL_SALES);

            if (idxKeyword < 0 || idxImpressions < 0 || idxClicks < 0
                    || idxSpend < 0 || idxOrders < 0 || idxSales < 0) {
                System.err.println("  Excel 缺少必要列");
                return 0;
            }

            // 遍历数据行
            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                String keyword = getCellString(row, idxKeyword);
                if (keyword == null || keyword.isEmpty()) continue;

                String date = idxDate >= 0 ? getCellString(row, idxDate) : "";
                String key = date + "|" + keyword;

                RowData rd = new RowData(date, keyword);
                rd.impressions = getCellDouble(row, idxImpressions);
                rd.clicks      = getCellDouble(row, idxClicks);
                rd.spend       = getCellDouble(row, idxSpend);
                rd.orders      = getCellDouble(row, idxOrders);
                rd.sales       = getCellDouble(row, idxSales);

                // 同 date+keyword 合并：取 MAX（应对不同批次导出的数据延迟）
                RowData existing = mergedMap.get(key);
                if (existing != null) {
                    existing.mergeMax(rd);
                } else {
                    mergedMap.put(key, rd);
                }
                rowCount++;
            }
        }
        return rowCount;
    }

    /** 获取 Sheet：先精确匹配 -> trim匹配 -> 回退第一个 */
    private static Sheet getSheet(Workbook workbook, String sheetName) {
        if (sheetName != null) {
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet != null) return sheet;
            for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
                if (workbook.getSheetName(i).trim().equals(sheetName.trim())) {
                    return workbook.getSheetAt(i);
                }
            }
        }
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println("  使用 Sheet: \"" + workbook.getSheetName(0) + "\"");
        return sheet;
    }

    // ==================== 工具方法 ====================

    private static String getCellString(Row row, int colIdx) {
        Cell cell = row.getCell(colIdx);
        if (cell == null) return null;
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }

    private static double getCellDouble(Row row, int colIdx) {
        Cell cell = row.getCell(colIdx);
        if (cell == null) return 0;
        try {
            if (cell.getCellType() == CellType.NUMERIC) {
                return cell.getNumericCellValue();
            } else if (cell.getCellType() == CellType.STRING) {
                String v = cell.getStringCellValue().trim();
                return v.isEmpty() ? 0 : Double.parseDouble(v);
            }
        } catch (Exception ignored) {
        }
        return 0;
    }

    private static int findColumn(Map<String, Integer> colMap, String keyword) {
        for (Map.Entry<String, Integer> entry : colMap.entrySet()) {
            if (entry.getKey().equals(keyword)) return entry.getValue();
        }
        String kw = keyword.replace('（', '(').replace('）', ')');
        for (Map.Entry<String, Integer> entry : colMap.entrySet()) {
            if (entry.getKey().replace('（', '(').replace('）', ')').contains(kw)) {
                return entry.getValue();
            }
        }
        return -1;
    }

    private static String escapeCsv(String value) {
        if (value == null) return "";
        if (value.contains(",") || value.contains("\"")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
