package pages;

import io.appium.java_client.AppiumDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by nishant on 01/04/17.
 */
public class BasePage {

    private AppiumDriver driver;
    private WebDriverWait wait;

    public BasePage(AppiumDriver driver) throws Exception {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, 30);
    }

    public void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void screenshot(String path_screenshot) throws IOException {
        File srcFile=driver.getScreenshotAs(OutputType.FILE);
        String filename= UUID.randomUUID().toString();
        File targetFile=new File(path_screenshot + filename +".jpg");
        FileUtils.copyFile(srcFile,targetFile);
    }


}
