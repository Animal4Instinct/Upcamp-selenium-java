package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
    private static WebDriver driver;

    ProductPage(WebDriver driver){
        ProductPage.driver=driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "suitcase")
    private WebElement price;

    public String getPrice(){
        return price.getText();
    }

}