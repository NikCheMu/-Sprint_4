package pom.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.Utils.getRandomNumber;

public class RentPage {
    private final WebDriver driver;
    //Поле ввода даты с дейтпикером
    private final By dateInput = By.cssSelector(".Input_Input__1iN_Z.Input_Responsible__1jDKN");
    //Выпадающий список сроков аренды
    private final By rentalPeriodDropDownArrow = By.className("Dropdown-arrow");
    //Элемент списка сроков аренды
    private final By rentalPeriodItem = By.className("Dropdown-option");
    //Чекбокс цвета самоката
    private final By scooterColourCheckbox = By.xpath(".//div[@class = 'Order_Checkboxes__3lWSI']//label[@class = 'Checkbox_Label__3wxSf']");
    //Кнопка Заказать
    private final By rentalOrderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']//button[text()='Заказать']");





    public RentPage(WebDriver driver){
        this.driver = driver;
    }

    public RentPage enterDate(String date){
        driver.findElement(dateInput).sendKeys(date);
        return this;
    }
    //Возвращаем период для дальнейшей проверки на экране проверки статуса заказа
    public String chooseRentalPeriod(){
        driver.findElement(rentalPeriodDropDownArrow).click();
        List<WebElement> elements =  driver.findElements(rentalPeriodItem);
        int periodsCount = elements.size()-1;
        int periodNumber = (int)getRandomNumber(0,periodsCount);
        String period = elements.get(periodNumber).getText();
        elements.get(periodNumber).click();
        return period;
    }
    //Возвращаем цвет для дальнейшей проверки на экране проверки статуса заказа
    public String chooseRandomColour(){
        List<WebElement> elements =  driver.findElements(scooterColourCheckbox);
        int coloursCount = elements.size();
        int colourNumber = (int)getRandomNumber(0,coloursCount);
        String colour = elements.get(colourNumber).getText();
        elements.get(colourNumber).click();
        return colour;
    }

    public RentPage pushRentalOrderButton(){
        driver.findElement(rentalOrderButton).click();
        return this;
    }

}
