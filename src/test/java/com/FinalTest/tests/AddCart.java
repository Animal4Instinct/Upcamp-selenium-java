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

public class AddCart {


    //1- Ingresar a:http://magento-demo.lexiconn.com/
    //2-Buscar GRAMERCY THROW
    //3- Verificar Precio
    //4- Añadir al carrito
    //5- Verificar que esté en el carrito
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
  public void testAddCart() throws Exception {
    driver.get("http://magento-demo.lexiconn.com/");
    driver.findElement(By.xpath("//img[@alt='Physical & Virtual Gift Cards']")).click();
    driver.findElement(By.xpath("//img[@alt='Bed & Bath']")).click();
    driver.findElement(By.id("product-collection-image-385")).click();
    assertEquals(driver.findElement(By.xpath("//span[@id='product-price-385']/span")).getText(), "$275.00");
    driver.findElement(By.xpath("//form[@id='product_addtocart_form']/div[4]/div/div/div[2]/button/span/span")).click();
    driver.findElement(By.xpath("//header[@id='header']/div/div[2]/div/a/span[2]")).click();
    assertEquals(driver.findElement(By.xpath("//ul[@id='cart-sidebar']/li/div/table/tbody/tr/td/span")).getText(), "$275.00");
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
