package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.WebElement;
import org.testng.annotations.*;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class AppiumMobileTest {
    AppiumDriverLocalService service;
    AndroidDriver driver;

    @BeforeTest
    public void sserverSetUp() throws MalformedURLException {
         service=new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
                .withAppiumJS(new File("C:\\Users\\sahil\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
                .withIPAddress("127.0.0.1").usingPort(4723).withArgument(GeneralServerFlag.BASEPATH, "/wd/hub").build();
        service.start();

        UiAutomator2Options options=new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setDeviceName("Android Emulator");
        options.setApp("D:\\Projects\\Appium Code\\APKFiles\\resources\\ApiDemos-debug.apk");
        //options.setApp("D:\\Projects\\Appium Code\\APKFiles\\resources\\General-Store.apk");
        URL url=new URL("http://127.0.0.1:4723/wd/hub");
        driver=new AndroidDriver(url,options);
    }

    @AfterTest
    public void tearDown(){
        //driver.quit();
        service.stop();
    }

    @Test
    public void mobileTest(){
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"App\"]")).click();

        TouchAction action=new TouchAction((AndroidDriver) driver);


    }
}
