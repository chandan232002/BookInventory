package com.thinkconstructive.book_store.mapper;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.entity.Book;

public class BookMapper {
    public static BookDto toDto(Book book) {
        BookDto bookDto=new BookDto(book.bookId(), book.name(),book.price(),book.author(),book.description());
        return bookDto;
    }
    public static Book toEntity(BookDto bookdto){
        Book book=new Book(bookdto.bookId(),bookdto.name(),bookdto.price(),bookdto.author(),bookdto.description());
        return book;
    }
}
