package ru.yandex.praktikum;

import jdk.jfr.Description;
import ru.yandex.praktikum.page.object.HomePageObject;
import ru.yandex.praktikum.page.object.PersonalCabinetPageObject;
import ru.yandex.praktikum.rest.send.UserSend;
import ru.yandex.praktikum.rest.step.UserSteps;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PageTransitionsTest {
    private final String email = RandomStringUtils.randomAlphabetic(5) + "@mail.com";
    private final String password = RandomStringUtils.randomAlphabetic(5) + RandomStringUtils.randomNumeric(5);
    private final String name = RandomStringUtils.randomAlphabetic(10);
    private WebDriver driver;
    private UserSteps userSteps;
    private HomePageObject homePageObject;
    private PersonalCabinetPageObject personalCabinetPageObject;

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
        homePageObject.clickLogInToAccountButton();
    }

    @Test
    @Description("Переход в личный кабинет")
    public void transitionToPersonalCabinetIsSuccessful() {
        personalCabinetPageObject.waitForLoadEnterButtonOnPersonalCabinetToConfirmSuccessfulTransitionToPersonalCabinet();
    }

    @Test
    @Description("Переход из личного кабинета в конструктор")
    public void transitionFromPersonalCabinetToMainPageThrowConstructorIsSuccessful() {
        homePageObject.clickLogoConstructor();
        homePageObject.waitForLoadLogInToAccountButtonAfterSuccessfulTransitionFromPersonalCabinetToHomePage();
    }

    @Test
    @Description("Переход на главную страницу через логотип сайта")
    public void transitionFromPersonalCabinetToMainPageThrowStellarBurgersLogoIsSuccessful() {
        homePageObject.clickLogoStellarBurgers();
        homePageObject.waitForLoadLogInToAccountButtonAfterSuccessfulTransitionFromPersonalCabinetToHomePage();
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

