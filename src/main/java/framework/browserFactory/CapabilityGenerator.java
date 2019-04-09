package framework.browserFactory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;
import java.util.Map;

public class CapabilityGenerator {

    protected static ChromeOptions getChromeCapability() {
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_settings.popups", 0);
        prefs.put("profile.default_content_setting_values.notifications", 1);
        prefs.put("safebrowsing.enabled", "true");
        prefs.put("download.default_directory", BrowserFactory.getDriverPath());
        cap.setCapability("prefs", prefs);
        ChromeOptions options = new ChromeOptions();
        return options.merge(cap);
    }

    protected static InternetExplorerOptions getIECapability() {
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

    protected static FirefoxOptions getFirefoxCapability() {
        FirefoxProfile prof = new FirefoxProfile();
        prof.setPreference("ignoreProtectedModeSettings", false);
        prof.setPreference("browser.download.folderList", 2);
        prof.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/x-debian-package, application/octet-stream");
        FirefoxOptions option = new FirefoxOptions();
        return option.setProfile(prof);
    }

}
