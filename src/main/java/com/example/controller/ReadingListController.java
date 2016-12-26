package com.example.controller;

import com.example.Property;
import com.example.entity.Book;
import com.example.service.ReadingListRepository;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by shichp on 2016/12/23.
 */
@Controller
@RequestMapping("/readingList")
@Slf4j(topic = "controller")
@Data
//@ConfigurationProperties(prefix="amazon")
public class ReadingListController {
    private ReadingListRepository readingListRepository;

    //private String associateId;

    @Autowired
    private Property property;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository){
        this.readingListRepository = readingListRepository;
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.GET)
    public String readerBooks(@PathVariable("reader") String reader, Model model){
        log.info("reader : " + reader);
        List<Book> readingList = readingListRepository.findByReader(reader);
        if (readingList != null && readingList.size() > 0){
            model.addAttribute("books",readingList);
        }else {
            Book book = new Book();
            book.setTitle("SpringBoot实战");
            book.setReader(reader);
            book.setAuthor("shichp");
            book.setDescription("springboot开发介绍");
            book.setIsbn("99937-0-014-2");
            readingList.add(book);
            model.addAttribute("books",readingList);
        //    model.addAttribute("amazonID", associateId);
            model.addAttribute("amazonID", property.getAssociateId());
        }

        model.addAttribute("host", "http://blog.didispace.com");

        return "readingList";
    }

    @RequestMapping(value = "/{reader}",method = RequestMethod.POST)
    public String addToReadingList(@PathVariable("reader") String reader, Book book){
        book.setReader(reader);
        readingListRepository.save(book);
        return  "redirect:/readingList/{reader}";
    }





}
