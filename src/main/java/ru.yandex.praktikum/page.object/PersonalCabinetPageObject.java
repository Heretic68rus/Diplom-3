package ru.yandex.praktikum.page.object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PersonalCabinetPageObject {
    private final By emailFieldOnPersonalCabinet = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='name']");//локатор поля Email
    private final By passwordFieldOnPersonalCabinet = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='Пароль']");//локатор поля Пароль
    private final By enterButtonOnPersonalCabinet = By.xpath(".//button[text()='Войти']");//локатор кнопки Войти
    private final By registrationButtonOnPersonalCabinet = By.xpath(".//a[text()='Зарегистрироваться']");//локатор кнопки Зарегистрироваться
    private final By recoverPasswordButtonOnPersonalCabinet = By.xpath(".//a[text()='Восстановить пароль']");//локатор кнопки Восстановить пароль

    private final By headerEnterOnPersonalCabinet = By.xpath(".//div[@class='Auth_login__3hAey']/*[self::h2]");//локатор заголовка Вход
    private final By logOutFromAccountButton = By.xpath(".//button[text()='Выход']");//локатор кнопки Выход
    private final WebDriver driver;


    public PersonalCabinetPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("wait for load header enter on personal cabinet to confirm successful registration")
    public void waitForLoadHeaderEnterOnPersonalCabinetToConfirmSuccessfulRegistration() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(headerEnterOnPersonalCabinet));
    }

    @Step("fill email field on personal cabinet")
    public void fillEmailFieldOnPersonalCabinet(String email) {
        this.driver.findElement(this.emailFieldOnPersonalCabinet).sendKeys(email);
    }

    @Step("fill password field on personal cabinet")
    public void fillPasswordFieldOnPersonalCabinet(String password) {
        this.driver.findElement(this.passwordFieldOnPersonalCabinet).sendKeys(password);
    }

    @Step("click enter button on personal cabinet")
    public void clickEnterButtonOnPersonalCabinet() {
        this.driver.findElement(this.enterButtonOnPersonalCabinet).click();
    }

    @Step("click registration button on personal cabinet")
    public void clickRegistrationButtonOnPersonalCabinet() {
        this.driver.findElement(this.registrationButtonOnPersonalCabinet).click();
    }

    @Step("click recover password button on personal cabinet")
    public void clickRecoverPasswordButtonOnPersonalCabinet() {
        this.driver.findElement(this.recoverPasswordButtonOnPersonalCabinet).click();
    }

    @Step("wait for load enter button on personal cabinet to confirm successful transition to personal cabinet")
    public void waitForLoadEnterButtonOnPersonalCabinetToConfirmSuccessfulTransitionToPersonalCabinet() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(enterButtonOnPersonalCabinet));
    }

    @Step("click log out from account button on personal cabinet")
    public void clickLogOutFromAccountButtonOnPersonalCabinet() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.elementToBeClickable(logOutFromAccountButton));
        this.driver.findElement(this.logOutFromAccountButton).click();
    }

    @Step("wait for load header enter on personal cabinet to confirm successful log out account")
    public void waitForLoadHeaderEnterOnPersonalCabinetToConfirmSuccessfulLogOutAccount() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(headerEnterOnPersonalCabinet));
    }
}
