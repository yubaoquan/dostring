package picture;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.ImageIcon;

public class P {
	
	//�����ߴ�ͼƬ�ǽ���ʹ������ģʽ,������=true
	public void doSome(String fileName,int suoLueH,int suoLueW) {
		if (suoLueH * suoLueW <= 0) {
			System.out.println("�������!");
			return;
		}
		try {
			File originFile = new File(fileName);
			String newFilePath = originFile.getParent() + File.separator + originFile.getName();
			newFilePath = newFilePath.substring(0,newFilePath.length() - 4) + ".txt";
			System.out.println(newFilePath);
			File characterPicture = new File(newFilePath);
			FileOutputStream fos = new FileOutputStream(characterPicture);
			DataOutputStream dos = new DataOutputStream(fos);
			
			// BufferedImage�൱��һ������,�ڴ������ڴ���
			//BufferedImage img = ImageIO.read(new File(fileName));
			BufferedImage img = ImageIO.read(originFile);
			
			int huabuwid = img.getWidth();
			int huabuhid = img.getHeight();
			
			int colStep = suoLueH;
			int rowStep = suoLueW;
			
			int newPictureLength = huabuhid / colStep + 1;//
			int newPictureWidth = huabuwid / rowStep + 1 ;
			//int step = suoLueBeiShu;
		//	int colStep = step + 1;//-----------------------
			
			String[][] characterImage = new String[newPictureLength][newPictureWidth];
			
			for (int i = 0; i < newPictureLength; i++) {
				for (int j = 0; j < newPictureWidth; j++) {
					characterImage[i][j] = "-";
				}
			}
			 
			String[] mappingPoint = {" ", ".",  ":","!", "?", "*", "c", "C", "O", "A", "@", "B", "E", "S", "W", "#"};//16
			int seperator =  16;

			for (int i = 0; i < huabuwid; i += rowStep) {
				for (int j = 0; j < huabuhid; j += colStep) {
					// �������ȡ�����Ӧ��RGB
					int rgb = img.getRGB(i, j);
					int R = (rgb & 0xff0000) >> 16;
					int G = (rgb & 0xff00) >> 8;
					int B = (rgb & 0xff);
					rgb = ((R * 256) + G) * 256 + B;

					characterImage[j / colStep][i / rowStep] = mappingPoint[15 - R / seperator];
				}
			}
			
			for (int i = 0; i < newPictureLength; i++) {
				for (int j = 0; j < newPictureWidth; j++) {
					dos.writeBytes(characterImage[i][j]);
				//	System.out.print(characterImage[i][j]);
				}
				//System.out.print("\r\n");
				dos.writeBytes("\r\n");
			}
			
			dos.close();
			dos = null;
			fos.close();
			fos = null;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String fileName = "C:\\Users\\DELL\\Desktop\\xst.jpg";
		new P().doSome(fileName,3,2);
		System.out.println("finished!");
	}
}