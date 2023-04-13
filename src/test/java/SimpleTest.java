import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SimpleTest {

    private static final String SELENOID_URL = "http://127.0.0.1:4444/wd/hub";
    public RemoteWebDriver driver;

    @BeforeEach
    public void beforeEachTest() throws MalformedURLException {
        DesiredCapabilities browser = new DesiredCapabilities();
        browser.setBrowserName("chrome");
        browser.setVersion("109.0");
        browser.setCapability("enableVNC", true);

        driver = new RemoteWebDriver(
                new URL(SELENOID_URL),
                browser
        );
    }

    @Test
    public void simpleTest() throws InterruptedException {
        driver.get("https://google.com");
        Thread.sleep(5000);
    }

    @AfterEach
    public void afterEachTest() {
        if (driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
