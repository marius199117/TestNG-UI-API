package UI_Test_Cases;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import UI.common.CommonDefinitions;
import UI.pages.AddToCartPage;
import UI.pages.LoginPage;

public class LoginAddToCart extends CommonDefinitions {


    LoginPage loginPage = new LoginPage();
    AddToCartPage addToCartPage = new AddToCartPage();

    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void testLoginWithValidCredentials() {
        loginPage.successfulLogin();
    }

    @Test(priority = 2)
    public void testAddToCart() {
     addToCartPage.addToCart();
    }

    @AfterClass
    public void tearDown() {
        driver.close();
        driver.quit();
    }
}
