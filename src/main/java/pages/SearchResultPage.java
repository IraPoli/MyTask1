package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class SearchResultPage extends BasePage {
    @FindBy(xpath = "//a[@class='goods-tile__heading ng-star-inserted' ]")
    private List<WebElement> productList;

    //  @FindBy(xpath = "//button[contains(@class,'buy-button')]")

    @FindBy(xpath = " //ul[contains(@ class, 'catalog-grid')]//button[contains(@class,'buy-button')]")
    private List<WebElement> buyButtonList;

    @FindBy(xpath = " //ul[contains(@ class, 'catalog-grid')]//button[contains(@class,'buy-button')][1]")
    private WebElement buyButtonFirst;

    @FindBy(xpath = "//div[@data-filter-name=\"producer\"]//label")
    private List<WebElement> producerList;


    @FindBy(xpath = "//label[@for='Есть в наличии']")
    private WebElement availableLabel;

    @FindBy(xpath = "//a[@class='goods-tile__heading ng-star-inserted' ]")
    private List<WebElement> itemList;


    @FindBy(xpath = "//button[@opencart]")
    private WebElement openCurtButton;


    public SearchResultPage() {
    }

    public void selectFirstProduct() {
        productList.get(1).click();
    }

    public WebElement getBuyButtonElement() {
        WebElement el = buyButtonList.get(1);
        return buyButtonList.get(1);
    }

    public void addFirstProduct() {
        WebElement el = buyButtonList.get(1);
        el.click();
    }

    public void clickOpenCartButton() {
        openCurtButton.click();
    }

    public void selectBrand(String brandName) {
        WebElement webElementBrand = producerList.stream().filter(e -> e.getText().contains(brandName)).findAny().orElse(null);
        if (webElementBrand == null) throw new AssertionError();
        webElementBrand.click();
    }


    public WebElement getAvailableLabelElement() {
        return availableLabel;
    }

    public void selectAvailableLabel() {
        availableLabel.click();
    }

    public void selectFirstItem() {
        itemList.get(0).click();
    }

    public void clickBuyButtonFirst() {
        WebElement el = buyButtonFirst;
        buyButtonFirst.click();
    }

    public WebElement getABuyButtonFirst() {
        return buyButtonFirst;
    }

}
