package minsu;

import java.util.*;


public class YouthHostelProfitSimulator {

    // 基础参数
    private static final int BEDS = 30;
    private static final double RENT = 3000;    // 房租（元/月）
    private static final double STAFF = 5000;   // 人工（元/月）

    // 销售平台及佣金率（Java 8 初始化方式）
    private static final Map<String, Map<String, Double>> PLATFORMS = new HashMap<String, Map<String, Double>>() {{
        put("美团", new HashMap<String, Double>() {{
            put("share", 0.4);
            put("commission", 0.15);
        }});
        put("携程", new HashMap<String, Double>() {{
            put("share", 0.3);
            put("commission", 0.12);
        }});
        put("飞猪", new HashMap<String, Double>() {{
            put("share", 0.2);
            put("commission", 0.10);
        }});
        put("去哪儿", new HashMap<String, Double>() {{
            put("share", 0.1);
            put("commission", 0.12);
        }});
    }};

    // 月度参数（镇江，Java 8 初始化方式）
    private static final Map<String, Map<String, Double>> MONTHS = new HashMap<String, Map<String, Double>>() {{
        put("1月", new HashMap<String, Double>() {{
            put("season", 0.0);
            put("occupancy_rate", 0.4);
            put("bed_price", 35.0);
            put("utilities", 2800.0);
        }});
        put("2月", new HashMap<String, Double>() {{
            put("season", 0.0);
            put("occupancy_rate", 0.5);
            put("bed_price", 40.0);
            put("utilities", 2500.0);
        }});
        put("3月", new HashMap<String, Double>() {{
            put("season", 1.0);
            put("occupancy_rate", 0.65);
            put("bed_price", 45.0);
            put("utilities", 2500.0);
        }});
        put("4月", new HashMap<String, Double>() {{
            put("season", 2.0);
            put("occupancy_rate", 0.85);
            put("bed_price", 55.0);
            put("utilities", 2500.0);
        }});
        put("5月", new HashMap<String, Double>() {{
            put("season", 2.0);
            put("occupancy_rate", 0.9);
            put("bed_price", 60.0);
            put("utilities", 2500.0);
        }});
        put("6月", new HashMap<String, Double>() {{
            put("season", 1.0);
            put("occupancy_rate", 0.7);
            put("bed_price", 50.0);
            put("utilities", 3000.0);
        }});
        put("7月", new HashMap<String, Double>() {{
            put("season", 1.0);
            put("occupancy_rate", 0.75);
            put("bed_price", 50.0);
            put("utilities", 3500.0);
        }});
        put("8月", new HashMap<String, Double>() {{
            put("season", 1.0);
            put("occupancy_rate", 0.7);
            put("bed_price", 50.0);
            put("utilities", 3500.0);
        }});
        put("9月", new HashMap<String, Double>() {{
            put("season", 2.0);
            put("occupancy_rate", 0.8);
            put("bed_price", 55.0);
            put("utilities", 2500.0);
        }});
        put("10月", new HashMap<String, Double>() {{
            put("season", 2.0);
            put("occupancy_rate", 0.85);
            put("bed_price", 60.0);
            put("utilities", 2500.0);
        }});
        put("11月", new HashMap<String, Double>() {{
            put("season", 0.0);
            put("occupancy_rate", 0.45);
            put("bed_price", 40.0);
            put("utilities", 2500.0);
        }});
        put("12月", new HashMap<String, Double>() {{
            put("season", 0.0);
            put("occupancy_rate", 0.35);
            put("bed_price", 35.0);
            put("utilities", 2800.0);
        }});
    }};

    public static void main(String[] args) {
        List<Map<String, Object>> results = new ArrayList<>();
        double annualProfit = 0;

        // 计算每月数据
        for (Map.Entry<String, Map<String, Double>> entry : MONTHS.entrySet()) {
            String month = entry.getKey();
            Map<String, Double> params = entry.getValue();

            // 收入计算
            double dailyIncome = BEDS * params.get("occupancy_rate") * params.get("bed_price");
            double monthlyIncome = dailyIncome * 30;

            // 平台佣金
            Map<String, Double> platformFees = new HashMap<>();
            double totalCommission = 0;
            for (Map.Entry<String, Map<String, Double>> platformEntry : PLATFORMS.entrySet()) {
                String platform = platformEntry.getKey();
                Map<String, Double> platformInfo = platformEntry.getValue();
                double fee = monthlyIncome * platformInfo.get("share") * platformInfo.get("commission");
                platformFees.put(platform, fee);
                totalCommission += fee;
            }

            // 总成本
            double totalCost = RENT + STAFF + params.get("utilities") + totalCommission;

            // 利润
            double profit = monthlyIncome - totalCost;
            annualProfit += profit;

            // 保存结果
            Map<String, Object> monthResult = new LinkedHashMap<>();
            monthResult.put("月份", month);
            monthResult.put("季节", getSeasonName(params.get("season")));
            monthResult.put("入住率", params.get("occupancy_rate"));
            monthResult.put("床位价格(元/晚)", params.get("bed_price"));
            monthResult.put("收入(元)", monthlyIncome);
            monthResult.put("房租(元)", RENT);
            monthResult.put("人工(元)", STAFF);
            monthResult.put("水电杂费(元)", params.get("utilities"));
            monthResult.put("平台佣金(元)", totalCommission);
            monthResult.put("总成本(元)", totalCost);
            monthResult.put("利润(元)", profit);
            platformFees.forEach((k, v) -> monthResult.put(k + "扣费(元)", v));

            results.add(monthResult);
        }

        // 打印结果
        printResults(results);
        System.out.printf("\n年度总利润：%.2f元\n", annualProfit);
    }

    private static String getSeasonName(double seasonCode) {
        if (seasonCode == 0.0) return "淡季";
        else if (seasonCode == 1.0) return "平季";
        else if (seasonCode == 2.0) return "旺季";
        else return "-";
    }

    private static void printResults(List<Map<String, Object>> results) {
        // 表头
        System.out.println("| 月份 | 季节 | 入住率 | 床位价格(元/晚) | 收入(元) | 房租(元) | 人工(元) | 水电杂费(元) | 平台佣金(元) | 总成本(元) | 利润(元) | 美团扣费(元) | 携程扣费(元) | 飞猪扣费(元) | 去哪儿扣费(元) |");
        System.out.println("|------|------|--------|-----------------|----------|----------|----------|--------------|--------------|------------|----------|--------------|--------------|--------------|----------------|");

        // 数据行
        for (Map<String, Object> row : results) {
            System.out.printf(
                    "| %s | %s | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f | %.2f |\n",
                    row.get("月份"),
                    row.get("季节"),
                    (Double) row.get("入住率"),
                    (Double) row.get("床位价格(元/晚)"),
                    (Double) row.get("收入(元)"),
                    (Double) row.get("房租(元)"),
                    (Double) row.get("人工(元)"),
                    (Double) row.get("水电杂费(元)"),
                    (Double) row.get("平台佣金(元)"),
                    (Double) row.get("总成本(元)"),
                    (Double) row.get("利润(元)"),
                    (Double) row.get("美团扣费(元)"),
                    (Double) row.get("携程扣费(元)"),
                    (Double) row.get("飞猪扣费(元)"),
                    (Double) row.get("去哪儿扣费(元)")
            );
        }
    }
}