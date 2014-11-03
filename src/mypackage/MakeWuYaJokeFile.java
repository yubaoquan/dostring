package mypackage;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 
 * @author yubaoquan
 * @Date 2014年10月31日
 * @Description 需要一个保存n多个笑话的js文件,手写太费劲了,用这个生成;
 * DataOutputStream 这个类里的方法需要和DataInputStream对应着用,比如writeUTF写完的文件,
 * 每一个String开头都有两个byte的字符代表String长度;再比如writeChars方法写入的String,
 * 每一个char之后都有一个'\0',所以不适合写文件;
 * 
 *
 */
public class MakeWuYaJokeFile {

	private static final String PARENT_PATH = "E:/code/yubaoquan.github.io/js/";

	private void mainMethod() {
		File file = createFile("joke.js");
		fillTheFile(file);
	}

	private File createFile(String fileName) {
		File file = new File(PARENT_PATH + fileName);
		if (!file.exists()) {
			try {
				boolean flag = file.createNewFile();
				System.out.println(flag);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	private void fillTheFile(File file) {
		BufferedWriter dos = null;
		try {
			dos = new BufferedWriter(new FileWriter(file));
			dos.write("var joke = [];\n");
			for (int i = 0; i < 200; i++) {
				dos.write("joke[" + i + "] = \"这个笑话我忘了\";\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (dos != null) {
					dos.close();
					dos = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new MakeWuYaJokeFile().mainMethod();
		System.out.println("ok");
	}

}
