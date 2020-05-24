package page.objects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class MainMenu extends BasePage {
    public MainMenu() {
        super();

    }

    @FindBy(xpath = "(//a[@class='displate-tile__link displate-item-link'])[1]")
    protected WebElement firstProduct;

    @FindBy(xpath = "//*[contains(@class, 'button button--primary')]/span")
    protected WebElement priceButton;

    @FindBy(xpath = "//*[@class='button button--secondary button--small']")
    protected WebElement popup;


    public static final String URL_ADRESS = "https://displate.com/";

    public void closePopup() {
        clickHelper.clickElement(popup);
    }

    public boolean chooseFirstProduct() {
        driver.navigate().to(URL_ADRESS);
        closePopup();
        clickHelper.scrollToElementPx(400);
        clickHelper.clickElement(firstProduct);
        return clickHelper.isDisplayed(priceButton);
    }


}