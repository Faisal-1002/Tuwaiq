package com.example.exam02.Controller;

import com.example.exam02.Api.ApiResponse;
import com.example.exam02.Model.Book;
import com.example.exam02.Model.User;
import com.example.exam02.Service.BookService;
import com.example.exam02.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/get")
    public ResponseEntity getAllBooks() {
        return ResponseEntity.status(200).body(bookService);
    }
    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody @Valid Book book, Errors errors) {
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (bookService.addBook(book)){
            return ResponseEntity.status(200).body(new ApiResponse("added"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("not added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateBook(@PathVariable String id, @RequestBody @Valid Book book, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        if (bookService.updateBook(id, book)){
            return ResponseEntity.status(200).body(new ApiResponse("updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(("not updated")));
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteBook(@PathVariable String id) {
        if (bookService.deleteBook(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Not deleted"));
    }
    @GetMapping("/searchbyname/{name}")
    public ResponseEntity searchBookByName(@PathVariable String name) {
        Book book1 = bookService.searchBookName(name);
        if (book1 == null)
            return ResponseEntity.status(400).body(new ApiResponse("null"));
        return ResponseEntity.status(200).body(book1);
    }
    @GetMapping("/getbooksbycategory/{category}")
    public ResponseEntity getBooksByCategory(@PathVariable String category){
        return ResponseEntity.status(200).body(bookService.getBooksByCategory(category));
    }
    @GetMapping("/getbooksbynumberofpages/{number_of_pages}")
    public ResponseEntity getBooksByNumberOfPages(@PathVariable int number_of_pages) {
        return ResponseEntity.status(200).body(bookService.getBooksByNumberOfPages(number_of_pages));
    }
//    @PutMapping("/updatebookstatus/{bookId}")
//    public ResponseEntity changeBookStatus(@PathVariable String bookId, @RequestBody User user){
//        if (bookService.changeBookStatus(bookId, user))
//            return ResponseEntity.status(200).body(new ApiResponse("status changed"));
//        return ResponseEntity.status(400).body(new ApiResponse("status did not changed"));
//    }
    @PutMapping("/updatebookstatus/{bookId}/{userId}")
    public ResponseEntity changeBookStatus(@PathVariable String bookId, @PathVariable String userId){
        if (bookService.changeBookStatus(bookId, userId))
            return ResponseEntity.status(200).body(new ApiResponse("status changed"));
        return ResponseEntity.status(400).body(new ApiResponse("status did not changed"));
    }
}
