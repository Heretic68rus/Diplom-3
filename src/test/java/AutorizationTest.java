import PageObject.HomePageObject;
import PageObject.PersonalCabinetPageObject;
import PageObject.RecoverPasswordPageObject;
import PageObject.RegistrationPageObject;
import Rest.send.UserSend;
import Rest.step.UserSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AutorizationTest {
    private final String email = RandomStringUtils.randomAlphabetic(5) + "@mail.com";
    private final String password = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(5);
    private final String name = RandomStringUtils.randomAlphabetic(10);
    private WebDriver driver;
    private UserSteps userSteps;

    @Before
    public void setup() {
        switch (System.getProperty("browser")) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                this.driver = new ChromeDriver();
                break;
            case "yandex":
            default:
                WebDriverManager.chromedriver().setup();
                System.setProperty("webdriver.chrome.driver", "src/main/resources/yandexdriver.exe");
                this.driver = new ChromeDriver();
        }
        userSteps = new UserSteps(new UserSend());
        userSteps
                .createUser(email, password, name);
    }

    @Test
    public void autorizationThrougHomePageCompletedSuccessfully() {
        this.driver.get("https://stellarburgers.nomoreparties.site/");
        HomePageObject homePageObject = new HomePageObject(this.driver);
        PersonalCabinetPageObject personalCabinetPageObject = new PersonalCabinetPageObject(this.driver);
        homePageObject.clickLogInToAccountButton();
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization();
    }

    @Test
    public void autorizationThroughPersonalCabinetCompletedSuccessfully() {
        this.driver.get("https://stellarburgers.nomoreparties.site/");
        HomePageObject homePageObject = new HomePageObject(this.driver);
        PersonalCabinetPageObject personalCabinetPageObject = new PersonalCabinetPageObject(this.driver);
        homePageObject.clickPersonalCabinetButton();
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization();
    }

    @Test
    public void autorizationThroughRegistrationPageCompletedSuccessfully() {
        this.driver.get("https://stellarburgers.nomoreparties.site/");
        HomePageObject homePageObject = new HomePageObject(this.driver);
        PersonalCabinetPageObject personalCabinetPageObject = new PersonalCabinetPageObject(this.driver);
        RegistrationPageObject registrationPageObject = new RegistrationPageObject(this.driver);
        homePageObject.clickPersonalCabinetButton();
        personalCabinetPageObject.clickRegistrationButtonOnPersonalCabinet();
        registrationPageObject.clickEnterButtonOnRegistrationPage();
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization();
    }

    @Test
    public void autorizationThroughPasswordRecoverPageCompletedSuccessfully() {
        this.driver.get("https://stellarburgers.nomoreparties.site/");
        HomePageObject homePageObject = new HomePageObject(this.driver);
        PersonalCabinetPageObject personalCabinetPageObject = new PersonalCabinetPageObject(this.driver);
        RecoverPasswordPageObject recoverPasswordPageObject = new RecoverPasswordPageObject(this.driver);
        homePageObject.clickPersonalCabinetButton();
        personalCabinetPageObject.clickRecoverPasswordButtonOnPersonalCabinet();
        recoverPasswordPageObject.fillEmailFieldOnRecoverPasswordPage(email);
        recoverPasswordPageObject.clickEnterButtonOnRecoverPasswordPage();
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization();
    }

    @After
    public void teardown() {
        this.driver.quit();
    }

    public void deleteUser() {
        userSteps
                .deleteUser();
    }
}
