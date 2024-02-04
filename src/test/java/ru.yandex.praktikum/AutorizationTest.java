package ru.yandex.praktikum;

import jdk.jfr.Description;
import ru.yandex.praktikum.page.object.HomePageObject;
import ru.yandex.praktikum.page.object.PersonalCabinetPageObject;
import ru.yandex.praktikum.page.object.RecoverPasswordPageObject;
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

public class AutorizationTest{
    private final String email = RandomStringUtils.randomAlphabetic(5) + "@mail.com";
    private final String password = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(5);
    private final String name = RandomStringUtils.randomAlphabetic(10);
    private WebDriver driver;
    private UserSteps userSteps;
    private HomePageObject homePageObject;
    private PersonalCabinetPageObject personalCabinetPageObject;
    private RegistrationPageObject registrationPageObject;
    private RecoverPasswordPageObject recoverPasswordPageObject;

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
        this.driver.get("https://stellarburgers.nomoreparties.site/");
        homePageObject = new HomePageObject(this.driver);
        personalCabinetPageObject = new PersonalCabinetPageObject(this.driver);
        registrationPageObject = new RegistrationPageObject(this.driver);
        recoverPasswordPageObject = new RecoverPasswordPageObject(this.driver);
        homePageObject.clickPersonalCabinetButton();
    }

    @Test
    @Description("Успешное авторизация через главную страницу")
    public void autorizationThroughHomePageCompletedSuccessfully() {
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization();
    }

    @Test
    @Description("Успешное авторизация через личный кабинет")
    public void autorizationThroughPersonalCabinetCompletedSuccessfully() {
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization();
    }

    @Test
    @Description("Успешное авторизация через страницу регистрации")
    public void autorizationThroughRegistrationPageCompletedSuccessfully() {
        personalCabinetPageObject.clickRegistrationButtonOnPersonalCabinet();
        registrationPageObject.clickEnterButtonOnRegistrationPage();
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization();
    }

    @Test
    @Description("Успешное авторизация через форму восстановления пароля")
    public void autorizationThroughPasswordRecoverPageCompletedSuccessfully() {
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
