package com.example.our_first_api.services;

import com.example.our_first_api.exceptions.ResourceNotFoundException;
import com.example.our_first_api.models.Book;
import com.example.our_first_api.models.dto.BookDTO;
import com.example.our_first_api.models.dto.CreateBookDTO;
import com.example.our_first_api.models.dto.UpdateBookDTO;
import com.example.our_first_api.models.mapper.BookMapper;
import com.example.our_first_api.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(BookMapper::toBookDTO).toList();
    }

    public BookDTO getBookById(long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toBookDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    public BookDTO createBook(CreateBookDTO bookDTO) {
        Book newBook = new Book();
        newBook.setTitle(bookDTO.getTitle());
        newBook.setAuthor(bookDTO.getAuthor());
        newBook.setIsbn(bookDTO.getIsbn());
        return BookMapper.toBookDTO(bookRepository.save(newBook));
    }

    public BookDTO updateBook(long id, UpdateBookDTO bookDTO) {
        Book toUpdate = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        toUpdate.setTitle(bookDTO.getTitle());
        toUpdate.setAuthor(bookDTO.getAuthor());
        toUpdate.setIsbn(bookDTO.getIsbn());
        return BookMapper.toBookDTO(bookRepository.save(toUpdate));
    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}
