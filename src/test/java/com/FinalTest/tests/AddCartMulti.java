package com.FinalTest.tests;

import com.FinalTest.pages.HeaderPage;
import com.FinalTest.pages.ProductSearchPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddCartMulti {

  protected WebDriver driver;
  @BeforeMethod
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver();
  }






  @Test
  public void testAddCartMulti(){
    driver.get("http://magento-demo.lexiconn.com");     //1- Ingresar a:http://magento-demo.lexiconn.com/
    HeaderPage headerPage = new HeaderPage(driver);
    String product="Retro Chic Eyeglasses";
    ProductSearchPage productSearchPage = headerPage.search(product);  //2- Buscar GRETRO CHIC EYEGLASSES
    String price = productSearchPage.getPrice();
    assertEquals(price, "$295.00", "El precio coincide"); //3- Verificar Precio
    productSearchPage.clickAddToCart();
    productSearchPage.clickAddToCart();  //4- Añadir 2 al carrito
    price= price+price;
                 //5- Verificar que precio en carrito

    driver.findElement(By.id("product_addtocart_form")).click();
    driver.findElement(By.id("qty")).clear();
    driver.findElement(By.id("qty")).sendKeys("2");
    assertEquals(driver.findElement(By.xpath("//span[@id='product-price-339']/span")).getText(), "$295.00");
    driver.findElement(By.xpath("//button[@type='button']")).click();
    assertEquals(driver.findElement(By.xpath("//table[@id='shopping-cart-table']/tbody/tr/td[5]/span/span")).getText(), "$590.00");
  }


  @Attachment(type = "image/png")
  @AfterMethod(alwaysRun = true)
  public byte[] takeScreenshot(){
    byte[] image;
    TakesScreenshot screenshot = (TakesScreenshot) driver;
    image = screenshot.getScreenshotAs(OutputType.BYTES);
    System.out.println("Successfully captured a screenshot");
    return image;
  }
  @AfterMethod
  public void tearDown() {
    driver.close();
  }

}
