package netLearning.download;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureDownloader {

	/**
	 * 
	 * @param pictureURLStringLeftPart
	 * @param pictureURLMatcher
	 * @param i
	 * @throws MalformedURLException
	 * @Description
	 */
	private static void savePicture(String pictureURLStringLeftPart,
			Matcher pictureURLMatcher, int i) throws MalformedURLException {
		String originalPictureURL = pictureURLMatcher.group(1);
		String pictureURLString = producePictureURLString(
				pictureURLStringLeftPart, originalPictureURL);
		File pictureFile = createResourceFile(i);
		DownloadTools.storeResource(new URL(pictureURLString), pictureFile);
	}
	
	/**
	 * 
	 * @param pictureURLStringLeftPart 根路径
	 * @param originalPictureURL 图片相对路径
	 * @return 图片的绝对路径
	 * @Description 返回图片资源的绝对路径
	 */
	private static String producePictureURLString(
			String pictureURLStringLeftPart, String originalPictureURL) {
		// e.g. /upFiles/infoImg/2014011225300813.jpg
		String pictureURLStringRightPart = originalPictureURL.substring(2);
		// e.g. http://www.xxxx.com/upFiles/infoImg/2014011225050969.jpg
		String pictureURLString = pictureURLStringLeftPart
				+ pictureURLStringRightPart;
		return pictureURLString;
	}
	
	/**
	 * 
	 * @param i
	 * @return
	 * @Description 构造图片文件
	 */
	private static File createResourceFile(int i) {
		String pictureFileName = "picture_" + i + ".jpg";
		File pictureFile = new File(pictureFileName);
		return pictureFile;
	}
	
	/**
	 * 
	 * @throws MalformedURLException
	 * @Description 下载本系列所有图片
	 */
	private static void mainDownloadPictures() throws MalformedURLException {
		String initialURLString = "http://www.xxxx.com/news/?2656_";
		String pictureURLStringLeftPart = "http://www.xxxx.com";
		StringBuffer pageContent = null;
		String pictureURLRegex = ".*(\\.\\./upFiles/infoImg/\\d*\\.jpg).*";
		Pattern pictureURLPattern = Pattern.compile(pictureURLRegex,
				Pattern.DOTALL);
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
	
	/**
	 * 
	 * @param initialURLString
	 * @param i
	 * @return
	 * @throws MalformedURLException
	 * @Description 获取网页中所有内容
	 */
	private static StringBuffer extractPageContent(String initialURLString,
			int i) throws MalformedURLException {
		URL pageURL;
		String pageURLString;
		StringBuffer pageContent;
		pageURLString = initialURLString + i + ".html";
		pageURL = new URL(pageURLString);
		pageContent = DownloadTools.getPageContent(pageURL);
		return pageContent;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
