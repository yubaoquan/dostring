package package5;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import static java.lang.System.*;

public class TestReadProperties {

	private static void testProperties() {
		InputStream is = TestReadProperties.class.getResourceAsStream("prop.txt");
		Properties prop = new Properties();
		try {
			
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String value = prop.getProperty("hantingyi");
		out.println(value);
	}
	
	public static void main(String[] args) {
		testProperties();

	}

}
