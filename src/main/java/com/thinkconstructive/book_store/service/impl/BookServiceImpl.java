package com.thinkconstructive.book_store.service.impl;
import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.entity.Book;
import com.thinkconstructive.book_store.mapper.BookMapper;
import com.thinkconstructive.book_store.repository.BookRepository;
import com.thinkconstructive.book_store.service.BookService;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
@Service
public class BookServiceImpl implements BookService {

    BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Override
    public BookDto getBookById(String bookId) {
        Book bookDto = bookRepository.findByBookId(bookId);
        return BookMapper.toDto(bookDto);
    }

    @Override
    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        List<BookDto> bookDtosList =new ArrayList<>();
        for(Book book:books){
            bookDtosList.add(BookMapper.toDto(book));
        }
        return bookDtosList;
    }

    @Override
    public BookDto createBook(BookDto bookDto) {
        Book book = BookMapper.toEntity(bookDto);
        Book saveBook=bookRepository.save(book);
        return BookMapper.toDto(saveBook);
    }

    @Override
    public BookDto updateBookName( BookDto bookDto) {
        bookRepository.updateBookNameByBookId(bookDto.bookId(),bookDto.name());
        Book book = bookRepository.findByBookId(bookDto.bookId());

        return BookMapper.toDto(book);
    }

    @Override
    public void deleteBookByBookId(String bookId) {
        bookRepository.deleteByBookId(bookId);

    }
}
