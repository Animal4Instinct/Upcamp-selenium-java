package com.FinalTest.pages;

import com.FinalTest.dataProviders.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.testng.Assert.assertEquals;


public class ProductSearchPage extends HomePage {
    @FindBy(css = ".products-grid .product-name")
    private List<WebElement> searchResultsItems;
    @FindBy(css = "#narrow-by-list li a")
    private List<WebElement> filterOptions;
    @FindBy(css = ".currently .value")
    private List<WebElement> currentFilters;
    @FindBy(className = "note-msg")
    private WebElement noResultsMessage;
    @FindBy(className = "page-title")
    private WebElement pageTitle;
    @FindBy(className = "search")
    private WebElement SearchBreadcrumb;
    @FindBy(className = "price")
    private WebElement SearchPrice;
    @FindBy(name = "Add to Cart")
    private WebElement addToCart;
    @FindBy(name = "product-name")
    private WebElement nameProductAdded;
    @FindBy(className = "skip-link skip-cart ")
    private WebElement openCart;

    public ProductSearchPage(WebDriver driver) { super(driver);   }

    @Step("Get the number of search results")
    public int getNumberOfResults() {
        return searchResultsItems.size();
    }

    @Step("Apply filter '{filter}'")
    public void applyFilter(String filter) {
        WebElement filterElement = findFilterElementByText(filter);
        if (filterElement != null) {
            filterElement.click();
        }
        new ProductSearchPage(driver);
    }

    @Step("Find filter element by text '{filterText}'")
    private WebElement findFilterElementByText(String filterText) {
        for (WebElement filterOption : filterOptions) {
            if (filterOption.getText().contains(filterText)) {
                return filterOption;
            }
        }
        return null;
    }

    @Step("Check if filter '{filter}' is applied")
    public boolean isFilterApplied(String filter) {
        for (WebElement currentFilter : currentFilters) {
            if (currentFilter.getText().contains(filter)) {
                return true;
            }
        }
        return false;
    }

    @Step("Get the no results message")
    public String getNoResultsMessage() {
        return noResultsMessage.getText();
    }

    @Step("Get the search title")
    public String getSearchTitle() {
        return pageTitle.getText();
    }

    @Step("Get the price")
    public String getPrice() {
        return SearchPrice.getText();
    }

    @Step("Click on Add to Cart")
    public void clickAddToCart() {
     //   addToCart.click();
        WebElement addProduct = driver.findElement(By.className("AddToCart"));
        addProduct.click();
    }

    @Step("Click on Open Cart")
    public void clickOpenToCart() {
        openCart.click();
    }

    @Step("Get nameProductAdded")
    public String getNameProductAdded() {
        return nameProductAdded.getText();
    }

    @Step("Get the search breadcrumb")
    public String getSearchBreadcrumb() {
        return SearchBreadcrumb.getText();
    }

    @Step("Click on product")
    public ProductPage clickProduct(String product) {
        WebElement productLink = driver.findElement(By.linkText(product));
        productLink.click();
        return new ProductPage(driver, product);
    }

    @Step("Verify product on cart")
    public boolean isOnTheCart(String productName) {
       this.clickOpenToCart();
        String nameProductAdded= getNameProductAdded();
        if (nameProductAdded == productName)
        {
            return true;
        }
        return false;
    }

    @Step("Wait for search results")
    public void waitForSearchResults() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfAllElements(searchResultsItems));
    }

}