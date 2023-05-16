package com.FinalTest.pages;

import com.FinalTest.dataProviders.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage extends HomePage {
    private final String selectedProductName;


    @FindBy(css = ".product-name .h1")
    private WebElement productName;
  //  @FindBy(css = ".breadcrumbs .product")
   // private WebElement searchBreadcrumb;        //que es?
    @FindBy(css = ".breadcrumbs .product")
    private WebElement price;

   public ProductPage(WebDriver driver, String selectedProductName){
        super(driver);
        this.selectedProductName = selectedProductName;
    }


    @Step("Get product name")
    public String getProductName() {
        return productName.getText();
    }

    @Step("Get product price")
    public String getPrice(){return price.getText();}

   // @Step("Get search breadcrumb")
   // public String getSearchBreadcrumb() {return searchBreadcrumb.getText(); }

    @Step("Wait for page title")
    public String getSelectedProductName() {
        return selectedProductName;
    }

    @Step("Wait for page title to be displayed")
    public void waitForPageTitle() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOf(productName));
    }


}