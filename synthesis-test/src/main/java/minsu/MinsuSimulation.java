package minsu;

import java.util.Random;

public class MinsuSimulation {

    public static void main(String[] args) {
        // 常量定义
        int totalRooms = 32; // 民宿房间总数
        double basePrice = 50; // 基础房价
        double lowSeasonRate = 0.3; // 淡季入住率
        double highSeasonRate = 0.95; // 旺季入住率
        double lowSeasonPriceFactor = 0.8; // 淡季价格调整系数（降低20%）
        double highSeasonPriceFactor = 1.5; // 旺季价格调整系数（提高80%）
        int lowSeasonDays = 240; // 淡季天数
        int highSeasonDays = 125; // 旺季天数

        // 成本及费用定义
        double transferFee = 36000.0; // 转让费
        double deposit = 0.0; // 押金
        double networkFee = 1000.0; // 网络费用
        double maintenanceFee = 5000.0; // 年度维修费用
        double employeeSalary = 3000.0 * 12; // 员工工资（按月 * 12个月）
        double taxRate = 0.01; // 纳税比例（1%）
        double electricityCostPerUnit = 0.56; // 每度电的电费
        double waterCostPerCubicMeter = 5.95; // 每立方米水费
        double disposableCostPerRoom = 0.0; // 每次入住的一次性用品成本
        int minElectricityUsagePerRoom = 1; // 每间房每日最低用电量（度）
        int maxElectricityUsagePerRoom = 3; // 每间房每日最高用电量（度）
        int averageWaterUsagePerRoom = 1; // 每间房每日平均用水量（立方米）

        // 平台佣金比例
        double douyinCommissionRate = 0.05; // 抖音佣金比例
        double ctripCommissionRate = 0.12; // 携程佣金比例
        double meituanCommissionRate = 0.12; // 美团佣金比例
        double otherPlatformCommissionRate = 0.10; // 其他平台佣金比例

        // 模拟变量初始化
        Random random = new Random();

        double[][] dailyRevenue = new double[365][totalRooms]; // 每日房间收入
        double[][] dailyElectricityCost = new double[365][totalRooms]; // 每日房间电费
        double[][] dailyWaterCost = new double[365][totalRooms]; // 每日房间水费
        double[][] dailyDisposableCost = new double[365][totalRooms]; // 每日房间一次性用品成本
        double[][] dailyPlatformCommission = new double[365][totalRooms]; // 每日房间平台佣金
        String[][] dailyOrderSource = new String[365][totalRooms]; // 每日房间订单来源
        int dayIndex = 0;

        double totalElectricityCost = 0; // 年度电费总计
        double totalWaterCost = 0; // 年度水费总计
        double totalDisposableCost = 0; // 年度一次性用品成本总计
        double totalDouyinCommission = 0; // 年度抖音佣金总计
        double totalCtripCommission = 0; // 年度携程佣金总计
        double totalMeituanCommission = 0; // 年度美团佣金总计
        double totalOtherPlatformCommission = 0; // 年度其他平台佣金总计
        double totalRevenue = 0; // 年度总收入
        int totalDouyinOrders = 0; // 年度抖音订单数量
        int totalCtripOrders = 0; // 年度携程订单数量
        int totalMeituanOrders = 0; // 年度美团订单数量
        int totalOtherOrders = 0; // 年度其他平台订单数量

        // 淡季模拟
        for (int i = 0; i < lowSeasonDays; i++) {
            for (int room = 0; room < totalRooms; room++) {
                boolean isOccupied = random.nextDouble() < lowSeasonRate; // 判断房间是否被占用
                if (isOccupied) {
                    double roomPrice = basePrice * (lowSeasonPriceFactor + random.nextDouble() * 0.1); // 随机房价浮动
                    int electricityUsage = random.nextInt(maxElectricityUsagePerRoom - minElectricityUsagePerRoom + 1) + minElectricityUsagePerRoom;
                    double electricityCost = electricityUsage * electricityCostPerUnit; // 电费计算
                    double waterCost = averageWaterUsagePerRoom * waterCostPerCubicMeter; // 水费计算
                    double disposableCost = disposableCostPerRoom; // 一次性用品成本

                    // 随机分配平台佣金
                    double platformCommission = 0;
                    String platformSource = "";
                    double randomPlatform = random.nextDouble();
                    if (randomPlatform < 0.40) {
                        platformCommission = roomPrice * meituanCommissionRate;
                        totalMeituanCommission += platformCommission;
                        platformSource = "美团";
                        totalMeituanOrders++; // 增加美团平台订单量
                    } else if (randomPlatform < 0.70) {
                        platformCommission = roomPrice * ctripCommissionRate;
                        totalCtripCommission += platformCommission;
                        platformSource = "携程";
                        totalCtripOrders++; // 增加携程平台订单量
                    } else if (randomPlatform < 0.85) {
                        platformCommission = roomPrice * douyinCommissionRate;
                        totalDouyinCommission += platformCommission;
                        platformSource = "抖音";
                        totalDouyinOrders++; // 增加抖音平台订单量
                    } else {
                        platformCommission = roomPrice * otherPlatformCommissionRate;
                        totalOtherPlatformCommission += platformCommission;
                        platformSource = "其他平台";
                        totalOtherOrders++; // 增加其他平台订单量
                    }

                    dailyRevenue[dayIndex][room] = roomPrice;
                    dailyElectricityCost[dayIndex][room] = electricityCost;
                    dailyWaterCost[dayIndex][room] = waterCost;
                    dailyDisposableCost[dayIndex][room] = disposableCost;
                    dailyPlatformCommission[dayIndex][room] = platformCommission;
                    dailyOrderSource[dayIndex][room] = platformSource;

                    totalRevenue += roomPrice;
                    totalElectricityCost += electricityCost;
                    totalWaterCost += waterCost;
                    totalDisposableCost += disposableCost;
                }
            }
            dayIndex++;
        }

        // 旺季模拟
        for (int i = 0; i < highSeasonDays; i++) {
            for (int room = 0; room < totalRooms; room++) {
                boolean isOccupied = random.nextDouble() < highSeasonRate; // 判断房间是否被占用
                if (isOccupied) {
                    double roomPrice = basePrice * (highSeasonPriceFactor - random.nextDouble() * 0.1); // 随机房价浮动
                    int electricityUsage = random.nextInt(maxElectricityUsagePerRoom - minElectricityUsagePerRoom + 1) + minElectricityUsagePerRoom;
                    double electricityCost = electricityUsage * electricityCostPerUnit; // 电费计算
                    double waterCost = averageWaterUsagePerRoom * waterCostPerCubicMeter; // 水费计算
                    double disposableCost = disposableCostPerRoom; // 一次性用品成本

                    // 随机分配平台佣金
                    double platformCommission = 0;
                    String platformSource = "";
                    double randomPlatform = random.nextDouble();
                    if (randomPlatform < 0.40) {
                        platformCommission = roomPrice * meituanCommissionRate;
                        totalMeituanCommission += platformCommission;
                        platformSource = "美团";
                        totalMeituanOrders++; // 增加美团平台订单量
                    } else if (randomPlatform < 0.70) {
                        platformCommission = roomPrice * ctripCommissionRate;
                        totalCtripCommission += platformCommission;
                        platformSource = "携程";
                        totalCtripOrders++; // 增加携程平台订单量
                    } else if (randomPlatform < 0.85) {
                        platformCommission = roomPrice * douyinCommissionRate;
                        totalDouyinCommission += platformCommission;
                        platformSource = "抖音";
                        totalDouyinOrders++; // 增加抖音平台订单量
                    } else {
                        platformCommission = roomPrice * otherPlatformCommissionRate;
                        totalOtherPlatformCommission += platformCommission;
                        platformSource = "其他平台";
                        totalOtherOrders++; // 增加其他平台订单量
                    }

                    dailyRevenue[dayIndex][room] = roomPrice;
                    dailyElectricityCost[dayIndex][room] = electricityCost;
                    dailyWaterCost[dayIndex][room] = waterCost;
                    dailyDisposableCost[dayIndex][room] = disposableCost;
                    dailyPlatformCommission[dayIndex][room] = platformCommission;
                    dailyOrderSource[dayIndex][room] = platformSource;

                    totalRevenue += roomPrice;
                    totalElectricityCost += electricityCost;
                    totalWaterCost += waterCost;
                    totalDisposableCost += disposableCost;
                }
            }
            dayIndex++;
        }

        // 输出每日明细
        System.out.println("=== 民宿每日明细 ===");
        for (int day = 0; day < 365; day++) {
            System.out.println("第 " + (day + 1) + " 天:");
            for (int room = 0; room < totalRooms; room++) {
                if (dailyRevenue[day][room] > 0) {
                    System.out.printf(" 房间 %d, 收入: %.2f 元, 电费: %.2f 元, 水费: %.2f 元, 一次性用品成本: %.2f 元, 平台佣金: %.2f 元, 订单来源: %s\n", room + 1, dailyRevenue[day][room], dailyElectricityCost[day][room], dailyWaterCost[day][room], dailyDisposableCost[day][room], dailyPlatformCommission[day][room], dailyOrderSource[day][room]);
                } else {
                    System.out.printf(" 房间 %d - 空置\n", room + 1);
                }
            }
        }

        // 输出年度总计
        System.out.println("\n=== 民宿年度总计 ===");
        System.out.printf("总收入: %.2f 元\n", totalRevenue);
        System.out.printf("总电费: %.2f 元\n", totalElectricityCost);
        System.out.printf("总水费: %.2f 元\n", totalWaterCost);
        System.out.printf("一次性用品总成本: %.2f 元\n", totalDisposableCost);
        System.out.printf("抖音平台佣金总计: %.2f 元\n", totalDouyinCommission);
        System.out.printf("携程平台佣金总计: %.2f 元\n", totalCtripCommission);
        System.out.printf("美团平台佣金总计: %.2f 元\n", totalMeituanCommission);
        System.out.printf("其他平台佣金总计: %.2f 元\n", totalOtherPlatformCommission);

        // 输出平台订单数量
        System.out.println("\n平台订单数量统计：");
        System.out.printf("抖音平台订单数量: %d 单\n", totalDouyinOrders);
        System.out.printf("携程平台订单数量: %d 单\n", totalCtripOrders);
        System.out.printf("美团平台订单数量: %d 单\n", totalMeituanOrders);
        System.out.printf("其他平台订单数量: %d 单\n", totalOtherOrders);

        // 其他费用和净利润计算
        System.out.printf("其他固定成本 (转让费、押金、网络费、维修费、员工工资): %.2f 元\n", transferFee + deposit + networkFee + maintenanceFee + employeeSalary);
        double totalCost = totalElectricityCost + totalWaterCost + totalDisposableCost + totalDouyinCommission + totalCtripCommission + totalMeituanCommission + totalOtherPlatformCommission + transferFee + deposit + networkFee + maintenanceFee + employeeSalary;
        System.out.printf("总支出: %.2f 元\n", totalCost);
        System.out.printf("净利润: %.2f 元\n", totalRevenue - totalCost);
    }
}
