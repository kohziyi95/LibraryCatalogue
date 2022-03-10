package sg.edu.nus.iss.library.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.tomcat.util.http.parser.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.library.model.Book;
import sg.edu.nus.iss.library.service.BookRepo;
import sg.edu.nus.iss.library.service.RedisRepo;

@Controller
public class LibraryController {
    Logger logger = Logger.getLogger(LibraryController.class.getName());


    @Autowired
    private BookRepo service;

    @GetMapping("/")
    public String showSearchPage() {
        
        return "searchPage";
    }

    @GetMapping(value = "/search", params = "searchAuthor")
    public String searchAuthor(Model model, @RequestParam("search") String author) {
        List<Book> result = service.findByAuthor(author);
        logger.log(Level.INFO, "Search author result>> " + result);
        model.addAttribute("bookList", result);
        return "showBookList";
    }

    @GetMapping(value = "/search", params = "searchTitle")
    public String searchTitle(Model model, @RequestParam("search") String title) {
        List<Book> result = service.findByTitle(title);
        logger.log(Level.INFO, "Search title result>> " + result);
        model.addAttribute("bookList", result);
        return "showBookList";
    }


    @GetMapping(value = "/search", params = "searchId")
    public String getBook(Model model, @RequestParam("search") String bookId){
        Book result = service.findById(bookId);
    
        logger.log(Level.INFO, "result >> " + result.getBookId());
        logger.log(Level.INFO, "result >> " + result.getTitle());
        logger.log(Level.INFO, "result >> " + result.getAuthor());
        model.addAttribute("book", result);

        return "showBook";
    }

    @GetMapping(value = "/search", params = "getAllBooks")
    public String getAllBook(Model model){
        List<Book> result = service.getAllBooks();
        logger.log(Level.INFO, "result >> " + result);
        model.addAttribute("bookList", result);
        return "showBookList";
    }

}
