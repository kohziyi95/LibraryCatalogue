package sg.edu.nus.iss.library.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.library.model.Book;

@Service
public class BookRepo implements RedisRepo {

    private Logger logger = Logger.getLogger(BookRepo.class.getName());
    private static final String BOOK_ENTITY = "bookList";

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Override
    public Book findById(String bookId) {
        Book resultById = (Book) redisTemplate.opsForHash().get(BOOK_ENTITY+ "_Map", bookId);
        logger.log(Level.INFO, "resultById >>" + resultById);
        return resultById;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        // List<Object> fromBookList = redisTemplate.opsForList().range(BOOK_ENTITY, 0, -1);
        // List<Book> books = (List<Book>) redisTemplate.opsForHash().multiGet(BOOK_ENTITY + "_Map", fromBookList)
        //                     .stream().filter(Book.class::isInstance).map(Book.class::cast).toList();
        List<Book> books = (List<Book>)(Object) redisTemplate.opsForHash().values(BOOK_ENTITY+"_Map");
        List<Book> findByAuthor = new ArrayList<Book>();
        for (Book book : books){

            if (book.getAuthor().toLowerCase().equals(author.toLowerCase())){
                findByAuthor.add(book);
                logger.log(Level.INFO, "Author found>> " + book.getAuthor());
            }
        }
        return findByAuthor;
    }

    @Override
    public List<Book> findByTitle(String title) {
        // List<Object> fromBookList = redisTemplate.opsForList().range(BOOK_ENTITY, 0, -1);
        // List<Book> books = (List<Book>) redisTemplate.opsForHash().multiGet(BOOK_ENTITY + "_Map", fromBookList)
        //                     .stream().filter(Book.class::isInstance).map(Book.class::cast).toList();
        List<Book> books = (List<Book>)(Object) redisTemplate.opsForHash().values(BOOK_ENTITY+"_Map");
        List<Book> findByTitle = new ArrayList<Book>();
        for (Book book : books){
            if (book.getTitle().toLowerCase().equals(title.toLowerCase())){
                findByTitle.add(book);
            }
        }
        return findByTitle;
    }

    @Override
    public List<Book> getAllBooks() {
        // List<Object> fromBookList = redisTemplate.opsForList().range(BOOK_ENTITY, 0, 10);
        // List<Book> books = (List<Book>) redisTemplate.opsForHash().multiGet(BOOK_ENTITY + "_Map",fromBookList)
        //                 .stream().filter(Book.class::isInstance).map(Book.class::cast).toList();
        List<Book> books = (List<Book>)(Object) redisTemplate.opsForHash().values(BOOK_ENTITY+"_Map");
        return books;
    }

    @Override
    public void storeBooks(Book book) {
        redisTemplate.opsForList().leftPush(BOOK_ENTITY, book);
        redisTemplate.opsForHash().put(BOOK_ENTITY + "_Map", book.getBookId(), book);
    }

}
