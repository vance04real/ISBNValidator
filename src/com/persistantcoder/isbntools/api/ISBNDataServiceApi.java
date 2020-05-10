package com.persistantcoder.isbntools.api;

import com.persistantcoder.isbntools.Book;

/**
 * Created by Evans K F C on May ,2020
 **/
public interface ISBNDataServiceApi {

 Book lookup (String isbn);
}
