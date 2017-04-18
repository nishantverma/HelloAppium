package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.CarResultsPage;
import pages.HomePage;
import pages.LandingPage;

import java.net.URL;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class HomePageStepsWithWait {

    private AppiumDriver appiumDriver;


    @When("^I launch Quikr app$")
    public void iLaunchQuikrApp() throws Throwable {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("deviceName", "Nexus");

        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("app", "/Users/nishant/Development/HelloAppium/app/quikr.apk");

        appiumDriver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        appiumDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        appiumDriver.rotate(ScreenOrientation.PORTRAIT);

    }

    @And("^I choose to log in using Google$")
    public void iChooseToLogInUsingGoogle() throws Throwable {
        appiumDriver.findElement(By.id("sign_in_button")).click();
    }

    @Then("^I see account picker screen with my email address \"([^\"]*)\"$")
    public void iSeeAccountPickerScreenWithMyEmailAddress(String expected) throws Throwable {
        Assert.assertEquals("Email Id matches", expected, appiumDriver.findElement(By.id("com.google.android.gms:id/account_name")).getText());
    }


    @And("^I choose \"([^\"]*)\" as my city$")
    public void iChooseAsMyCity(String city) throws Throwable {
        new LandingPage(appiumDriver).skipToHomePage();
        try {
            if (appiumDriver.findElement(By.xpath("//android.widget.Button[@text='LATER']")).isDisplayed())
                appiumDriver.findElement(By.xpath("//android.widget.Button[@text='LATER']")).click();
        } catch (Exception e) {
            //Do nothing
        }
        new HomePage(appiumDriver).selectCity(city);
    }

    @And("^I search for \"([^\"]*)\" under Used Cars$")
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

//    @Then("^I should see the result$")
//    public void iShouldSeeTheResult() throws Throwable {
//        new CarResultsPage(appiumDriver).verifySearchResult("Honda");
//    }

    @And("^I tap on \"([^\"]*)\"$")
    public void iTapOn(String arg0) throws Throwable {
        TouchAction action = new TouchAction(appiumDriver);

    }


    @Then("^I should see the first car search result with \"([^\"]*)\"$")
    public void iShouldSeeTheFirstCarSearchResultWith(String searchInput) throws Throwable {
        String searchResult = new CarResultsPage(appiumDriver).getFirstSearchResult();
        Assert.assertTrue(searchResult.startsWith(searchInput));
    }


}


















