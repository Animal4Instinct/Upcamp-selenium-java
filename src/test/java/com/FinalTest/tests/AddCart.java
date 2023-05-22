package com.FinalTest.tests;

import com.FinalTest.pages.HeaderPage;
import com.FinalTest.pages.ProductSearchPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.internal.matchers.text.ValuePrinter.print;
import static org.testng.Assert.assertEquals;

public class AddCart {
  protected WebDriver driver;

  @BeforeMethod
  public void setUp() {
    System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");
    driver = new ChromeDriver();
  }
  @Test
  public void testAddCart() {
    driver.get("http://magento-demo.lexiconn.com");     //1- Ingresar a:http://magento-demo.lexiconn.com/
    HeaderPage headerPage = new HeaderPage(driver);
    String product="Retro Chic Eyeglasses";
    ProductSearchPage productSearchPage = headerPage.search(product);  //2- Buscar GRETRO CHIC EYEGLASSES
    String price = productSearchPage.getPrice();
    assertEquals(price, "$295.00", "El precio coincide"); //3- Verificar Precio
    productSearchPage.clickAddToCart();  //4- Añadir al carrito
    productSearchPage.clickOpenToCart(); // 5 - Abrir el carrito
    boolean test = true;
    assertEquals(productSearchPage.isOnTheCart("Retro Chic Eyeglasses"),test, "Fue añadido al carrito con exito"); // 4- Verificar si  está en el carrito
  }

  @AfterMethod
  public void tearDown() {
    driver.close();
  }
}
