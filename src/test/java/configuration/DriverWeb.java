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
         */
        public DriverWeb() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            //ChromeDriver driver = new ChromeDriver(options);
            WebDriverManager.chromedriver().setup();
            this.driver = new ChromeDriver(options);
        }

        /**
         * Allows to get the Web Driver.
         * @return WebDriver
         */
        public WebDriver getDriver() {
            return this.driver;
        }
}
