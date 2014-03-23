package package3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLTest {

	// 一个public方法，返回字符串，错误则返回"error open url"
	public static String getContent(String strUrl) {

		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
			String s = "";
			StringBuffer sb = new StringBuffer("");

			while ((s = br.readLine()) != null) {
				sb.append(s + "/r/n");
			}

			br.close();
			return sb.toString();
		} catch (Exception e) {
			return "error open url:" + strUrl;
		}
	}

	public static void initProxy(String host, int port, final String username, final String password) {
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, new String(password).toCharArray());
			}
		});

		System.setProperty("http.proxyType", "4");
		System.setProperty("http.proxyPort", Integer.toString(port));
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxySet", "true");

	}

	private static StringBuffer getPageContent(URL pageURL) {
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

	private static void storeResource(URL url, File storeFile) {
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

	private static void storeResource(InputStream inputStreamFromWebPage, File storeFile) {
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

	private static void storeResource(String content, File storeFile) {
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

	public static void main(String[] args) throws IOException {
		String initialURLString = "http://www.xxxx.com/news/?2656_";
		String pictureURLStringLeftPart = "http://www.xxxx.com";
		StringBuffer pageContent = null;
		String pictureURLRegex = ".*(\\.\\./upFiles/infoImg/\\d*\\.jpg).*";
		Pattern pictureURLPattern = Pattern.compile(pictureURLRegex, Pattern.DOTALL);
		Matcher pictureURLMatcher = null;
		int picturesCount = 15;
		for (int i = 1; i <= picturesCount; i++) {
			pageContent = extractPageContent(initialURLString, i);
			pictureURLMatcher = pictureURLPattern.matcher(pageContent);
			
			if (pictureURLMatcher.matches()) {
				savePicture(pictureURLStringLeftPart, pictureURLMatcher, i);
				System.out.println(i + "个资源被保存！");
			}
		}
	}

	private static StringBuffer extractPageContent(String initialURLString, int i) throws MalformedURLException {
		URL pageURL;
		String pageURLString;
		StringBuffer pageContent;
		pageURLString = initialURLString + i + ".html";
		pageURL = new URL(pageURLString);
		pageContent = getPageContent(pageURL);
		return pageContent;
	}

	
	private static File createResourceFile(int i) {
		String pictureFileName = "picture_" + i + ".jpg";
		File pictureFile = new File(pictureFileName);
		return pictureFile;
	}
	

	private static String producePictureURLString(String pictureURLStringLeftPart, String originalPictureURL) {
		// e.g. /upFiles/infoImg/2014011225300813.jpg
		String pictureURLStringRightPart = originalPictureURL.substring(2);
		//e.g.  http://www.xxxx.com/upFiles/infoImg/2014011225050969.jpg
		String pictureURLString = pictureURLStringLeftPart + pictureURLStringRightPart;
		return pictureURLString;
	}
	
	private static void savePicture(String pictureURLStringLeftPart, Matcher pictureURLMatcher, int i) throws MalformedURLException {
		String originalPictureURL = pictureURLMatcher.group(1);
		String pictureURLString = producePictureURLString(pictureURLStringLeftPart, originalPictureURL);
		File pictureFile = createResourceFile(i);
		storeResource(new URL(pictureURLString), pictureFile);
	}


}
