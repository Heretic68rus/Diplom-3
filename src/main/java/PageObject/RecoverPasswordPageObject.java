package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPageObject {
    private final By emailFieldOnRecoverPasswordPage = By.xpath(".//input[@class='text input__textfield text_type_main-default' and @name='name']");//локатор поля Email
    private final By enterButtonOnRecoverPasswordPage = By.xpath(".//a[text()='Войти']");//локатор кнопки Войти
    private final WebDriver driver;

    public RecoverPasswordPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("fill email field on recover password page")
    public void fillEmailFieldOnRecoverPasswordPage(String email) {
        this.driver.findElement(this.emailFieldOnRecoverPasswordPage).sendKeys(email);
    }

    @Step("click enter button on recover password page")
    public void clickEnterButtonOnRecoverPasswordPage() {
        this.driver.findElement(this.enterButtonOnRecoverPasswordPage).click();
    }
}
