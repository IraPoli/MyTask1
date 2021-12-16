package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CurtPage extends BasePage {

    @FindBy(xpath = "//div[@class= 'cart-receipt__sum-price']/span")
    private WebElement sumPrice;

    public CurtPage() {
    }

    public int getSumPrice() {
        return Integer.parseInt(sumPrice.getText());
    }
}
