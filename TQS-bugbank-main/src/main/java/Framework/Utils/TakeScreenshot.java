package Framework.Utils;

import io.qameta.allure.Allure;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class TakeScreenshot {

    public static void attachPageScreenshot(WebDriver webDriver, String name) {
        byte[] screenshotBytes = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BYTES);
        Allure.attachment(name, new ByteArrayInputStream(screenshotBytes));
    }

    public static void saveScreenshotFile(WebDriver driver, String filename) {
        try {
            File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("target/screenshots/" + filename + ".png");
            
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("Screenshot salvo em: " + destFile.getAbsolutePath());
            
        } catch (IOException e) {
            System.err.println("Erro ao salvar o screenshot em arquivo: " + e.getMessage());
        }
    }
}
