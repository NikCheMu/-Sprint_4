package orderpage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import pom.MainPage;
import pom.order.ConfirmationModal;
import pom.order.ContactDetailsPage;
import pom.order.OrderCreatedModal;
import pom.order.RentPage;

import static utils.Utils.*;


@RunWith(Parameterized.class)
public class OrderPageTest {
    private WebDriver driver;
    private MainPage mainPage;
    private final MainPage.OrderButtonLocation orderButtonLocation;

    public OrderPageTest(MainPage.OrderButtonLocation orderButtonLocation){
        this.orderButtonLocation = orderButtonLocation;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderButtonLocation(){
        return new Object[][]{
                {MainPage.OrderButtonLocation.BODY},
                {MainPage.OrderButtonLocation.HEADER}
        };
    }

    @Before
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        mainPage = new MainPage(driver);
        mainPage.openMainPage().clickCookieAcceptButton();
    }

    @Test
    public void orderCreatedModalAppearsAfterOrderSuccessCreated(){
        ContactDetailsPage contactDetailsPage = new ContactDetailsPage(driver);
        RentPage rentPage = new RentPage(driver);
        ConfirmationModal confirmationModal = new ConfirmationModal(driver);
        OrderCreatedModal orderCreatedModal = new OrderCreatedModal(driver);
        mainPage.clickOrderButton(orderButtonLocation);
        String name = getRandomString(8);
        String sureName = getRandomString(8);
        String address = getRandomAddress();
        String phoneNumber = getRandomPhoneNumber();
        String date = getNextDayString();
        contactDetailsPage.selectSubway();
        contactDetailsPage.enterName(name)
                .enterSurename(sureName)
                .enterAddress(address)
                .enterPhoneNumber(phoneNumber)
                .pushOrderNextButton();
        rentPage.enterDate(date);
        rentPage.chooseRentalPeriod();
        rentPage.chooseRandomColour();
        rentPage.pushRentalOrderButton();
        confirmationModal.pushYesButton();
        Assert.assertTrue("Модальное окно не отображается",orderCreatedModal.findOrderCreatedModal());
    }

    @After
    public void teardown() {
        driver.quit();
    }

}
