package pom;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class MainPage {
    private final WebDriver driver;

    //Список вопросов и ответов
    private final By faqAccordion = By.className("Home_FAQ__3uVm4");

    //Ответ на вопрос
    private final By answer = By.xpath(".//div[@class='accordion__item']//div[not(@hidden)and @class='accordion__panel']");

    //Кнопка Заказать в хэдере
    private final By headerOrderButton = By.className("Button_Button__ra12g");

    //Кнопка Заказать в боди
    private final By bodyOrderButton = By.xpath(".//div[@class = 'Home_FinishButton__1_cWm']//button");

    //Кнопка да все привыкли
    private final By cookieAcceptButton = By.id("rcc-confirm-button");

    public enum OrderButtonLocation{
        HEADER,
        BODY
    }

    public MainPage(WebDriver driver){
        this.driver = driver;
    }

    public MainPage openMainPage(){
        driver.get("https://qa-scooter.praktikum-services.ru/");
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(headerOrderButton)));
        return this;
    }

    public String getAnswerByQuestion(String question){
        driver.findElement(By.xpath(".//div[text()='"+ question+"']")).click();
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(answer)));
        return driver.findElement(answer).getText();
    }

    public MainPage scrollToFaqSection(){
        WebElement element = driver.findElement(faqAccordion);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;

    }

    public MainPage clickOrderButton(OrderButtonLocation buttonLocation){
        if(buttonLocation ==OrderButtonLocation.BODY){
            WebElement element = driver.findElement(bodyOrderButton);
            ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
            new WebDriverWait(driver, Duration.ofSeconds(3))
                    .until(ExpectedConditions.visibilityOf(driver.findElement(bodyOrderButton))).click();
        }
        driver.findElement(headerOrderButton).click();
        return this;
    }

    public MainPage clickCookieAcceptButton(){
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(cookieAcceptButton))).click();
        return this;
    }






}
