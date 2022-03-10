package sg.edu.nus.iss.library;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;	
import org.springframework.data.redis.core.RedisTemplate;


import sg.edu.nus.iss.library.model.Book;
import sg.edu.nus.iss.library.service.BookRepo;

@SpringBootApplication
public class LibraryCatalogueApplication {

	@Autowired
    RedisTemplate<String,Object> redisTemplate;

	@Autowired
    BookRepo service;

	Logger logger = Logger.getLogger(LibraryCatalogueApplication.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(LibraryCatalogueApplication.class, args);
	}

	@Bean
    CommandLineRunner runner(){
        return args ->{
            Integer count = (Integer)redisTemplate.opsForValue().get("count");
            if (count == null ){
                redisTemplate.opsForValue().set("count",1);
				Book book1 = new Book("1", "Ella the rose fairy", "Daisy Meadows" , "/images/ella_the_rose_fairy.jpg" );
				service.storeBooks(book1);
				logger.log(Level.INFO, "book 1 >> " + book1.getTitle());
				logger.log(Level.INFO, "book 1 >> " + book1.getBookId());
				logger.log(Level.INFO, "book 1 >> " + book1.getAuthor());

				Book book2 = new Book("2", "Harry Potter and the Philosopher's Stone", "J.K. Rowling" , "/images/harry-potter-p-stone.jpg" );
				service.storeBooks(book2);
				logger.log(Level.INFO, "book 2 >> " + book2.getTitle());
				logger.log(Level.INFO, "book 2 >> " + book2.getBookId());
				logger.log(Level.INFO, "book 2 >> " + book2.getAuthor());

				Book book3 = new Book("3", "The Haunted Tower", "Susannah Leigh" , "/images/the_haunted_tower.jpg" );
				service.storeBooks(book3);
				logger.log(Level.INFO, "book 3 >> " + book3.getTitle());
				logger.log(Level.INFO, "book 3 >> " + book3.getBookId());
				logger.log(Level.INFO, "book 3 >> " + book3.getAuthor());
            }
        };
	}
}
