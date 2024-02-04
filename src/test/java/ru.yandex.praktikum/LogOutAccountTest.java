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

public class LogOutAccountTest {
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
    @Description("Выход из аккаунта")
    public void LogOutAccountCompletedSuccessfully() {
        this.driver.get("https://stellarburgers.nomoreparties.site/");
        HomePageObject homePageObject = new HomePageObject(this.driver);
        PersonalCabinetPageObject personalCabinetPageObject = new PersonalCabinetPageObject(this.driver);
        homePageObject.clickLogInToAccountButton();
        personalCabinetPageObject.fillEmailFieldOnPersonalCabinet(email);
        personalCabinetPageObject.fillPasswordFieldOnPersonalCabinet(password);
        personalCabinetPageObject.clickEnterButtonOnPersonalCabinet();
        homePageObject.clickPersonalCabinetButton();
        personalCabinetPageObject.clickLogOutFromAccountButtonOnPersonalCabinet();
        personalCabinetPageObject.waitForLoadHeaderEnterOnPersonalCabinetToConfirmSuccessfulLogOutAccount();
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
