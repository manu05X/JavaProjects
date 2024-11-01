package com.example.BookREST.controller;

import com.example.BookREST.model.Book;
import com.example.BookREST.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookServices bookServices;

    private List<Book> books = new ArrayList<>();

    // Constructor to add some sample books
    public BookController() {
        books.add(new Book(1, "1984", "George Orwell"));
        books.add(new Book(2, "To Kill a Mockingbird", "Harper Lee"));

//        books.add(new Book(1L, "1984", "George Orwell"));
//        books.add(new Book(2L, "To Kill a Mockingbird", "Harper Lee"));
    }


    @GetMapping
    public List<Book> getAllBooks() {
        return bookServices.getAllBook();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        //return books.stream().filter(book -> book.getId().equals(id)).findFirst().orElse(null);
        return bookServices.getBook(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
//        book.setId((long) (books.size() + 1));
//        books.add(book);
//        return book;
        return bookServices.addBook(book);
    }

//    @PutMapping("/{id}")
//    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
//        Book existingBook = books.stream().filter(b -> b.getId().equals(id)).findFirst().orElse(null);
//        if (existingBook != null) {
//            existingBook.setTitle(book.getTitle());
//            existingBook.setAuthor(book.getAuthor());
//            return existingBook;
//        }
//        return null;
//    }

    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book book){
        return bookServices.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        //books.removeIf(book -> book.getId().equals(id));
        bookServices.deleteBook(id);
    }
}
