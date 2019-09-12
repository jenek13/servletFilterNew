package javaguides.usermanagement.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    public static String getValue(String propertyname, String propertiesFileName) {
        String propertyvalue = null;
        InputStream inputStream = DBHelper.class.getClassLoader().getResourceAsStream(propertiesFileName);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            propertyvalue = properties.getProperty(propertyname);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return propertyvalue;
    }
}


