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
         * Supports CI/CD environments with automatic configuration.
         */
        public DriverWeb() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            
            // Detect if running in CI/CD environment
            boolean isCI = isRunningInCI();
            
            // Configure options based on environment
            if (isCI) {
                // CI environment configuration
                options.addArguments("--no-sandbox");
                options.addArguments("--disable-dev-shm-usage");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920,1080");
                options.addArguments("--disable-software-rasterizer");
                options.addArguments("--disable-extensions");
                options.addArguments("--disable-default-apps");
                
                // Try to use system Chrome executable
                String chromeExecutable = findChromeExecutable();
                if (chromeExecutable != null) {
                    options.setBinary(chromeExecutable);
                    System.out.println("Using Chrome executable: " + chromeExecutable);
                }
            }
            
            // Setup driver
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver(options);
        }

        /**
         * Check if running in CI environment
         */
        private boolean isRunningInCI() {
            return System.getenv("CI") != null || 
                   System.getenv("GITHUB_ACTIONS") != null ||
                   System.getenv("JENKINS_HOME") != null ||
                   System.getenv("GITLAB_CI") != null ||
                   System.getenv("CIRCLECI") != null;
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
                "/snap/bin/chromium",
                "/opt/google/chrome/chrome"
            };
            
            for (String path : possiblePaths) {
                java.io.File file = new java.io.File(path);
                if (file.exists()) {
                    return path;
                }
            }
            
            // If not found, return null and let WebDriverManager handle it
            System.out.println("Chrome executable not found in common locations. Using WebDriverManager.");
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
