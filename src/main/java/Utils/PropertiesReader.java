package Utils;

import Data.TestData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Properties;

public class PropertiesReader {
	
	private final static Logger log = LogManager.getLogger(PropertiesReader.class);
	

	
	public static String setVariable(String variable_fromJenkins, String variable_fromXML) {
		String result;
		if (System.getenv(variable_fromJenkins) != null) {
			result = System.getenv(variable_fromJenkins);
			log.info(variable_fromJenkins + " from Jenkins is " + result);
		} else {
			result = variable_fromXML;
			log.info(variable_fromJenkins + " from XML is " + result);
		}
		return result;
	}
	
	public static String getSystemPropFile(String propFileName) {
		String popFilePath = "";
		try {
			Field field = TestData.class.getDeclaredField(propFileName);
			popFilePath = field.get(new TestData()).toString();
		} catch (IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		log.info("SYSTEM_PROP_FILE path is " + popFilePath);
		return popFilePath;
	}
	

	public static String getPropertyValue(String property, String propertiesFilePath) {
		Properties properties = new Properties();
		try (FileReader fileReader = new FileReader(propertiesFilePath)) {
			properties.load(fileReader);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return properties.getProperty(property);
	}
}
