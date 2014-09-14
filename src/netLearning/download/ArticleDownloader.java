package netLearning.download;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.MalformedURLException;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArticleDownloader {

	// 一个public方法，返回字符串，错误则返回"error open url"
	public static String getContent(String strUrl) {

		try {
			URL url = new URL(strUrl);
			BufferedReader br = new BufferedReader(new InputStreamReader(
					url.openStream()));
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

	/**
	 * 
	 * @param host
	 * @param port
	 * @param username
	 * @param password
	 * @Description 用户初始化一些属性,此属性用于需要登录的网站
	 */
	public static void initProxy(String host, int port, final String username,
			final String password) {
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,
						new String(password).toCharArray());
			}
		});

		System.setProperty("http.proxyType", "4");
		System.setProperty("http.proxyPort", Integer.toString(port));
		System.setProperty("http.proxyHost", host);
		System.setProperty("http.proxySet", "true");

	}

	private static void mainDownloadPage() throws MalformedURLException {
		String urlString = "http://www.ii69.net/5367.html";
		DownloadTools.storeResource(new URL(urlString), new File(
				"E:/xx/page.html"));
	}

	private static void findZhouDedongURLs() {
		String shortNovelURL = "<a href=\"((\\w)+.html)\">(《.+?》)</a>";
		String longNovelURL = "<a href=\"(((\\w)+?)?(/(\\w)+?)+?.html)\">(《.+?》)</a>";
		String mainPageURL = "http://www.shuku.net/novels/zhentan/zhouddongap/zddzp.html";
		String mainPageContent = DownloadTools.getPageContent(mainPageURL)
				.toString();
		System.out.println("short novels:");
		findArticleURLs(mainPageContent, shortNovelURL);
		System.out.println("long novels:");
		findArticleURLs(mainPageContent, longNovelURL);
	}

	private static void testFindZhouDedong() {
		findZhouDedongURLs();
	}

	/**
	 * 
	 * @param mainPageContent
	 *            网页内容
	 * @param urlPatternStr
	 *            目标URL的正则
	 * @return result 包含本页中所有匹配的URL的List
	 * @Description 从网页内容中找到匹配的URL
	 */
	private static Map<String, URL> findArticleURLs(String mainPageContent,
			String urlPatternStr) {
		Map<String, URL> result = new HashMap<>();
		Pattern urlPattern = Pattern.compile(urlPatternStr);
		Matcher matcher = null;
		matcher = urlPattern.matcher(mainPageContent);
		System.out.println(matcher.groupCount());
		while (matcher.find()) {
			int titleIndex = matcher.groupCount();
			String url = matcher.group(1);
			String title = matcher.group(titleIndex);
			System.out.println("url:" + url);
			System.out.println("title:" + title);

		}
		return result;
	}

	private static void testFindArticleURLs() {
		String content = "aaaadsfadfadhttpfasdfhttp://www.baidu.comasdfadfdsf\nhttp://sohu.cn";
		String urlPatternStr = "http://(www\\.)?(\\w)*\\.(com|cn)";
		findArticleURLs(content, urlPatternStr);
	}

	public static void main(String[] args) throws IOException {
		// mainDownloadPage();
		// testFindArticleURLs();
		testFindZhouDedong();
	}

}
