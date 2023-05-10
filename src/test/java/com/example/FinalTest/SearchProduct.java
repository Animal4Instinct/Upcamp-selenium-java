package com.example.FinalTest;

import dataProviders.ProductsData;
import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductPage;
import pages.ProductSearchPage;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class SearchProduct {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  private JavascriptExecutor js;

  @BeforeClass(alwaysRun = true)
  public void setUp() throws Exception {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    driver = new ChromeDriver();
    baseUrl = "http://magento-demo.lexiconn.com/";
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    js = (JavascriptExecutor) driver;
  }

  @Test(dataProvider = "products", dataProviderClass = ProductsData.class)
  public void testSearchProduct(String product,String price) throws Exception {
    HomePage homePage = new HomePage(driver);
    ProductSearchPage productSearchPage = homePage.search(product);
    ProductPage productPage = productSearchPage.clickProduct(product);
    assertEquals(productPage.getPrice(), price);
  }

  @AfterClass(alwaysRun = true)
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
  @Attachment(type = "image/png")
  @AfterMethod(alwaysRun = true)
  public byte[] takeScreenshot() throws Exception {
    byte[] image = new byte[0];
    try {
      TakesScreenshot screenshot = (TakesScreenshot) driver;
      image = screenshot.getScreenshotAs(OutputType.BYTES);
      System.out.println("Successfully captured a screenshot");
    } catch (Exception e) {
      System.out.println("Exception while taking screenshot " + e.getMessage());
    }
    return image;
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
