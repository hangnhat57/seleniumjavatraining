package Base;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.Random;

public class FuncModules {
    public  WebDriver driver;

    public FuncModules(WebDriver driver){
        this.driver=driver;
    }


    public void _HitEnter(){
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void _Click(WebElement element){
        _WaitFor(element);
        element.click();
    }

    public void _ClickNoWait(WebElement element){
        element.click();
    }

    public void _WaitUntillURL(String expect){
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.urlContains(expect));
    }

    public void _WaitUntillURL(String expect, int timeout){
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.urlContains(expect));
    }

    public void _AlertHandle(String ok_or_cancel){
        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        if (ok_or_cancel.toUpperCase().equals("OK")||ok_or_cancel.toUpperCase().equals("ACCEPT")){
            alert.accept();}else {alert.dismiss();}
    }

    public void _JSClicker(WebElement element){
        _WaitFor(element);
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public boolean isNotPresent(WebElement element)  {
        try {element.click(); return false; }
        catch (NoSuchElementException | NullPointerException e) { return true; }
    }

    public void _WaitFor(WebElement element){
        _WaitForLoad();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        try {
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e){
            _WaitForLoad();
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        }
    }

    public void _WaitFor(WebElement element, int time){
        _WaitForLoad();
        WebDriverWait wait = new WebDriverWait(driver, time);
        try {
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));
        } catch (StaleElementReferenceException e){
            _WaitForLoad();
            wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOf(element));

        }
    }

    public void Hover(WebElement element){
        _WaitFor(element);
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }

    public void _WaitFor(By element){
        _WaitForLoad();
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.ignoring(StaleElementReferenceException.class).until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public void _WaitForLoad() {
        new WebDriverWait(driver, 30).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
    }

    public void _ClearandType(WebElement Element, String value){
        _WaitFor(Element);
        Actions act = new Actions(driver);
        act.moveToElement(Element).doubleClick().sendKeys(value).build().perform();
    }

    public void _DelayTime(){
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void _LeaveInputFieldBlank(WebElement element ){
        Actions builder = new Actions(driver);
        builder.click(element).build().perform();
    }

    public void _Doub_WaitFor(WebElement element) throws InterruptedException {
        try {
            _WaitFor(element);
        }
        catch (StaleElementReferenceException e){
            Thread.sleep(1000*60);
            _WaitFor(element);
        }
    }

    public void _MoveMouseToElement(WebElement element){
        _WaitFor(element);
        Actions act = new Actions(driver);
        act.moveToElement(element).build().perform();
    }

    public void _SwitchtoNextTab(){
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);}
    }

    public void _SwitchToFrame(WebElement iframe){
        _WaitFor(iframe);
        driver.switchTo().frame(iframe);
    }
    public void _SwitchToFrame(String id){
        driver.switchTo().frame(id);
    }

    public void isVisible(WebElement element){
        boolean flag;
        try {
            _WaitFor(element,30);
            flag=true;
        }catch(TimeoutException e){
            flag = false;
        }
        Assert.assertTrue("Element is not visible after 30s ",flag);
    }
}
