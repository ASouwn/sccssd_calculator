package com.sccssd.sccssd_calculator.core;

public class cal_dragline {
      double aMax; // 角度
      double Pc ;//货车载荷重量
      double Pmax;// 最大载荷重量
      double Pq; // 牵引载荷重量
      double Qq; // 牵引索自重
      double Lmax; // 最大挡跨度
      double Tq;
      double Tm;
      double Tz , Ts;
      double Tmax;
      double S0;
      public cal_dragline(){};
    public double cal_for_angle(double h,double LMax)
    {
        double tanAlphaMax  = h/LMax; //计算正切值
        double aMax = Math.atan(tanAlphaMax); //计算角度
        this. aMax = aMax;
        return aMax;
    }
      public double cal_for_Pq(double Pmax,  double Pc, double Qq, double Lmax)
      {
        double Pq = Pmax + Pc + (Qq * Lmax) / 2;
        this. Pq = Pq;
        return Pq; // 牵引载荷重量
      }
    public double cal_for_Tq(double Pq,double N, double Tq)
    {
        Tq =  Pq*N*Math.sin(aMax);
        this.Tq = Tq;
        return Tq;  // 牵引力
    }
    public double cal_for_Tm(double Pq,double N, double Tm)
    {
        double Km = 0.05;
        Tm = Km*Pq*N*Math.cos(aMax);
        this.Tm = Tm;
        return Tm; // 摩擦阻力
    }
    public double cal_for_Tz( double Tz)
    {
        double S0 = 0.05; // 中央驰比度
        Tz = Qq*Lmax/8*1.25*S0*Math.cos(aMax);
        this.Tz = Tz;
        return Tz;//无载荷时水平张力
    }
    public double cal_for_Tmax( double Tmax)
    {
        Tmax = Tq+Tm+Tz;
        this.Tmax = Tmax;
        return Tmax;//最大牵引力
    }
    public double cal_for_Ts( double Ts)
    {
        double Tp = 197;
        Ts = Tp/Tmax;
        this.Ts = Ts;
        return Ts;//牵引索强度校核
    }
}





















