package com.persistantcoder.isbntools;

import com.persistantcoder.isbntools.api.ISBNDataServiceApi;

/**
 * Created by Evans K F C on May ,2020
 **/
public class StockManager {

    private ISBNDataServiceApi serviceApi;

    public void setIsbnDataServiceApi(ISBNDataServiceApi isbnDataServiceApi) {
        this.serviceApi = isbnDataServiceApi;
    }

    public String getLocatorCode(String isbn) {

        Book book = serviceApi.lookup(isbn);
        StringBuilder  builder = new StringBuilder();
        builder.append(isbn.substring(isbn.length()-4));
        builder.append(book.getAuthor().substring(0,1));
        builder.append(book.getTitle().split(" ").length);

        return builder.toString();
    }
}
