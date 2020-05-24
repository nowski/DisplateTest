package page.objects;

import helper.ClickHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PosterPage extends BasePage {

    ClickHelper clickHelper;

    public PosterPage() {
        clickHelper = new ClickHelper();
    }


    @FindBy(xpath = "//*[@class='gallery-container gallery-container--alone gallery-container--large']")
    public WebElement selectSizePosterLarge;
    @FindBy(xpath = "//*[@class='button button--primary button--full-width button--big']")
    public WebElement addToCartButton;
    @FindBy(xpath = "//*[@class='button button--primary button--full-width button--big']/span")
    public WebElement addToCartButtonValue;
    @FindBy(xpath = "//*[contains(@class, 'purchase-feedback-text')and(text()='Item successfully added to cart!')]")
    public WebElement successInfo;
    @FindBy(xpath = "//*[contains(text(), 'Your cart')]")
    public WebElement yourCart;
    @FindBy(xpath = "//*[contains(@class, 'aside-menu__label')and(text()=' Cart ')]")
    public WebElement cartIcon;

    public static final String POSTER_FRAME_UNIQUE_SIZE = "image-background image-background-size--";
    public static final String MEDIUM_SIZE = "m";
    public static final String LARGE_SIZE = "l";
    public static final String EXTRA_LARGE = "xl";
    public String posterPrice;


    public boolean chooseSizeOfPoster(String size) {
        driver.findElement(By.xpath("//*[contains(@class, 'image-background-size--"+size+"')]")).click();
        return selectSizePosterLarge.isDisplayed();
    }

    public String checkPriceOfPoster() {
        clickHelper.waitUntilElementIsVisible(addToCartButtonValue);
        posterPrice = addToCartButtonValue.getText().substring(1, 3);
        addToVariable("posterPriceValue", posterPrice);
        return posterPrice;
    }

    public boolean addPosterToCart() {
        clickHelper.clickElement(addToCartButton);
        return clickHelper.isDisplayed(successInfo);
    }


    public boolean goToCartPage() {
        clickHelper.clickElement(cartIcon);
        clickHelper.waitUntilElementIsVisible(cartIcon);
        return clickHelper.isDisplayed(yourCart);
    }
}
