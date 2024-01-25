package UI.pages;
import UI.common.CommonDefinitions;
import org.openqa.selenium.By;

public class LoginPage extends CommonDefinitions {

    private final By usernameLocator = By.xpath("//input[@id='user-name']");
    private final By passwordLocator = By.xpath("//input[@id='password']");
    private final By loginButtonLocator = By.xpath("//input[@id='login-button']");
    private final By logo = By.xpath("//div[@class='app_logo']");

    public void successfulLogin() {
        insertKeywoardIntoField(usernameLocator, "standard_user");
        insertKeywoardIntoField(passwordLocator, "secret_sauce");
        wait10AndClick(loginButtonLocator);
        findElementByXPath(logo);
    }
}

