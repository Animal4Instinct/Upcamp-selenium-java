package dataProviders;

import org.testng.annotations.DataProvider;

public class ProductsData {
    @DataProvider(name = "products")
    public static Object[][] getProductsData() {
        return new Object[][]{
                {"product-collection-image-339", "$295.00"},
                {"Classic Hardshell Suitcase", "$275.00"},
                {"Canon EOS 5D", "$98.00"}
        };
    }
}