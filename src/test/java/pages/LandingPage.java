package pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by nishant on 01/04/17.
 */
public class LandingPage extends BasePage {
    AppiumDriver driver;

    @FindBy(id = "skip")
    private WebElement skipLink;

    @FindBy(id = "login_register_view")
    private WebElement mobileOrEmailField;

    @FindBy(id = "continue_login")
    private WebElement continueButton;

    @FindBy(id = "fb")
    private WebElement fbButton;

    @FindBy(id = "sign_in_button")
    private WebElement googleButton;

    public LandingPage(AppiumDriver appiumDriver) throws Exception {
        super(appiumDriver);
        this.driver = appiumDriver;
        PageFactory.initElements(driver, this);
    }

    public void skipToHomePage() {
        waitForElementToBeVisible(skipLink);
        skipLink.click();
    }

    public void registerByMobileOrEmail(String mobileorEmail) {
        mobileOrEmailField.sendKeys(mobileorEmail);
        continueButton.click();
    }

    public void signInByFacebook() {
        fbButton.click();
    }

    public void signInByGoogle() {
        googleButton.click();
    }
}