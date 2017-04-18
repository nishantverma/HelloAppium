package steps;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;
import java.util.List;

public class HomePageSteps {

    private AppiumDriver appiumDriver;

    //    @When("^I launch Quikr app$")
    public void iLaunchQuikrApp() throws Throwable {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("deviceName", "Nexus");
        capabilities.setCapability("newCommandTimeout", 120);

        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("app", "/Users/nishant/Development/HelloAppium/app/quikr.apk");

//        capabilities.setCapability("appPackage", "com.quikr");
//        capabilities.setCapability("appActivity", "com.quikr.old.SplashActivity");

        appiumDriver = new AppiumDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    //    @And("^I choose to log in using Google$")
    public void iChooseToLogInUsingGoogle() throws Throwable {
        appiumDriver.findElement(By.id("sign_in_button")).click();
    }

    //    @Then("^I see account picker screen with my email address \"([^\"]*)\"$")
    public void iSeeAccountPickerScreenWithMyEmailAddress(String expected) throws Throwable {
        Assert.assertEquals("Email Id matches", expected, appiumDriver.findElement(By.id("com.google.android.gms:id/account_name")).getText());
    }


    //    @And("^I choose \"([^\"]*)\" as my city$")
    public void iChooseAsMyCity(String city) throws Throwable {
        appiumDriver.findElement(By.id("skip")).click();

        try {
            if (appiumDriver.findElement(By.xpath("//android.widget.Button[@text='Later']")).isDisplayed())
                appiumDriver.findElement(By.xpath("//android.widget.Button[@text='Later']")).click();
        } catch (Exception e) {
            //Do nothing
        }

        appiumDriver.findElement(By.id("citySpinner")).click();

        appiumDriver.findElement(By.id("search_ET")).click();
        appiumDriver.findElement(By.id("search_ET")).sendKeys(city);

        appiumDriver.findElement(By.id("city_name")).click();
    }

    //    @And("^I search for \"([^\"]*)\" under Used Cars$")
    public void iSearchForUnderUsedCars(String carName) throws Throwable {
        appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='Cars']")).click();
        appiumDriver.findElement(By.xpath("//android.widget.TextView[@text='Cars']")).click();
        appiumDriver.findElement(By.id("cnb_hp_choose_et")).click();
        appiumDriver.findElement(By.id("cnb_search_text_et")).sendKeys(carName);

        List<WebElement> results = appiumDriver.findElements(By.id("text1"));
        for (WebElement result : results) {
            if (result.getText().contains(carName)) {
                result.click();
                break;
            }
        }
        appiumDriver.findElement(By.id("cnb_search_button")).click();
    }

//    @Then("^I should see the first car search result with \"([^\"]*)\"$")
    public void iShouldSeeTheFirstCarSearchResultWith(String arg0) throws Throwable {
        List<WebElement> elements = appiumDriver.findElements(By.id("cars_ad_list_title_tv"));
        Assert.assertTrue("Verified first result contains Honda",elements.get(0).getText().startsWith(arg0));
    }

}


















