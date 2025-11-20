package com.thinkconstructive.book_store.controller;

import com.thinkconstructive.book_store.dto.BookDto;
import com.thinkconstructive.book_store.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/book-app")
public class BookController {
    BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }
    @GetMapping("/welcome")
    public ResponseEntity<String> getAllBooks(){
        return new ResponseEntity<>("Accessible for All Users", HttpStatus.OK);

    }
    @GetMapping("/{bookId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<BookDto> getBook(@PathVariable String bookId){
       BookDto bookDto= bookService.getBookById(bookId);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);

    }
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<List<BookDto>> addBook(){
           List<BookDto> bookDtosList= bookService.getAllBooks();
        return new ResponseEntity<>(bookDtosList,HttpStatus.OK);
   }

   @PostMapping("/")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto){

        return new ResponseEntity<>(bookService.createBook(bookDto),HttpStatus.OK);
   }
   @PutMapping("/")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto){
       BookDto bookDto1=bookService.updateBookName(bookDto);
        return new ResponseEntity<>(bookDto1,HttpStatus.OK);
   }
   @DeleteMapping("/{bookId}")
   @PreAuthorize("hasRole('ADMIN')")
   public ResponseEntity<String> deleteBook(@PathVariable String bookId){
        bookService.deleteBookByBookId(bookId);
        return new ResponseEntity<>("Book deleted Successfully "+bookId,HttpStatus.OK);
   }
}
