package steps;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by nishant on 23/01/17.
 */
public class StartingSteps extends BaseSteps {

    private AppiumDriverLocalService appiumService;

    @Before
    public void setup() throws IOException {

        startAppiumService();

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("platformVersion", "7.0");
        capabilities.setCapability("deviceName", "Nexus");

        capabilities.setCapability("fullReset", true);
        capabilities.setCapability("noReset", false);
        capabilities.setCapability("app", "/Users/nishant/Development/HelloAppium/app/quikr.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    private void startAppiumService() {
        int port = 4723;
        String nodeJS_Path = "C:/Program Files/Appium/node.exe";
        String appiumJS_Path = "C:/Program Files/Appium/node_modules/appium/bin/appium.js";
        String udid = null;

        String osName = System.getProperty("os.name");

        if (osName.contains("Mac")) {
            appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(("/usr/local/bin/node")))
                    .withAppiumJS(new File(("/usr/local/bin/appium")))
                    .withIPAddress("0.0.0.0")
                    .usingPort(port)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withLogFile(new File("build/appium.log")));
        } else if (osName.contains("Windows")) {
            appiumService = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                    .usingDriverExecutable(new File(nodeJS_Path))
                    .withAppiumJS(new File(appiumJS_Path))
                    .withIPAddress("0.0.0.0")
                    .usingPort(port)
                    .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                    .withArgument(GeneralServerFlag.ROBOT_ADDRESS, udid)
                    .withLogFile(new File("build/appium.log")));
        }
        appiumService.start();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                final byte[] screenshot = driver
                        .getScreenshotAs(OutputType.BYTES);
                scenario.embed(screenshot, "image/png");
            }
            driver.quit();
        } catch (Exception e) {
            System.out.println("Exception while running Tear down :" + e.getMessage());
        }

    }
}
