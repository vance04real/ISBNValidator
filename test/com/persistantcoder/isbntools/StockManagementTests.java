package com.persistantcoder.isbntools;

import com.persistantcoder.isbntools.api.ISBNDataServiceApi;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyString;

/**
 * Created by Evans K F C on May ,2020
 **/
public class StockManagementTests {

    private ISBNDataServiceApi webService;

    @Test
    public void testCanGetACorrectLocaterCode() {

        ISBNDataServiceApi testwebservice = new ISBNDataServiceApi() {
            @Override
            public Book lookup(String isbn) {
                return new Book(isbn, "Of Mice and Men", "J Steinbeck");
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
        assertEquals("7396J4", locatorCode);
    }


    @Test
    public void databaseIsUsedIfDataIsPresent() {

        ISBNDataServiceApi dataService = mock(ISBNDataServiceApi.class);
        ISBNDataServiceApi webService = mock(ISBNDataServiceApi.class);

        when(dataService.lookup("0140177396")).thenReturn(new Book("0140177396","The Life and times of Evans","Evans K F C"));
        StockManager stockManager = new StockManager();
        stockManager.setWebService(dataService);
        stockManager.setDatabaseServiceApi(webService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(dataService,times(1)).lookup("0140177396");
        verify(webService,times(0)).lookup("");


    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        ISBNDataServiceApi dataService = mock(ISBNDataServiceApi.class);
        ISBNDataServiceApi webService = mock(ISBNDataServiceApi.class);

        when(dataService.lookup("0140177396")).thenReturn(null);
        when(webService.lookup("0140177396")).thenReturn(new Book("0140177396","The Life and times of Evans","Evans K F C"));

        StockManager stockManager = new StockManager();
        stockManager.setWebService(dataService);
        stockManager.setDatabaseServiceApi(webService);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

       // verify(dataService,times(1)).lookup("0140177396");
        verify(webService,times(1)).lookup("0140177396");
    }

}
