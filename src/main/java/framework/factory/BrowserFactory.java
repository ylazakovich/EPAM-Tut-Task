package framework.factory;

import framework.factory.users.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class BrowserFactory {
    private static final String CHROME = "chrome";
    private static final String FIREFOX = "firefox";
    private static final String IE = "internet explorer";

    private static final String operatingSystemName = System.getProperty("os.name");
    private static final String LINUX = "linux";

    private static final String PROPERTY_CHROME = "webdriver.chrome.driver";
    private static final String PROPERTY_FIREFOX = "webdriver.gecko.driver";
    private static final String PROPERTY_IE = "webdriver.ie.driver";

    private static final String DRIVER_CHROME = "chromedriver";
    private static final String DRIVER_FIREFOX = "geckodriver";
    private static final String DRIVER_IE = "IEDriverServer";
    private static String driverPath = "src/main/resources/";

    private static BrowserFactory instance;
    private static WebDriver driver;

    private final static List<User> userList = DataStash.getUserList();

    private final WebDriverManager webDriverManager = new WebDriverManager();

    private static final String browserName = PropertyReader.getProperty("browserName");
    private static final String url = PropertyReader.getProperty("url");


    private BrowserFactory() throws IOException {
        driverPath = new File(driverPath).getCanonicalPath();
        initBrowser(browserName);
        webDriverManager.openUrl(driver, url);
    }

    public static BrowserFactory getInstance() {
        try {
            if (instance == null) {
                instance = new BrowserFactory();
            }
        } catch (IllegalStateException e) {
            System.err.println("The driver executable does not exist at " + driverPath);
        } catch (IOException ex) {
            System.err.println("Could not open browser");
        }
        return instance;
    }

    protected static String getDriverPath() {
        return driverPath;
    }

    private static String initOS(String operatingSystemName) {
        System.out.println("Current Operating System: " + System.getProperties().getProperty("os.name"));
        return operatingSystemName.toLowerCase().equals(LINUX) ? "" : ".exe";
    }

    private void SetPropertyBrowser(String prop, String driverName) {
        System.setProperty(prop, Paths.get(driverPath, driverName.concat(initOS(operatingSystemName))).toString());
    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverManager getWebDriverManager() {
        return webDriverManager;
    }

    private void initBrowser(String browserName) {
        System.out.println("Current Browser Selection: " + browserName);
        switch (browserName.toLowerCase()) {
            case CHROME:
                SetPropertyBrowser(PROPERTY_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver(CapabilityGenerator.capabilityForChrome());
                break;
            case FIREFOX:
                SetPropertyBrowser(PROPERTY_FIREFOX, DRIVER_FIREFOX);
                driver = new FirefoxDriver(CapabilityGenerator.capabilityForFirefox());
                break;
            case IE:
                SetPropertyBrowser(PROPERTY_IE, DRIVER_IE);
                driver = new InternetExplorerDriver(CapabilityGenerator.capabilityForIE());
                break;
            default:
                SetPropertyBrowser(PROPERTY_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver();

        }
    }

    public static List<User> getUserList() {
        return userList;
    }
}
