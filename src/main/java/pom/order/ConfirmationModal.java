package pom.order;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConfirmationModal {
    private final WebDriver driver;

    //Кнопка Да
    private final By yesButton = By.xpath(".//div[@class = 'Order_Modal__YZ-d3']//div[@class = 'Order_Buttons__1xGrp']//button[text()='Да']");




    public ConfirmationModal(WebDriver driver){
        this.driver = driver;
    }



    public ConfirmationModal pushYesButton(){
        new WebDriverWait(driver,Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOf(driver.findElement(yesButton))).click();
        return this;
    }

}
