package sg.edu.nus.iss.library.service;

import java.util.List;

import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.library.model.Book;

@Repository
public interface RedisRepo {
    public List<Book> findByAuthor(String author);
    public List<Book> findByTitle(String title);
    public Book findById(String Id);
    // public List<Book> sortByAuthorAsc(List<Book> Catalogue);
    // public List<Book> sortByTitleAsc(List<Book> Catalogue);
    // public List<Book> sortByAuthorDesc(List<Book> Catalogue);
    // public List<Book> sortByTitleDesc(List<Book> Catalogue);
    public List<Book> getAllBooks();
    public void storeBooks(Book book);
}
