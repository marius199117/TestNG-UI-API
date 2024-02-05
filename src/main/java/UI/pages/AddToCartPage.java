package UI.pages;

import UI.common.CommonDefinitions;
import org.openqa.selenium.By;

public class AddToCartPage  {
    CommonDefinitions commonDefinitions = new CommonDefinitions();
    private final By addToCartButton = By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']");
    private final By shoppingCart = By.xpath("//a[@class='shopping_cart_link']");
    private final By product = By.xpath("//div[@class='cart_item_label']");

    public void addToCart() {
        commonDefinitions.wait10AndClick(addToCartButton);
        commonDefinitions.wait10AndClick(shoppingCart);
        commonDefinitions.findElementByXPath(product);
    }
}
