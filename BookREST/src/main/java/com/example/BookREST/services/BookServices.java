package com.example.BookREST.services;

/*
import com.example.BookREST.model.Book;
import com.example.BookREST.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;

    public List<Book> getAllBook(){
        return bookRepository.findAll();
    }

    public Book getBook(int id){
        Optional<Book> temp = bookRepository.findById(id);

        Book book = null;

        if(temp.isEmpty()){
            throw new RuntimeException("Book of this id : {"+ id +"} not present");
        } else {
            book = temp.get();
        }

        return book;
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    //Update a player
    public Book updateBook(int id, Book b){
        //get Book Object by ID
        Book book = bookRepository.getOne(id);

        //update book details using b
        book.setTitle(b.getTitle());
        book.setAuthor(b.getAuthor());

        //save update book
        return bookRepository.save(book);
    }

    public void deleteBook(int id){
        Optional<Book> temp = bookRepository.findById(id);

        Book book = null;

        if(temp.isEmpty()){
            throw new RuntimeException("Book of this id : {"+ id +"} not present");
        } else {
            book = temp.get();
        }

         bookRepository.delete(book);
    }

}
*/


import com.example.BookREST.dao.BookDAO;
import com.example.BookREST.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    private BookDAO bookDAO;

    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public Book getBookById(int id) {
        return bookDAO.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book with ID: " + id + " not found"));
    }

    public Book addBook(Book book) {
        return bookDAO.saveBook(book);
    }

    public Book updateBook(int id, Book updatedBook) {
        Book book = bookDAO.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book with ID: " + id + " not found"));
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        return bookDAO.saveBook(book);
    }

    public void deleteBook(int id) {
        Book book = bookDAO.getBookById(id)
                .orElseThrow(() -> new RuntimeException("Book with ID: " + id + " not found"));
        bookDAO.deleteBook(book);
    }
}
