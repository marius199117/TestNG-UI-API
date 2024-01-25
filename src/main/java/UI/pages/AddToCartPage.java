package UI.pages;

import UI.common.CommonDefinitions;
import org.openqa.selenium.By;

public class AddToCartPage extends CommonDefinitions {
    private final By addToCartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    private final By shoppingCart = By.xpath("//a[@class='shopping_cart_link']");
    private final By product = By.xpath("//div[@class='cart_item_label']");

    public void addToCart() {
        wait10AndClick(addToCartButton);
        wait10AndClick(shoppingCart);
        findElementByXPath(product);
    }
}
