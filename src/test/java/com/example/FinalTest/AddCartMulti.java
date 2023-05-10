package com.example.FinalTest;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

public class AddCartMulti {
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

  @Test
  public void testAddCartMulti() throws Exception {
    driver.get("http://magento-demo.lexiconn.com/");
    driver.findElement(By.xpath("//html[@id='top']/body/div/div[2]/div[2]/div/div/div/div/ul/li[2]/a/img")).click();
    driver.findElement(By.id("product-collection-image-339")).click();
    driver.findElement(By.id("product_addtocart_form")).click();
    driver.findElement(By.id("qty")).clear();
    driver.findElement(By.id("qty")).sendKeys("2");
    assertEquals(driver.findElement(By.xpath("//span[@id='product-price-339']/span")).getText(), "$295.00");
    driver.findElement(By.xpath("//button[@type='button']")).click();
    assertEquals(driver.findElement(By.xpath("//table[@id='shopping-cart-table']/tbody/tr/td[5]/span/span")).getText(), "$590.00");
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
