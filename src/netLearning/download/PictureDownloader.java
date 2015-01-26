package netLearning.download;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PictureDownloader {

	private static class MatchAndDownload implements Runnable {
		String initialURLString;
		String left;
		Pattern p;
		int i;

		MatchAndDownload(String initialURLString,
				String pictureURLStringLeftPart, Pattern pictureURLPattern,
				int i) {
			this.initialURLString = initialURLString;
			System.out.println(initialURLString);
			left = pictureURLStringLeftPart;
			p = pictureURLPattern;
			this.i = i;
		}

		@Override
		public void run() {
			System.out.println("start downloading picture " + i);
			matchAndDownload(initialURLString, left, p, i);
			System.out.println(i + " 引导线程结束, 图片下载正在新线程中进行");
		}
	}

	/**
	 * 
	 * @param i
	 * @return
	 * @Description 构造图片文件
	 */
	private static File createResourceFile(int i) {
		String suffix = ".gif";
		String fileNameLeftPart = "picture_" + i;
		String pictureFileName = fileNameLeftPart + suffix;
		File pictureFile = new File(pictureFileName);
		int index = 2;
		while (pictureFile.exists()) {
			fileNameLeftPart += "_";
			pictureFileName = fileNameLeftPart + suffix;
			pictureFile = new File(pictureFileName);
			System.out.println("there are " + index + " files in the same page.");
			index ++;
		}
		return pictureFile;
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
			int i)  {
		URL pageURL;
		String pageURLString;
		StringBuffer pageContent = null;
		pageURLString = initialURLString + i;
		System.out.println(pageURLString);
		try {
			pageURL = new URL(pageURLString);
			pageContent = DownloadTools.getPageContent(pageURL);
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return pageContent;
	}

	public static void main(String[] args) throws MalformedURLException {
		// // TODO Auto-generated method stub
		// "http://www.lunanzi.com/wp-content/uploads/2014/10/883121d8gw1ecl5s4m4uog20b408c7uo.gif";
		// "http://www.lunanzi.com/gif/4348.html/";
		String pictureName = "xxxxx<img src=http://www.lunanzi.com/wp-content/uploads/2014/10/1_131026193246_1.gif alt=衣服还1heightla-full wp-image-478t=----gifxxxxx";
		String pictureURLStringLeftPart = "http://www.lunanzi.com/wp-content/uploads/2014/10/";
		String pictureURLRegex = ".*/([0-9A-Za-z-_]*\\.gif) alt=.*?";// ".*(\\.\\./upFiles/infoImg/\\d*\\.jpg).*";
		Pattern pictureURLPattern = Pattern.compile(pictureURLRegex,
				Pattern.DOTALL);
		Matcher pictureURLMatcher = null;
		pictureURLMatcher = pictureURLPattern.matcher(pictureName);

		if (pictureURLMatcher.matches()) {

			System.out.println("匹配！");
			String str = pictureURLStringLeftPart + pictureURLMatcher.group(1);
			System.out.println(str);
		}
		mainDownloadPictures();
	}

	/**
	 * 
	 * @throws MalformedURLException
	 * @Description 下载本系列所有图片
	 */
	private static void mainDownloadPictures() throws MalformedURLException {
		String initialURLString = "http://www.lunanzi.com/gif/4502.html/";//4841//4502
		String pictureURLStringLeftPart = "http://www.lunanzi.com/wp-content/uploads/2014/10/";
		String pictureURLRegex = ".*?/([0-9A-Za-z-_]*\\.gif)\" alt=.*?";// ".*(\\.\\./upFiles/infoImg/\\d*\\.jpg).*";
		Pattern pictureURLPattern = Pattern.compile(pictureURLRegex,
				Pattern.DOTALL);
		int picturesCount = 50;
		for (int i = 1; i <= picturesCount; i++) {
			// matchAndDownload(initialURLString, pictureURLStringLeftPart,
			// pictureURLPattern, i);
			new Thread(new MatchAndDownload(initialURLString,
					pictureURLStringLeftPart, pictureURLPattern, i)).start();
		}
	}

	private static void matchAndDownload(String initialURLString,
			String pictureURLStringLeftPart, Pattern pictureURLPattern, int i) {
		StringBuffer pageContent;
		Matcher pictureURLMatcher;
		pageContent = extractPageContent(initialURLString, i);
		System.out.println("here1");
		pictureURLMatcher = pictureURLPattern.matcher(pageContent);
//		System.out.println(pageContent);
		System.out.println("here2");
		int index = 1;
		while (pictureURLMatcher.find()) {
			System.out.println("here");
			System.out.println("match " + index ++);
			try {
				System.out.println("into save");
				savePicture(pictureURLStringLeftPart, pictureURLMatcher, i);
				System.out.println("finish save");
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println(e.getMessage());
				continue;
			}
			System.out.println("资源" + i + "正在新线程中被下载,请稍等...");
		}
	}

	/**
	 * 
	 * @param pictureURLStringLeftPart
	 *            根路径
	 * @param originalPictureURL
	 *            图片相对路径
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
	 * @param pictureURLStringLeftPart
	 * @param pictureURLMatcher
	 * @param i
	 * @throws MalformedURLException
	 * @Description
	 */
	private static void savePicture(String pictureURLStringLeftPart,
			Matcher pictureURLMatcher, int i) throws MalformedURLException {
		String originalPictureURL = pictureURLMatcher.group(1);
		// String pictureURLString = producePictureURLString(
		// pictureURLStringLeftPart, originalPictureURL);
		String pictureURLString = pictureURLStringLeftPart
				+ pictureURLMatcher.group(1);
		
		if (pictureURLString.indexOf("ad-3.gif") != -1) {
			return;
		}
		System.out.println(pictureURLString);
		File pictureFile = createResourceFile(i);
		new Thread(new Runnable(){
			@Override
			public void run() {
				try {
					DownloadTools.storeResource(new URL(pictureURLString), pictureFile);
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("save one");
			}}).start();
//		DownloadTools.storeResource(new URL(pictureURLString), pictureFile);
	}
}
