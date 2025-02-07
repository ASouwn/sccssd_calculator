package com.sccssd.sccssd_calculator.core;
public class cal_skyline {
    double LMax;
    double h ;
    double Tb;
    double P_max;
    double qc;
    double S0;
    double Ta;
    double aMax;
    public cal_skyline(){};

    public double cal_for_angle(double h,double LMax)
    {
        double tanAlphaMax  = h/LMax; //计算正切值
        double aMax = Math.atan(tanAlphaMax); //计算角度
        this. aMax = aMax;
        return aMax;
    }

    public double cal_for_P_Max( double pMax,  double ph, double Qq, double LMax,double h) // pmax 最大载荷重量  ph货车载荷质量 Qq 牵引索每米自重  Lmax 最大档跨度 h 高度
    {

        double Kc = 1.31;             // 冲击系数
        double cosaMax = Math.cos(aMax); // 计算余弦值  AMax 高差角
        double P_max = Kc * (pMax + ph + (Qq * LMax) / (2 * cosaMax));
        this.P_max=P_max;//最大载荷时的计算载荷
        return P_max;
    }

    public double cal_for_Ta( double qc, double LMax, double S0, double aMax) // qc承重索每米自重  AMax高差角
    {
        S0 = 0.05; // 中央驰比度
        double cosaMax = Math.cos(aMax); // 计算余弦值  AMax 高差角
        double Ta = (qc * LMax) / (8 * S0 * cosaMax);
        this.Ta=Ta;
        return Ta; // 无载荷时最大档的水平张力
    }


    public double cal_for_Tb(double qc, double Sc, double Ec, double LMax, double aMax, double P_max )
    {

        return Tb;// 最大载荷时最大档的水平张力
    }
    public double cal_for_T_max(double Tb, double P_max,  double qc)
    {

        double T_max = Math.sqrt(Math.pow(Tb, 2) + Math.pow(P_max, 2)) + qc * LMax;
        return T_max; //最大载荷时各支架支点处最大张力
    }
    public double cal_for_Tc(double Tp,double Tc)
    {
        Tp = 197;
        Tc = Tp/cal_for_T_max( Tb, P_max,qc);
        return Tc; //最大载荷时各支架支点处最大张力
    }
}



