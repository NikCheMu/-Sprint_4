package pom.order;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static utils.Utils.*;

import java.time.Duration;

public class ContactDetailsPage {
    private final WebDriver driver;
    //Поле ввода имени
    private final By nameInput = By.xpath(".//input[@placeholder = '* Имя']");
    //Поле ввода фамилии
    private final By surenameInput = By.xpath(".//input[@placeholder = '* Фамилия']");
    //Поле ввода адреса
    private final By addressInput = By.xpath(".//input[@placeholder = '* Адрес: куда привезти заказ']");
    //Выпадающий список станций метро
    private final By subwaySelect = By.className("select-search__input");
    //Станция метро в списке
    private final By subwayListItem = By.className("select-search__row");
    //Поле ввода номера телефона
    private final By phoneNumberInput = By.xpath(".//input[@placeholder = '* Телефон: на него позвонит курьер']");
    //Кнопка Далее
    private final By orderNextButton = By.xpath(".//div[@class = 'Order_NextButton__1_rCA']//button");



    public ContactDetailsPage(WebDriver driver){
        this.driver = driver;
    }

    public ContactDetailsPage enterName(String name){
        driver.findElement(nameInput).sendKeys(name);
        return this;
    }

    public ContactDetailsPage enterSurename(String sureName){
        driver.findElement(surenameInput).sendKeys(sureName);
        return this;
    }

    public ContactDetailsPage enterAddress(String address){
        driver.findElement(addressInput).sendKeys(address);
        return this;
    }
    //Возвращаем Название для дальнейшей проверки на экране проверки статуса заказа
    public String selectSubway(){
        driver.findElement(subwaySelect).click();
        int subwayCount = driver.findElements(subwayListItem).size();
        long subwayIndex = getRandomNumber(0,subwayCount);
        WebElement element = driver.findElement(By.xpath(".//li[@data-index='"+subwayIndex+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
        String subwayName = new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath(".//li[@data-index='"+subwayIndex+"']")))).getText();
        driver.findElement(By.xpath(".//li[@data-index='"+subwayIndex+"']")).click();
        return subwayName;
    }

    public ContactDetailsPage enterPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
        return this;
    }

    public ContactDetailsPage pushOrderNextButton(){
        driver.findElement(orderNextButton).click();
        return this;
    }

}
