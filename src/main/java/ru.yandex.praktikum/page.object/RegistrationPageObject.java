package ru.yandex.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegistrationPageObject {
    private final By nameFieldOnRegistrationPage = By.xpath(".//form[@class='Auth_form__3qKeq mb-20']/fieldset[1]//input[@class='text input__textfield text_type_main-default' and @name='name']");//локатор поля Имя
    private final By emailFieldOnRegistrationPage = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='name']");//локатор поля Email
    private final By passwordFieldOnRegistrationPage = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");//локатор поля Пароль
    private final By enterButtonOnRegistrationPage = By.xpath(".//a[text()='Войти']");//локатор кнопки Войти
    private final By registrationButtonOnRegistrationPage = By.xpath(".//button[text()='Зарегистрироваться']");//локатор кнопки Зарегистрироваться
    private final By incorrectPasswordTextOnRegistrationPage = By.xpath(".//p[text()='Некорректный пароль']");//локатор надписи Некорректный пароль
    private final WebDriver driver;


    public RegistrationPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("wait for load name field on registration page")
    public void waitForLoadNameFieldOnRegistrationPage() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(nameFieldOnRegistrationPage));
    }

    @Step("fill email field on registration page")
    public void fillEmailFieldOnRegistrationPage(String email) {
        this.driver.findElement(this.emailFieldOnRegistrationPage).sendKeys(email);
    }

    @Step("fill password field on registration page")
    public void fillPasswordFieldOnRegistrationPage(String password) {
        this.driver.findElement(this.passwordFieldOnRegistrationPage).sendKeys(password);
    }

    @Step("fill name field on registration page")
    public void fillNameFieldOnRegistrationPage(String name) {
        this.driver.findElement(this.nameFieldOnRegistrationPage).sendKeys(name);
    }

    @Step("click enter button on registration page")
    public void clickEnterButtonOnRegistrationPage() {
        this.driver.findElement(this.enterButtonOnRegistrationPage).click();
    }

    @Step("click registration button on registration page")
    public void clickRegistrationButtonOnRegistrationPage() {
        this.driver.findElement(this.registrationButtonOnRegistrationPage).click();
    }

    @Step("wait for incorrect password text on registration page when entering password less than six characters")
    public void waitForIncorrectPasswordTextOnRegistrationPageWhenEnteringPasswordLessThanSixCharacters() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(incorrectPasswordTextOnRegistrationPage));
    }
}
