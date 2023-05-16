package com.FinalTest.pages;

import com.FinalTest.dataProviders.HomePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.Keys.ENTER;
public class HeaderPage extends HomePage {

    @FindBy(css = "#search_mini_form input")
    private WebElement searchBar;

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Step("Search for product with keyword: {keyword}")
    public ProductSearchPage search(String keyword) {
        searchBar.sendKeys(keyword);
        searchBar.sendKeys(ENTER);
        return new ProductSearchPage(driver);
    }

}
