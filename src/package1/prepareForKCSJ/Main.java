package package1.prepareForKCSJ;

import java.util.Scanner;

public class Main {

	/**
	 * @param args
	 */
	
	public static void doStr(String str,float[] array){
		int arrayIndex = 0;//��ǰ�����������еĴ��λ��
		String number = "";//������װ���ֵ��ַ�
		boolean dotExist = false;//�������Ƿ��Ѿ���С���
		for(int i = 0;i < str.length();i++){
			char a = str.charAt(i);
			while(Character.isDigit(a) ||( (a == '+'||a == '-'||a == '.' ) && Character.isDigit( str.charAt(i+1) ) ) ){
				//��ǰ��ĸ��С���Ĵ���
				if(a == '.'){	
					if(!dotExist){
							number += a;
							dotExist = true;
						}else{
							dotExist = false;
							break;
						}
				}else{//��ǰ��ĸ����С���
					number += a;
				}
				if(i == str.length() - 1){//�ַ������
						break;
					}
				if(i < (str.length() - 1) ){//�ַ�δ�������
					i ++;
					a = str.charAt(i);
				}
			}
			//���������������ִ�ӡһ�²��Ž�����
			if(number != ""){
				System.out.println(number);
				array[arrayIndex] = Float.parseFloat(number);
				arrayIndex ++;
				number = "";
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String targetStr = null;
		do{
			targetStr = sc.nextLine();
			float[] array = new float[20];
			doStr(targetStr,array);
			for(int i = 0;i < 19;i ++){
				System.out.print(array[i] + " ");
			}
		}while(targetStr.length() != 0);
		sc.close();
	}
}
