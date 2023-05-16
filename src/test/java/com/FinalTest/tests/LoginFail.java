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

public class LoginFail {
 // 1- Ingresar a: http://magento-demo.lexiconn.com/
 //2-Realizar login con las siguientes credenciales:
 // Email: Limon@cito.com
 // Password: limoncito123
//3- Verificar si envía el error correspondiente.
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
  public void testLoginFail() throws Exception {
    driver.get("http://magento-demo.lexiconn.com/");
    driver.findElement(By.xpath("//header[@id='header']/div/div[2]/a[3]/span[2]")).click();
    driver.findElement(By.linkText("Log In")).click();
    driver.findElement(By.id("email")).clear();
    driver.findElement(By.id("email")).sendKeys("Limon@cito.com");
    driver.findElement(By.id("pass")).click();
    driver.findElement(By.id("pass")).clear();
    driver.findElement(By.id("pass")).sendKeys("limoncito123");
    driver.findElement(By.xpath("//button[@id='send2']/span/span")).click();
    assertEquals(driver.findElement(By.xpath("//html[@id='top']/body/div/div[2]/div[2]/div/div/div/ul/li/ul/li/span")).getText(), "Invalid login or password.");
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
