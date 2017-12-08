package Pages;

import Base.BasePOM;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SelectmenuPage extends BasePOM {
    public SelectmenuPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(className = "demo-frame") private WebElement demoIframe;
    @FindBy(id="speed-button") private WebElement SpeedSelect;

    public void ClickSpeedSelect(){
        _SwitchToFrame(demoIframe);
        _Click(SpeedSelect);
    }

    public void SelectOption(String value){
        String xpath = "//li[@class='ui-menu-item' and contains(.,'"+ value+"')]";
        _WaitFor(By.xpath(xpath));
        driver.findElement(By.xpath(xpath)).click();
    }

    public void VerifySelectDisplay(String actual){
        Assert.assertTrue(SpeedSelect.getText().contains(actual));
    }
}
