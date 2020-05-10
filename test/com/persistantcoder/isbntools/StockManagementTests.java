package com.persistantcoder.isbntools;

import com.persistantcoder.isbntools.api.ISBNDataServiceApi;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Matchers.anyString;

/**
 * Created by Evans K F C on May ,2020
 **/
public class StockManagementTests {

    private ISBNDataServiceApi testWebService;
    StockManager stockManager;
    ISBNDataServiceApi testDatabaseService;

    @Before
    public void setUp() {
        testWebService = mock(ISBNDataServiceApi.class);
        stockManager = new StockManager();
        stockManager.setWebService(testWebService);
        testDatabaseService = mock(ISBNDataServiceApi.class);
        stockManager.setDatabaseServiceApi(testDatabaseService);

    }


    @Test
    public void testCanGetACorrectLocaterCode() {
        when(testWebService.lookup(anyString())).thenReturn(new Book("0140177396", "Of Mice and Men", "J Steinbeck"));

        when(testWebService.lookup(anyString())).thenReturn(null);

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);
        assertEquals("7396J4", locatorCode);
    }


    @Test
    public void databaseIsUsedIfDataIsPresent() {
        when(testDatabaseService.lookup("0140177396")).thenReturn(new Book("0140177396", "The Life and times of Evans", "Evans K F C"));

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(testDatabaseService, times(1)).lookup("0140177396");
        verify(testWebService, never()).lookup("");


    }

    @Test
    public void webserviceIsUsedIfDataIsNotPresentInDatabase() {
        when(testDatabaseService.lookup("0140177396")).thenReturn(null);
        when(testWebService.lookup("0140177396")).thenReturn(new Book("0140177396", "The Life and times of Evans", "Evans K F C"));

        String isbn = "0140177396";
        String locatorCode = stockManager.getLocatorCode(isbn);

        // verify(dataService,times(1)).lookup("0140177396");
        verify(testWebService, times(1)).lookup("0140177396");
    }

}
