package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CarResultsPage;
import pages.HomePage;
import pages.LandingPage;

import java.util.List;

public class HomePageStepsWithWait extends BaseSteps{


    @When("^I launch Quikr app$")
    public void iLaunchQuikrApp() throws Throwable {
        System.out.println("Hello World " + driver.getAutomationName());
    }

    @And("^I choose to log in using Google$")
    public void iChooseToLogInUsingGoogle() throws Throwable {
        driver.findElement(By.id("sign_in_button")).click();
    }

    @Then("^I see account picker screen with my email address \"([^\"]*)\"$")
    public void iSeeAccountPickerScreenWithMyEmailAddress(String expected) throws Throwable {
        Assert.assertEquals("Email Id matches", expected, driver.findElement(By.id("com.google.android.gms:id/account_name")).getText());
    }


    @And("^I choose \"([^\"]*)\" as my city$")
    public void iChooseAsMyCity(String city) throws Throwable {
        new LandingPage(driver).skipToHomePage();
        try {
            if (driver.findElement(By.xpath("//android.widget.Button[@text='LATER']")).isDisplayed())
                driver.findElement(By.xpath("//android.widget.Button[@text='LATER']")).click();
        } catch (Exception e) {
            //Do nothing
        }
        new HomePage(driver).selectCity(city);
    }

    @And("^I search for \"([^\"]*)\" under Used Cars$")
    public void iSearchForUnderUsedCars(String carName) throws Throwable {
        driver.findElement(By.xpath("//android.widget.TextView[@text='Cars']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@text='Cars']")).click();
        driver.findElement(By.id("cnb_hp_choose_et")).click();
        driver.findElement(By.id("cnb_search_text_et")).sendKeys(carName);

        List<WebElement> results = driver.findElements(By.id("text1"));
        for (WebElement result : results) {
            if (result.getText().contains(carName)) {
                result.click();
                break;
            }
        }
        driver.findElement(By.id("cnb_search_button")).click();
    }

//    @Then("^I should see the result$")
//    public void iShouldSeeTheResult() throws Throwable {
//        new CarResultsPage(appiumDriver).verifySearchResult("Honda");
//    }

    @And("^I tap on \"([^\"]*)\"$")
    public void iTapOn(String arg0) throws Throwable {
        TouchAction action = new TouchAction(driver);
    }

    @Then("^I should see the first car search result with \"([^\"]*)\"$")
    public void iShouldSeeTheFirstCarSearchResultWith(String searchInput) throws Throwable {
        String searchResult = new CarResultsPage(driver).getFirstSearchResult();
        Assert.assertTrue(searchResult.startsWith(searchInput));
    }


}


















