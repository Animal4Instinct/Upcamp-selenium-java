package com.FinalTest.tests;

import com.FinalTest.dataProviders.ProductsData;
import com.FinalTest.pages.HeaderPage;
import com.FinalTest.pages.ProductPage;
import com.FinalTest.pages.ProductSearchPage;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchProduct {

  protected WebDriver driver;

  @BeforeMethod
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver();
  }

  @Test(dataProvider = "productsToBeSearch", dataProviderClass = ProductsData.class)
  public void testSearchProduct(String product, String price){
    driver.get("http://magento-demo.lexiconn.com");
    HeaderPage headerPage = new HeaderPage(driver); //1- Ingresar a: http://magento-demo.lexiconn.com/

    ProductSearchPage productSearchPage = headerPage.search(product);  //2- Buscar un determinado(producto)

    ProductPage productPage = productSearchPage.clickProduct(product);    //3- Clickear el primer producto
    Assert.assertEquals(productPage.getProductName(), product, "Es el producto correcto");  //4- Verificar si coincide con el producto buscado y el precio.
    Assert.assertEquals(productPage.getPrice(), price, "Es el precio correcto");

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
