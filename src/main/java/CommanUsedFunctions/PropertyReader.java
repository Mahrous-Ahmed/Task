package CommanUsedFunctions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    Properties  prop;
    public PropertyReader(String filePath) throws IOException {
        FileInputStream FIS = new FileInputStream(filePath);
        prop = new Properties();
        prop.load(FIS);
    }
    public String getValue( String propertyKey) {
        return prop.getProperty(propertyKey);
    }
}
