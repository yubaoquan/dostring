package netLearning.download;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadTools {

	/**
	 * 
	 * @param url
	 * @param storeFile
	 * @Description 讲URL对应的内容保存成本地文件
	 */
	public static void storeResource(URL url, File storeFile) {
		InputStream is = null;
		try {
			is = url.openStream();
			storeResource(is, storeFile);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(is);
		}
	}
	
	/**
	 * 
	 * @param inputStreamFromWebPage
	 * @param storeFile
	 * @Description 从输入流中获取数据,保存到文件中
	 */
	public static void storeResource(InputStream inputStreamFromWebPage,
			File storeFile) {
		byte[] byteArray = new byte[1024];
		FileOutputStream fos = null;
		int available = 0;
		try {
			fos = new FileOutputStream(storeFile);
			while ((available = inputStreamFromWebPage.read(byteArray)) != -1) {
				fos.write(byteArray, 0, available);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void storeResource(String content, File storeFile) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			fos = new FileOutputStream(storeFile);
			bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(new String(content.toString().getBytes(), "gb2312"));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				bw.close();
				bw = null;

				fos.close();
				fos = null;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void closeStream(Object stream) {
		if (stream != null) {
			try {
				if (stream instanceof InputStream) {
					((InputStream) stream).close();
				} else if (stream instanceof OutputStream) {
					((OutputStream) stream).close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * @param pageURLStr 网页URL的字符串表示
	 * @return
	 * @Description 抓取网页源代码
	 */
	public static StringBuffer getPageContent(String pageURLStr) {
		URL url;
		StringBuffer result = null;
		try {
			url = new URL(pageURLStr);
			result = getPageContent(url); 
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 
	 * @param pageURL 网页URL
	 * @return
	 * @Description 抓取网页源代码
	 */
	public static StringBuffer getPageContent(URL pageURL) {
		StringBuffer content = new StringBuffer("");
		HttpURLConnection connection;
		InputStream is = null;
		byte[] byteArray = new byte[1024];
		try {
			connection = (HttpURLConnection) pageURL.openConnection();
			connection.connect();
			is = connection.getInputStream();
			int available = 0;
			while ((available = is.read(byteArray)) != -1) {
				content.append(new String(byteArray, 0, available, "gb2312"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeStream(is);
		}
		return content;
	}
}
