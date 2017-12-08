package Base;

import com.github.shyiko.dotenv.DotEnv;
import org.openqa.selenium.WebDriver;

import java.util.Map;

/**
 * Created by NhatDell on 06-Jul-17.
 */
public class BaseUtils {
    public WebDriver        driver;

    public static String url(){
        Map<String, String> dot = DotEnv.load();
        String env = dot.get("ENVIRONMENT").toLowerCase();
        switch (env){
            case "local":
                return "https://local.url.com.au";
            case "staging":
                return   "https://staging.url.com.au";
            case "production":
                return   "https://google.com.vn";
            case "prelaunch":
                return "https://prelaunch.url.com.au";
            default:
                return "Please enter environment in config file";
        }
    }
}
