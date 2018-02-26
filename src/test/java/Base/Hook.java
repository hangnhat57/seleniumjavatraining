package Base;

import Base.BaseUtils;
import com.github.shyiko.dotenv.DotEnv;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.EdgeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by NhatDell on 06-Jul-17.
 */
public class Hook extends BaseUtils {

    private final BaseUtils base;
    public Hook(BaseUtils base) {
        this.base = base;
    }
    private final String browser = getEnvirOf("BROWSER").toLowerCase();//dot.get("BROWSER").toLowerCase();
    private String browser_size(){
        String browser_size = "window-size=3840,2160";
        switch (getEnvirOf("BROWSER_SIZE").toLowerCase())
        {
            case "2k":browser_size =  "window-size=1920,1080";break;
            case "4k":browser_size =  "window-size=3840,2160";break;
        }
        return browser_size;
    }
    private final String os = System.getProperty("os.name").toLowerCase();
    private final String mode = getEnvirOf("BROWSER_MODE");
    private final String Env = getEnvirOf("ENVIRONMENT");

    @Before
    public void TestInit(Scenario scenario) throws Exception {
        System.out.println("\n");
        System.out.println("========(^.^) Execute "+ scenario.getName() + " in "+Env + " with " + browser +" on " + os+" (^.^)========");
        System.out.println("\n");
        switch (browser){
            case "chrome":
                ChromeDriverManager.getInstance().arch64().setup();
                ChromeOptions option = new ChromeOptions();
                Map<String, Object> preferences = new HashMap<String, Object>();
                option.addArguments(browser_size());
                if (mode.equals("headless")){
                option.addArguments("--headless");}
                base.driver = new ChromeDriver(option);
                break;
            case "firefox":
                FirefoxDriverManager.getInstance().arch64().setup();
                FirefoxOptions ffoption = new FirefoxOptions();
                ffoption.addArguments(browser_size());
                if (mode.equals("headless")){
                    ffoption.addArguments("--headless");}
                base.driver = new FirefoxDriver(ffoption);
                break;
            case "edge":
                EdgeDriverManager.getInstance().setup();
                base.driver = new EdgeDriver();
                break;
            case "remote-chrome":
                base.driver = RemoteChrome();
                break;
        }

    }
    @After
    public void endTestCase(Scenario result) throws URISyntaxException, IOException {
                if(result.isFailed()){
                    final byte[] screenshot = ((TakesScreenshot) base.driver).getScreenshotAs(OutputType.BYTES);
                    result.embed(screenshot, "image/png");
                }
                 System.out.println("\n");
                 System.out.println("******* Execute scenario "+ result.getName() + " done as :" + result.getStatus().toUpperCase()+" ********");
                 System.out.println("\n");
                 base.driver.quit();
            }

     String getEnvirOf(String keyword){
        Map<String, String> dot = DotEnv.load();
        return dot.get(keyword);
    }
    private RemoteWebDriver RemoteChrome() throws Exception {
        ChromeOptions option = new ChromeOptions();
        option.addArguments(browser_size());
        if (mode.equals("headless")) {
            option.addArguments("--headless");
        }
        return new RemoteWebDriver(new URL("http://localhost:35029/wd/hub"), option);
    }

}
