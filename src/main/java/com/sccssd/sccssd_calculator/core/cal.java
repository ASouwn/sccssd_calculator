package com.sccssd.sccssd_calculator.core;


public class cal {

    public static double calculateTb(
            double Ta,
            double q1g,
            double S1g,
            double E1g,
            double L_max,
            double alphaMaxRadians,
            double P_l_plus_max
    ) {
        // 计算D的值
        double cosAlphaMax = Math.cos(alphaMaxRadians);
        double numeratorD = Math.pow(q1g, 2) * S1g * E1g * Math.pow(L_max, 2) * Math.pow(cosAlphaMax, 3);
        double denominatorD = 24 * Math.pow(Ta, 2);
        double D = numeratorD / denominatorD;

        // 计算RHS
        double rhsPart1 = (Math.pow(q1g, 2) * S1g * E1g * Math.pow(L_max, 2) * Math.pow(cosAlphaMax, 3)) / 24.0;
        double term1 = (3 * P_l_plus_max) / (Math.pow(q1g, 2) * L_max);
        double term2 = q1g + (P_l_plus_max / L_max);
        double rhs = rhsPart1 * (1 + term1 * term2);

        // 牛顿迭代法参数
        double tolerance = 1e-6;
        int maxIterations = 1000;
        double Tb = Ta * 2; // 初始猜测值为Ta的两倍（可根据实际情况调整）

        for (int i = 0; i < maxIterations; i++) {
            double f = Math.pow(Tb, 3) - (Ta - D) * Math.pow(Tb, 2) - rhs;
            double fPrime = 3 * Math.pow(Tb, 2) - 2 * (Ta - D) * Tb;

            if (Math.abs(fPrime) < 1e-10) {
                // 处理导数过小的情况，调整初始猜测
                Tb += 1.0;
                continue;
            }

            double delta = f / fPrime;
            Tb -= delta;

            if (Math.abs(delta) < tolerance) {
                break;
            }
        }

        // 确保结果为正值（物理意义）
        return Math.abs(Tb);
    }

    // 示例用法
    public static void main(String[] args) {
        double Ta = 17.665;
        double q1g = 5.9e-3;
        double S1g = 1.33;
        double E1g = 167;
        double L_max = 1000;
        double alphaMaxRadians = Math.toRadians(0.835); // 角度转换为弧度
        double P_l_plus_max = 3.910;

        double Tb = calculateTb(Ta, q1g, S1g, E1g, L_max, alphaMaxRadians, P_l_plus_max);
        System.out.println("Calculated Tb: " + Tb);
    }
}