package steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

import java.io.File;
import java.io.IOException;

/**
 * Created by nishant on 23/01/17.
 */
public class StartingSteps {

    private AppiumDriverLocalService appiumService;

    @Before
    public void startAppiumServer() throws IOException {

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
    public void killAppiumServer() {
        appiumService.stop();
    }


}
