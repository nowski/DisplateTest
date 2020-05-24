package page.objects;

import helper.ClickHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CartPage extends BasePage {

    ClickHelper clickHelper;
    PosterPage posterPage;

    public CartPage() {
        posterPage = new PosterPage();
        clickHelper = new ClickHelper();
    }





    @FindBy(xpath = "//*[@class='text text--bold text--extra-big']")
    protected WebElement priceOnTheCartPage;
    @FindBy(xpath = "//*[@id='select-country']/div[@class='input-select__item input-select__item--selected']")
    protected WebElement selectedCountryFromAList;
    @FindBy(xpath = "//*[@id='discount-code']")
    protected WebElement discountCodeInput;
    @FindBy(xpath = "//div[@class='discount']")
    protected WebElement openDiscountCodeInput;
    @FindBy(xpath = "//div[@class='discount__info text text--bold']//*")
    protected WebElement discountCodeValue;
    @FindBy(xpath = "//*[@id='select-country']")
    protected WebElement selectCountry;
    @FindBy(xpath = "//*[@id='discount-apply']")
    protected WebElement applyDiscountButton;
    @FindBy(xpath = "//span[@class='text text--bold text--cross text--large text--darkgrey']")
    protected WebElement oldPrice;
    @FindBy(xpath = "//span[@class='text text--bold text--extra-big']")
    protected WebElement newPrice;

    public static final String USA = "United States";
    public static final String CODE = "serej1";


    public boolean checkIfThePricesAreEqual() {
        String priceOnTheCartPageValue = priceOnTheCartPage.getText().substring(1, 3);
        System.out.println(priceOnTheCartPageValue);
        System.out.println(globalVariables.get("posterPriceValue"));
        return priceOnTheCartPageValue.equals(globalVariables.get("posterPriceValue"));
    }

    public boolean checkCountryFromShipToList(String selectedCountry) {
        clickHelper.clickElement(selectCountry);
        WebElement country = driver.findElement(By.xpath("//*[contains(@class, 'input-select__item')and(text()='" + selectedCountry + "')]"));
        clickHelper.clickElement(country);
        String selectedCountryList = selectedCountryFromAList.getText();
        return selectedCountryList.equals(selectedCountry);
    }

    public boolean addCode(String code) throws InterruptedException {
        clickHelper.scrollToElementPx(-400);
        try {
            driver.findElement(By.xpath("//div[@class='discount']")).click();
        } catch (TimeoutException e1) {
            Thread.sleep(3);
            driver.findElement(By.xpath("//div[@class='discount']")).click();
        }
        clickHelper.clickElementAndSendText(discountCodeInput, code);
        clickHelper.scrollToElementPx(-400);
        clickHelper.clickElement(applyDiscountButton);
        clickHelper.scrollToElementPx(-300);
        return clickHelper.isDisplayed(discountCodeValue);
    }

    public int checkValueOfDiscount() {
        clickHelper.scrollToElementPx(-200);
        clickHelper.waitUntilElementIsVisible(discountCodeValue);
        String discValue = discountCodeValue.getText().substring(0, 1);
        int discValueInt = Integer.parseInt(discValue);
        System.out.println(discValue);
        return discValueInt;
    }

    public boolean checkIsPriceIsAppropriate() {
        clickHelper.waitUntilElementIsVisible(priceOnTheCartPage);
        String oldPriceValue = oldPrice.getText().substring(1);
        String newPriceValue = newPrice.getText().substring(1);
        int oldPriceValueInt = Integer.parseInt(oldPriceValue);
        float newPriceValueInt = Float.parseFloat(newPriceValue);
        return (oldPriceValueInt > newPriceValueInt);
    }


}
