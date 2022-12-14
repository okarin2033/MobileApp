package ru.project.mobile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.project.mobile.dto.Book;
import ru.project.mobile.dto.BookSimple;
import ru.project.mobile.parsing.ParsingFullBook;
import ru.project.mobile.parsing.ParsingServiceMainPage;
import ru.project.mobile.parsing.ParsingServiceSearch;

import java.util.List;

@RestController
@RequestMapping("/book")
public class ContentController {
    @Autowired
    ParsingServiceMainPage parsingServiceMainPage;
    @Autowired
    ParsingServiceSearch parsingServiceSearch;
    @Autowired
    ParsingFullBook parsingFullBook;

    @GetMapping("/new")
    public List<BookSimple> getNewBooks(){
        return parsingServiceMainPage.getNewBooks();
    }
    @GetMapping("/best")
    public List<BookSimple> getBestBooks(){
        return parsingServiceMainPage.getBestBooks();
    }
    @GetMapping("/search/{name}")
    public List<BookSimple> getBestBooks(@PathVariable String name){
        return parsingServiceSearch.getSearchRequest(name);
    }
    @GetMapping("/get/{url}")
    public Book getBookWithUrl(@PathVariable String url){
        System.out.println("url = "+ url);
        return parsingFullBook.getBookData(url);
    }

}

