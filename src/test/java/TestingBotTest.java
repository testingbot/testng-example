import com.testingbot.testng.TestingBotAuthenticationProvider;
import com.testingbot.testng.TestingBotSessionIdProvider;
import com.testingbot.testng.TestingBotTestListener;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners({TestingBotTestListener.class})
public class TestingBotTest implements TestingBotSessionIdProvider, TestingBotAuthenticationProvider {
    
    public String getTestingBotKey() {
        return "key";
    }

    public String getTestingBotSecret() {
        return "secret";
    }

    private WebDriver driver;

    @BeforeClass
    public void setUp() throws Exception {
        DesiredCapabilities capabillities = DesiredCapabilities.firefox();
        capabillities.setCapability("version", "45");
        capabillities.setCapability("platform", Platform.WINDOWS);
        capabillities.setCapability("name", "Testing Selenium 2");

        driver = new RemoteWebDriver(
                new URL("http://" + getTestingBotKey() + ":" + getTestingBotSecret() + "@hub.testingbot.com/wd/hub"),capabillities);
    }

    @Test
    public void testSimple() throws Exception {
        driver.get("https://testingbot.com/");
        String searchHeader = driver.findElement(By.cssSelector("H1"))
                .getText().toLowerCase();

        Assert.assertTrue(searchHeader.contains("Browser Testing"));
    }
    
    public String getSessionId() {
        return ((RemoteWebDriver)driver).getSessionId().toString();
    }

    @AfterClass
    public void tearDown() throws Exception {
        driver.quit();
    }
}
