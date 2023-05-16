package com.FinalTest.dataProviders;

import org.testng.annotations.DataProvider;

public class ProductsData {
    @DataProvider(name = "productsToBeSearch")
    public static Object[][] getProductsData() {
        return new Object[][]{
                {"RETRO CHIC EYEGLASSES", "$295.00"},
                {"Classic Hardshell Suitcase", "$275.00"},
                {"Canon EOS 5D", "$98.00"}
        };
    }
}