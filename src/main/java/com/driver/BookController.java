package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    private List<Book> bookList;
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        book.setId(id);
        bookList.add(book);
        id++;
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable String id){
        int idd = Integer.parseInt(id);
        Book book = null;
        for (Book book1 : bookList){
            if(book1.getId()==idd){
                book = book1;
                break;
            }
        }
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable String id){
        int idd = Integer.parseInt(id);
        for(int i=0;i<bookList.size();i++){
            if(bookList.get(i).getId()==idd){
                bookList.remove(i);
                break;
            }
        }
        return new ResponseEntity<>("Book with id="+ idd +" deleted Successfully", HttpStatus.CREATED);
    }

    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookList, HttpStatus.CREATED);
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity<String> deleteAllBooks(){
        bookList.clear();
        return new ResponseEntity<>("Books were deleted successfully", HttpStatus.CREATED);
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String author){
        List<Book> book = new ArrayList<>();
        for (Book book1 : bookList){
            if(book1.getAuthor().equals(author)){
                book.add(book1);
            }
        }
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam String genre){
        List<Book> book = new ArrayList<>();
        for (Book book1 : bookList){
            if(book1.getGenre().equals(genre)){
                book.add(book1);
            }
        }
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
