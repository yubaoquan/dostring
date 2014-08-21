package package1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import static java.lang.System.*;

public class CalculateAverage {

	private static Logger logger = Logger.getLogger(CalculateAverage.class);
	private static InputStream is;
	private static BufferedReader br = null;
	
	public static void readAndCalculate(String fileName, String respPrefix, String bizPrefix) {
		BufferedReader br = init(fileName);
		int respCount = 0;
		int bizCount = 0;
		long respSum = 0;
		long bizSum = 0;
		long restAvgTime = 0;
		long bizAvgTime = 0;
		int respPos ;
		int bizPos ;
		int upperThan1200 = 0;
		int upperAvg = 0;
		int upperSum = 0;
		try {
			String line = "";
			while (line != null) {
				line = br.readLine();
				if (line != null && line.contains(respPrefix)) {
					respCount ++;
					respPos = line.indexOf(respPrefix) + respPrefix.length();
					String numberStr = line.substring(respPos);
					int number = Integer.parseInt(numberStr);
					respSum += number;
					restAvgTime = respSum / respCount;
					logger.info("this time: " + number + "|sum: " + respSum + "|" + "resp average time: " + restAvgTime);
					if (number > 1253) {
						upperThan1200 ++;
						upperSum += number;
						upperAvg = upperSum / upperThan1200;
						out.println(line);
					}
					//out.println("resp average time: " + restAvgTime);
				}
				if (line != null && line.contains(bizPrefix)) {
					bizCount ++;
					bizPos = line.indexOf(bizPrefix) + bizPrefix.length();
					String numberStr = line.substring(bizPos);
					int number = Integer.parseInt(numberStr);
					bizSum += number;
					bizAvgTime = bizSum / bizCount;
					out.println("biz average time: " + bizAvgTime);
				}
			}
			out.println("beyond 1253 time: " + upperThan1200 + "| avg : " + upperAvg);
			out.println("resp count: " + respCount);
			out.println("biz count: " + bizCount);
		} catch (IOException e) {
			e.printStackTrace();
			closeReader();
			closeStream();
			return;
		}
	}
	private static void closeStream() {
		if (is != null) {
			try {
				is.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			is = null;
		}
	}
	private static void closeReader() {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			br = null;
		}
	}
	private static BufferedReader init(String fileName) {
		File file = new File(fileName);
		try {
			is = new FileInputStream(file);
			InputStreamReader reader = new InputStreamReader(is);
			br = new BufferedReader(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return br;
	}
	
	public static void main(String[] args) {
		readAndCalculate("Sigapi2.log", "resptime:", "dealFetionSubBiz:");
		
	}
}
