package PageObject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePageObject {
    private final By personalCabinetButton = By.xpath(".//p[text()='Личный Кабинет']");//локатор кнопки Личный кабинет
    private final By logInToAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");//локатор кнопки Войти в аккаунт
    private final By logoStellarBurgers = By.xpath("//*[local-name()='svg' and @width= '290']");//локатор логотипа StellarBurgers
    private final By logoConstructor = By.xpath(".//p[text()='Конструктор']");//локатор конструктора
    private final By bunsButton = By.xpath(".//span[text()='Булки']");//локатор кнопки Булки в конструкторе
    private final By sauceButton = By.xpath(".//span[text()='Соусы']");//локатор кнопки Соусы в конструкторе
    private final By fillingsButton = By.xpath(".//span[text()='Начинки']");//локатор кнопки Начинки в конструкторе
    private final By firstBurgerIngredientImage = By.xpath(".//img[contains(@alt, 'Флюоресцентная булка R2-D3')]");//локатор иконки булки Флюоресцентная булка R2-D3
    private final By firstSauceIngredientImage = By.xpath(".//img[contains(@alt, 'Соус Spicy-X')]");//локатор иконки соуса Соус Spicy-X
    private final By firstFillingsIngredientImage = By.xpath(".//img[contains(@alt, 'Мясо бессмертных моллюсков Protostomia')]");//локатор иконки начинки Мясо бессмертных моллюсков Protostomia
    private final By placeAnOrderButton = By.xpath(".//button[text()='Оформить заказ']");//локатор кнопки Оформить заказ
    private final WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("click on personal cabinet button on home page")
    public void clickPersonalCabinetButton() {
        this.driver.findElement(this.personalCabinetButton).click();
    }

    @Step("click on login to account button on home page")
    public void clickLogInToAccountButton() {
        this.driver.findElement(this.logInToAccountButton).click();
    }

    @Step("click on logo Stellar Burgers on home page")
    public void clickLogoStellarBurgers() {
        this.driver.findElement(this.logoStellarBurgers).click();
    }

    @Step("click on logo of constructor on home page")
    public void clickLogoConstructor() {
        this.driver.findElement(this.logoConstructor).click();
    }

    @Step("click on buns in constructor on home page")
    public void clickBunsButton() {
        this.driver.findElement(this.bunsButton).click();
    }

    @Step("click on sauce in constructor on home page")
    public void clickSauceButton() {
        this.driver.findElement(this.sauceButton).click();
    }

    @Step("click on filling in constructor on home page")
    public void clickFillingsButton() {
        this.driver.findElement(this.fillingsButton).click();
    }

    @Step("wait for load first burger ingredient image to confirm successful open section bun of constructor")
    public void waitForLoadFirstBurgerIngredientImageToConfirmSuccessfulOpenSectionBunOfConstructor() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(firstBurgerIngredientImage));
    }

    @Step("wait for load first sauce ingredient image to confirm successful open section sauce of constructor")
    public void waitForLoadFirstSauceIngredientImageToConfirmSuccessfulOpenSectionSauceOfConstructor() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(firstSauceIngredientImage));
    }

    @Step("wait for load first filling ingredient image to confirm successful open section fillings of constructor")
    public void waitForLoadFirstFillingsIngredientImageToConfirmSuccessfulOpenSectionFillingsOfConstructor() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(firstFillingsIngredientImage));
    }

    @Step("wait for load place an order button after successful authorization")
    public void waitForLoadPlaceAnOrderButtonAfterSuccessfulAuthorization() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(placeAnOrderButton));
    }

    @Step("wait for load login to account button after successful transition from personal cabinet to home page")
    public void waitForLoadLogInToAccountButtonAfterSuccessfulTransitionFromPersonalCabinetToHomePage() {
        (new WebDriverWait(this.driver, 3)).until(ExpectedConditions.visibilityOfElementLocated(logInToAccountButton));
    }
}
