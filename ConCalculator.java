package test;

/**
 * 该程序用以计算各游戏中抽奖系统相关数据
 * 比如计算前n次概率和第n次概率，以及保底概率
 * 计算出货数学期望
 * 计算接近概率n的次数
 * 计算包括的概率
 *概率显示保留两位小数
 *@author 短发(Raiyon)
 */

import java.util.*;
public class ConCalculator {
	final static int MODE_PRINT_EXC=0;
	final static int MODE_PRINT_PRO=1;
	static Scanner in=new Scanner(System.in);
	static double proba;
	static double sum=0;
	static double result=0;
	static double consumption;
	static boolean flag=true;
	static int x;
	public static void main(String[]args) {
		System.out.println("输入保底次数:");
		x=in.nextInt();
		System.out.println("输入概率:");
		proba=in.nextDouble();
		System.out.println("输入每次的消耗:");
		consumption=in.nextDouble();
		while(flag) {
			System.out.println("输入1查看n次概率,输入2查看出货的消耗数学期望，输入3查看概率接近的次数,输入4查看包括保底的概率");
			char c=in.next().charAt(0);
			switch(c) {
				case '1':
					printPro();
					break;
				case '2':
					printExpec(ConCalculator.MODE_PRINT_EXC);
					break;
				case '3':
					printClose();
					break;
				case '4':
					printExpec(ConCalculator.MODE_PRINT_PRO);
					break;
					default:
						flag=false;
						System.out.println("结束程序");
						break;
			}
		}
	}
	public static void printPro() {
		System.out.println("输入概率显示开始的次数");
		int begin=in.nextInt();
		System.out.println("输入概率显示结束的次数");
		int end=in.nextInt();
		if(end>x||begin<1||begin>x||end<1||begin>end) {
			System.out.println("显示的次数超过范围");
			return;
		}
		sum=0;
		for (int i=0;i<x-1;i++) {
			double totalproba=proba*Math.pow(1-proba,i);
			sum+=totalproba;
			if(i>=begin-1&&i<end)
			System.out.printf("第"+(i+1)+"次概率:%.4f前"+(i+1)+"次概率总和:%.4f\n",totalproba,sum);
		}
		System.out.printf("第"+x+"次概率:%.4f(保底)\n",1-sum);
	}
	public static void printExpec(int mode) {
		result=0;
		sum=0;
		for (int i=0;i<x-1;i++) {
			double totalproba=proba*Math.pow(1-proba,i);
			sum+=totalproba;
			result+=totalproba*consumption*(i+1);
		}
		result+=(1-sum)*consumption*(x);
		if(mode==ConCalculator.MODE_PRINT_EXC)
			System.out.printf("出货的数学期望:%.2f(保留2位小数)\n",result);
		if(mode==ConCalculator.MODE_PRINT_PRO)
			System.out.printf("包括保底在内的概率为:%.3f",(consumption/result)*100);
			System.out.println("%");
	}
	public static void printClose() {
		System.out.println("输入概率，查看接近概率的次数");
		double close=in.nextDouble();
		sum=0;
		double closest=1;
		int resultTimes=0;
		for (int i=0;i<x-1;i++) {
			double totalproba=proba*Math.pow(1-proba,i);
			sum+=totalproba;
			if(Math.abs(close-sum)<Math.abs(closest)) {
				closest=close-sum;
				resultTimes=i+1;
			}
		}
		System.out.printf("当次数为"+resultTimes+"时概率最为接近输入的概率，为%.4f\n",(close-closest));
	}
}
