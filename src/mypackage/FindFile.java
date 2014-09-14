package mypackage;

import java.io.File;

import static java.lang.System.*;
public class FindFile {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File file = new File("data.txt");
		String proPath = System.getProperty("user.dir");
		out.println(file.exists());

	}

}
