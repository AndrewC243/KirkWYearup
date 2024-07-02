package com.example.our_first_api.models.mapper;

import com.example.our_first_api.models.Book;
import com.example.our_first_api.models.dto.BookDTO;
import com.example.our_first_api.models.dto.CreateBookDTO;
import com.example.our_first_api.models.dto.UpdateBookDTO;

public class BookMapper {
    public static Book toEntity(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        return book;
    }

    public static Book toEntity(CreateBookDTO createBookDTO) {
        Book book = new Book();
        book.setTitle(createBookDTO.getTitle());
        book.setAuthor(createBookDTO.getAuthor());
        book.setIsbn(createBookDTO.getIsbn());
        return book;
    }

    public static Book toEntity(UpdateBookDTO updateBookDTO) {
        Book book = new Book();
        book.setTitle(updateBookDTO.getTitle());
        book.setAuthor(updateBookDTO.getAuthor());
        book.setIsbn(updateBookDTO.getIsbn());
        return book;
    }

    public static BookDTO toBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle(book.getTitle());
        bookDTO.setAuthor(book.getAuthor());
        bookDTO.setIsbn(book.getIsbn());
        return bookDTO;
    }
}
