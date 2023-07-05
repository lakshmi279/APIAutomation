package utils;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Utils {

    public static String getJsonPath(Response response, String key) {
        String resp = response.asString();
        JsonPath js = new JsonPath(resp);
        return js.get(key).toString();
    }
    public static String getProperty(String key) throws IOException {
      InputStream input = new FileInputStream("src/test/java/utils/config.properties");

            Properties prop = new Properties();

            // load a properties file
            prop.load(input);
            return prop.getProperty(key);
    }

    public static String getBaseUrl() throws IOException {

        String env = getEnvironment();
        System.out.println("This is env: "+ env);

        String key = (env.equals("UAT")) ? "chain_url"  : (env.equals("QA")) ? "qa_url" : "dev_url";

        System.out.println(key);

        System.out.println(getProperty(key));

        return getProperty(key);
    }

    public static String  getEnvironment(){
        System.out.println(System.getProperty("env"));
        return System.getProperty("env");
    }
}
