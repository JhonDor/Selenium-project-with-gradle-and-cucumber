package configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


/**
 * This is the class for the Web Driver.
 */
public class DriverWeb {

        private final WebDriver driver;

        /**
         * Constructor method.
         * Only Chrome browser is defined.
         * Supports headless mode via environment detection for CI/CD environments.
         */
        public DriverWeb() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            
            // Detect if running in CI/CD environment (GitHub Actions, Jenkins, etc.)
            boolean isCI = System.getenv("CI") != null || 
                          System.getenv("GITHUB_ACTIONS") != null ||
                          System.getProperty("headlessMode") != null;
            
            // Enable headless mode for CI environments
            if (isCI) {
                options.addArguments("--headless=new");
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-software-rasterizer");
                options.addArguments("--disable-extensions");
                
                // Try to use system Chrome if available in CI environment
                String chromeExecutable = findChromeExecutable();
                if (chromeExecutable != null) {
                    options.setBinary(chromeExecutable);
                }
            }
            
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver(options);
        }

        /**
         * Find Chrome/Chromium executable in the system
         */
        private String findChromeExecutable() {
            String[] possiblePaths = {
                "/usr/bin/google-chrome",
                "/usr/bin/google-chrome-stable",
                "/usr/bin/chromium",
                "/usr/bin/chromium-browser",
                "/snap/bin/chromium"
            };
            
            for (String path : possiblePaths) {
                java.io.File file = new java.io.File(path);
                if (file.exists()) {
                    System.out.println("Found Chrome executable: " + path);
                    return path;
                }
            }
            
            return null;
        }

        /**
         * Allows to get the Web Driver.
         * @return WebDriver
         */
        public WebDriver getDriver() {
            return this.driver;
        }
}
