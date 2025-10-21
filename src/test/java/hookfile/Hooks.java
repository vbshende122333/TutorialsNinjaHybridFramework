package hookfile;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.CommonUtils;
import utils.ConfigReader;
import utils.DriverManager;

import java.time.Duration;
import java.util.Properties;

public class Hooks {

    private WebDriver driver;
    private ConfigReader configReader;
    private CommonUtils commonUtils;
    private boolean isScenarioLogged = false;

    @Before
    public void setUp(){
        configReader = new ConfigReader();
        Properties prop = configReader.initializeProperties();
        // Pick browser per thread automatically
        String[] browsers = System.getProperty("browsers", "chrome,edge").split(",");
        int threadIndex = (int) (Thread.currentThread().getId() % browsers.length);
        String browser = browsers[threadIndex];
        DriverManager.initializeDriver(browser);
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(prop.getProperty("url"));
        commonUtils = new CommonUtils(driver);

    }

    @AfterStep
    public void afterEachStep(Scenario scenario){
        if (driver != null) {
            String timeStamp = commonUtils.getTimeStamp();

            // Log scenario name only once
            if (!isScenarioLogged) {
                System.out.println(scenario.getName() + " " + timeStamp);
                isScenarioLogged = true;
            }

            // Log step name with timestamp
            // scenario.getName() returns scenario name, for step name you may need to pass step info manually or use cucumber plugin
            // Here we log step keyword + step text as available in scenario (simplified)
//            System.out.println(scenario.getStatus() + " " + timeStamp);
            // Capture screenshot bytes
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getName() + "_" + timeStamp);
            } catch (Exception e) {
                System.out.println("Screenshot capture failed: " + e.getMessage());
            }

        }
    }
    @After
    public void tearDown(Scenario scenario){
        isScenarioLogged = false;
        String scenarioName = scenario.getName().replace(" ", "_");
        if(scenario.isFailed()){
            byte[] srcScreenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
            scenario.attach(srcScreenshot, "image/png", scenarioName);
        }
        DriverManager.quitDriver();

    }
}
