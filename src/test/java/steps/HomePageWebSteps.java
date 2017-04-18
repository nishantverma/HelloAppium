package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by nishant on 20/01/17.
 */
public class HomePageWebSteps {
    private AppiumDriver appiumDriver;

    @When("^I launch Quikr mobile web$")
    public void iLaunchQuikrMobileWeb() throws Throwable {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("deviceName", "Nexus");
        desiredCapabilities.setCapability("browserName", "Browser");

        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        appiumDriver = new AppiumDriver(url, desiredCapabilities);
        appiumDriver.get("http://m.quikr.com");
        appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @And("^I choose to register$")
    public void iChooseToRegister() throws Throwable {
        appiumDriver.findElement(By.id("hamburger")).click();

        appiumDriver.findElement(By.id("hamLoginLink")).click();

    }

    @Then("^I should see an option to register using Facebook$")
    public void iShouldSeeAnOptionToRegisterUsingFacebook() throws Throwable {
        appiumDriver.findElement(By.partialLinkText("Register")).click();

        WebDriverWait wait = new WebDriverWait(appiumDriver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("icon-facebook")));

        Assert.assertTrue(appiumDriver.findElement(By.className("icon-facebook")).isDisplayed());

    }
}
