package test;

/**
 * �ó������Լ������Ϸ�г齱ϵͳ�������
 * �������ǰn�θ��ʺ͵�n�θ��ʣ��Լ����׸���
 * ���������ѧ����
 * ����ӽ�����n�Ĵ���
 * ��������ĸ���
 *������ʾ������λС��
 *@author �̷�(Raiyon)
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
		System.out.println("���뱣�״���:");
		x=in.nextInt();
		System.out.println("�������:");
		proba=in.nextDouble();
		System.out.println("����ÿ�ε�����:");
		consumption=in.nextDouble();
		while(flag) {
			System.out.println("����1�鿴n�θ���,����2�鿴������������ѧ����������3�鿴���ʽӽ��Ĵ���,����4�鿴�������׵ĸ���");
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
						System.out.println("��������");
						break;
			}
		}
	}
	public static void printPro() {
		System.out.println("���������ʾ��ʼ�Ĵ���");
		int begin=in.nextInt();
		System.out.println("���������ʾ�����Ĵ���");
		int end=in.nextInt();
		if(end>x||begin<1||begin>x||end<1||begin>end) {
			System.out.println("��ʾ�Ĵ���������Χ");
			return;
		}
		sum=0;
		for (int i=0;i<x-1;i++) {
			double totalproba=proba*Math.pow(1-proba,i);
			sum+=totalproba;
			if(i>=begin-1&&i<end)
			System.out.printf("��"+(i+1)+"�θ���:%.4fǰ"+(i+1)+"�θ����ܺ�:%.4f\n",totalproba,sum);
		}
		System.out.printf("��"+x+"�θ���:%.4f(����)\n",1-sum);
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
			System.out.printf("��������ѧ����:%.2f(����2λС��)\n",result);
		if(mode==ConCalculator.MODE_PRINT_PRO)
			System.out.printf("�����������ڵĸ���Ϊ:%.3f",(consumption/result)*100);
			System.out.println("%");
	}
	public static void printClose() {
		System.out.println("������ʣ��鿴�ӽ����ʵĴ���");
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
		System.out.printf("������Ϊ"+resultTimes+"ʱ������Ϊ�ӽ�����ĸ��ʣ�Ϊ%.4f\n",(close-closest));
	}
}
