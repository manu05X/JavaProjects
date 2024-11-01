package com.example.BookREST.services;

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
