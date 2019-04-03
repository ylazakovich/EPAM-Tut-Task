package framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

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
    private final PropertyReader reader = PropertyReader.getInstance();
    private final DriverManager manager = new DriverManager();


    private BrowserFactory() throws IOException {
        driverPath = new File(driverPath).getCanonicalPath();
        initBrowser(reader.getBrowserName());
        manager.maximize(driver);
        manager.openUrl(driver, reader.getUrl());
    }

    public static BrowserFactory getInstance() {
        try {
            if (instance == null) {
                instance = new BrowserFactory();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver cannot be found");
        } catch (IOException ex) {
            System.out.println("Could not open browser");
        }
        return instance;
    }

    private void initBrowser(String browserName) {
        System.out.println("Current Browser Selection: " + browserName);
        switch (browserName.toLowerCase()) {
            case CHROME:
                SetPropertyBrowser(PROPERTY_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver(capabilityForChrome());
                break;
            case FIREFOX:
                SetPropertyBrowser(PROPERTY_FIREFOX, DRIVER_FIREFOX);
                driver = new FirefoxDriver(capabilityForFirefox());
                break;
            case IE:
                SetPropertyBrowser(PROPERTY_IE, DRIVER_IE);
                driver = new InternetExplorerDriver(capabilityForIE());
                break;
            default:
                SetPropertyBrowser(PROPERTY_CHROME, DRIVER_CHROME);
                driver = new ChromeDriver();

        }
    }

    private static String initOS(String operatingSystemName) {
        System.out.println("Current Operating System: " + System.getProperties().getProperty("os.name"));
        return operatingSystemName.toLowerCase().equals(LINUX) ? "" : ".exe";
    }

    private void SetPropertyBrowser(String prop, String driverName) {
        System.setProperty(prop, Paths.get(driverPath, driverName.concat(initOS(operatingSystemName))).toString());
    }

    private ChromeOptions capabilityForChrome() {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.notifications", 1);
        prefs.put("safebrowsing.enabled", "true");
        prefs.put("download.default_directory", driverPath);
        cap.setCapability("prefs", prefs);
        ChromeOptions options = new ChromeOptions();
        return options.merge(cap);
    }

    private InternetExplorerOptions capabilityForIE() {
        DesiredCapabilities cap = DesiredCapabilities.internetExplorer();
        cap.setCapability("nativeEvents", false);
        cap.setCapability("unexpectedAlertBehaviour", "accept");
        cap.setCapability("ignoreProtectedModeSettings", true);
        cap.setCapability("disable-popup-blocking", true);
        cap.setCapability("enablePersistentHover", true);
        cap.setCapability("ignoreZoomSetting", true);
        cap.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        InternetExplorerOptions options = new InternetExplorerOptions();
        return options.merge(cap);
    }

    private FirefoxOptions capabilityForFirefox() {
        FirefoxProfile prof = new FirefoxProfile();
        prof.setPreference("ignoreProtectedModeSettings", false);
        prof.setPreference("browser.download.folderList", 2);
        prof.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package, application/octet-stream");
        FirefoxOptions option = new FirefoxOptions();
        return option.setProfile(prof);
    }

    public WebDriver getDriver() {
        return driver;
    }

    public DriverManager getManager() {
        return manager;
    }
}
