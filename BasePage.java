package page.objects;

import helper.ClickHelper;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashMap;
import java.util.Map;

public class BasePage {
    public static WebDriver driver;
    public static WebDriverWait waitt;
    public static JavascriptExecutor js;
    public static Map<String, String> globalVariables = new HashMap<>();
    ClickHelper clickHelper;

    public BasePage() {
        clickHelper = new ClickHelper();
        PageFactory.initElements(getDriver(), this);
    }

    public void initital() {

        System.setProperty("webdriver.chrome.driver", "/Users/lukaszpydanowski/seleniumintro/chromedriver");
        driver = new ChromeDriver();
        waitt = new WebDriverWait(driver, 30);
        js = (JavascriptExecutor) driver;
        driver.manage().window().setSize(new Dimension(1920, 1080));
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");


    }

    public static void addToVariable(String name, String value) {
        globalVariables.put(name, value);
    }

    public static String getVariable(String name) {
        return globalVariables.get(name);
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void disposeDriver() {
        driver.close();
        driver.quit();
        driver = null;
    }
}
