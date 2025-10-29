package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    public Book patchBook(Long id, Book parcialBook){
    Optional<Book> bookOptional = bookRepository.findById(id);
    if(bookOptional.isPresent()){

        Book bookToUpdate = bookOptional.get();

        if(parcialBook.getAuthor() != null){
            bookToUpdate.setAuthor(parcialBook.getAuthor());
        }
        if(parcialBook.getTitle() != null){
            bookToUpdate.setTitle(parcialBook.getTitle());
        }
        return bookRepository.save(bookToUpdate);
    }else {
        return null;
        }
    }


}
