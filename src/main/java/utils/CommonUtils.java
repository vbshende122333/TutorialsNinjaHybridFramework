package utils;

import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CommonUtils {

    private List<String> stepLogs = new ArrayList<>();
    private WebDriver driver;

    public CommonUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Get timestamp
    public String getTimeStamp() {
        return java.time.LocalDateTime.now()
                .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
    }

    public void takeScreenShot(Scenario scenario, String stepName) {
        if (driver == null) return;

        String timeStamp = getTimeStamp().replace(":", "-"); // safe for filenames
        String fileName = stepName.replaceAll(" ", "_") + "_" + timeStamp + ".png";

        // Screenshot path
        String screenshotPath = System.getProperty("user.dir") + "/target/screenshots/" + fileName;

        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(srcFile, new File(screenshotPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Add step log to list for PDF generation
        stepLogs.add(stepName + " " + getTimeStamp() + " -> Screenshot: " + screenshotPath);
    }
    public List<String> getStepLogs() {
        return stepLogs;
    }

    // Clear logs after scenario
    public void clearStepLogs() {
        stepLogs.clear();
    }
}
