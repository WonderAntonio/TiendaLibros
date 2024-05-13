package tiendalibros;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class propertyFile {

	private Properties configProps = null; 
	
	public propertyFile(){ 
		try { 
			File configFile = new File("src/tiendalibros/config.properties");
			FileReader reader = new FileReader(configFile);
			configProps = new Properties();
			configProps.load(reader);
			reader.close();
			configFile = null;
		} catch (FileNotFoundException ex) {
			// file does not exist
			ex.printStackTrace();
		} catch (IOException ex) {
			// I/O error
			ex.printStackTrace();
		}
	}
	public String getPropValue(String key) {
		String keyvalue =""; 
		if (configProps.containsKey(key))
			keyvalue = configProps.getProperty(key);
		 	return keyvalue ;
	}
}

