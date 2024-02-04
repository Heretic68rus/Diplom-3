package ru.yandex.praktikum;

import jdk.jfr.Description;
import ru.yandex.praktikum.page.object.HomePageObject;
import ru.yandex.praktikum.page.object.PersonalCabinetPageObject;
import ru.yandex.praktikum.page.object.RegistrationPageObject;
import ru.yandex.praktikum.rest.send.UserSend;
import ru.yandex.praktikum.rest.step.UserSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RegistrationTest {
    private final String name = RandomStringUtils.randomAlphabetic(5);
    private final String email = RandomStringUtils.randomAlphabetic(5) + "@mail.com";
    private WebDriver driver;
    private UserSteps userSteps;
    private HomePageObject homePageObject;
    private PersonalCabinetPageObject personalCabinetPageObject;
    private RegistrationPageObject registrationPageObject;

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
        this.driver.get("https://stellarburgers.nomoreparties.site/");
        homePageObject = new HomePageObject(this.driver);
        personalCabinetPageObject = new PersonalCabinetPageObject(this.driver);
        registrationPageObject = new RegistrationPageObject(this.driver);
        homePageObject.clickLogInToAccountButton();
        personalCabinetPageObject.clickRegistrationButtonOnPersonalCabinet();
        registrationPageObject.waitForLoadNameFieldOnRegistrationPage();
        registrationPageObject.fillNameFieldOnRegistrationPage(name);
        registrationPageObject.fillEmailFieldOnRegistrationPage(email);
    }

    @Test
    @Description("Успешная регистрация")
    public void registrationIsSuccess() {
        String password = RandomStringUtils.randomAlphabetic(3) + RandomStringUtils.randomNumeric(3);
        registrationPageObject.fillPasswordFieldOnRegistrationPage(password);
        registrationPageObject.clickRegistrationButtonOnRegistrationPage();
        personalCabinetPageObject.waitForLoadHeaderEnterOnPersonalCabinetToConfirmSuccessfulRegistration();
    }

    @Test
    @Description("Регистрация с паролем меньше 6 знаков")
    public void registrationNotCompletedWithPasswordLessThenSixCharacters() {
        String password = RandomStringUtils.randomAlphabetic(2) + RandomStringUtils.randomNumeric(3);
        registrationPageObject.fillPasswordFieldOnRegistrationPage(password);
        registrationPageObject.clickRegistrationButtonOnRegistrationPage();
        registrationPageObject.waitForIncorrectPasswordTextOnRegistrationPageWhenEnteringPasswordLessThanSixCharacters();
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
