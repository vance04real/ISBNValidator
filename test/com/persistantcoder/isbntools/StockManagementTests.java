package com.persistantcoder.isbntools;

import com.persistantcoder.isbntools.api.ISBNDataServiceApi;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Evans K F C on May ,2020
 **/
public class StockManagementTests {

    @Test
    public void testCanGetACorrectLocaterCode(){

        ISBNDataServiceApi testwebservice = new ISBNDataServiceApi() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men","J Steinbeck");
            }
        };

        ISBNDataServiceApi testDatabase = new ISBNDataServiceApi() {
            @Override
            public Book lookup(String isbn) {
                return null;
            }
        };


        StockManager stockManager = new StockManager();
        stockManager.setWebService(testwebservice);
        stockManager.setDatabaseServiceApi(testDatabase);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4",locatorCode);
    }




}