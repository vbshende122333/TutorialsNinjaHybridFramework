package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class DriverManager {

    private static WebDriver driver;
    public static void initializeDriver(String browser){

        if(browser == null){
            driver = new ChromeDriver();
        }

        switch (browser.toLowerCase()){
            case "chrome":
                driver = new ChromeDriver();
                break;

            case "edge":
                driver = new EdgeDriver();
                break;

            case "firefox":
                driver = new FirefoxDriver();
                break;

            default:
                throw new IllegalArgumentException("Invalid Browser: " + browser);

        }
        driver.manage().window().maximize();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }
    public static WebDriver getDriver(){
        return driver;
    }

    public static void quitDriver(){
        driver.quit();
    }
}
