package pom.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class OrderCreatedModal {
    private final WebDriver driver;
    //Модальное окно заказ оформлен
    private final By orderCreatedModal = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ' and text() = 'Заказ оформлен']/parent::div");



    public OrderCreatedModal(WebDriver driver){
        this.driver = driver;
    }


    public boolean findOrderCreatedModal(){
        return new WebDriverWait(driver,Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(driver.findElement(orderCreatedModal))).isDisplayed();
    }

}
