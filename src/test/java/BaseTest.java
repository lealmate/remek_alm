import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.example.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BaseTest {

    WebDriver driver;
    WebDriverWait wait;
    LandingPage landingPage;
    BlogPage blogPage;
    ContactPage contactPage;
    ProfilePage profilePage;
    RecentWorksPage recentWorksPage;
    SigningPage signingPage;
    Actions action;
    static Map<String, String> CREDENTIALS = new HashMap<>();

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("ignore-certificate-errors");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-extensions");
        options.addArguments("--headless");
        options.addArguments("--window-size=1920,1080");
        options.addArguments("start-maximized");
        options.addArguments("--remote-allow-origins=*");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        landingPage = new LandingPage(driver);
        blogPage = new BlogPage(driver);
        contactPage = new ContactPage(driver);
        profilePage = new ProfilePage(driver);
        recentWorksPage = new RecentWorksPage(driver);
        signingPage = new SigningPage(driver);
        action = new Actions(driver);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @BeforeAll
    public static void beforeAll() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(new FileReader("src/main/resources/users.json"));

        JSONArray solutions = (JSONArray) obj;

        for(Object solution: solutions){
            String username = ((JSONObject) solution).get("login").toString();
            String password = ((JSONObject) solution).get("password").toString();

            CREDENTIALS.put(username, password);
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public void screenshot() {
        Allure.addAttachment("Screenshot", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
    }

    @AfterEach
    public void quit(){
        driver.quit();
    }
}
