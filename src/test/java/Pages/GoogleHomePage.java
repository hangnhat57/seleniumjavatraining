package Pages;

import Base.BasePOM;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class GoogleHomePage extends BasePOM {
    public GoogleHomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(name ="q") private WebElement input_seach;

    public void SearchForKeyword(String keyword){
        _WaitFor(input_seach);
        input_seach.sendKeys("facebook");
        _HitEnter();
    }

}
