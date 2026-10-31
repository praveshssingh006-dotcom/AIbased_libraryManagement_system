package com.LibraryManagement.service;

import com.LibraryManagement.dto.BookDto;
import com.LibraryManagement.entity.Book;
import com.LibraryManagement.repo.BookRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepo bookRepo;

    public Book getBookById(Long id) {
        return bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Book Not Found"));
    }

    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    public Book addBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());
        book.setIsAvailable(bookDto.getIsAvailable());
        book.setIsbn(bookDto.getIsbn());
        book.setQuantity(bookDto.getQuantity());
        return bookRepo.save(book);

    }

    public Book updateBook(Long id,BookDto bookDto) {
        Book savedBook =  bookRepo.findById(id).orElseThrow(()-> new RuntimeException("Book Not Found"));
        savedBook.setTitle(bookDto.getTitle());
        savedBook.setAuthor(bookDto.getAuthor());
        savedBook.setIsAvailable(bookDto.getIsAvailable());
        savedBook.setIsbn(bookDto.getIsbn());
        savedBook.setQuantity(bookDto.getQuantity());
        return bookRepo.save(savedBook);


    }

    public void deleteBook(Long id) {
        bookRepo.deleteById(id);
    }
}
