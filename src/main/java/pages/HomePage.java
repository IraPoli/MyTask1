package pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {
    @FindBy(xpath = "//input[contains(@class,'search-form')]")
    private WebElement searchField;

    @FindBy(xpath = "//button[contains(@class,' search-form__submit')]")
    private WebElement searchButton;

    public HomePage() {
    }

    public void enterTextToSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText, Keys.ENTER);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

}
