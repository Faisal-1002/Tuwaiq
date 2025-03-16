package com.example.exam02.Service;

import com.example.exam02.Model.Book;
import com.example.exam02.Model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class BookService extends UserService{

    ArrayList<Book> books = new ArrayList<>();
    public ArrayList<Book> getAllBooks() {
        return books;
    }

    public boolean addBook(Book book) {
        for (Book book1 : books) {
            if (book1.getId().equals(book.getId())) {
                return false;
            }
        }
        books.add(book);
        return true;
    }

    public boolean updateBook(String id, Book book) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)){
                books.set(i, book);
                return true;
            }
        }
        return false;
    }

    public boolean deleteBook(String id) {
        for (Book book1 : books){
            if (book1.getId().equals(id)){
                books.remove(book1);
                return true;
            }
        }
        return false;
    }

    public Book searchBookName(String name) {
        for (Book book1 : books){
            if (book1.getName().equals(name))
                return book1;
        }
        return null;
    }

    public ArrayList<Book> getBooksByCategory(String category) {
        ArrayList<Book> booksByCategory = new ArrayList<>();
        for (Book book1 : books){
            if (book1.getCategory().equals(category))
                booksByCategory.add(book1);
        }
        return booksByCategory;
    }

    public ArrayList<Book> getBooksByNumberOfPages(int number_of_pages) {
        ArrayList<Book> booksByNumberOfPages = new ArrayList<>();
        for (Book book1 : books){
            if (book1.getNumber_of_pages()>=number_of_pages)
                booksByNumberOfPages.add(book1);
        }
        return booksByNumberOfPages;
    }

    public boolean changeBookStatus(String bookId, String userId){
        for (User user1 : users) {
            if (user1.getId().equals(userId) && user1.getRole().equals("librarian ")) {
                for (Book book1 : books) {
                    if (book1.getId().equals(bookId)) {
                        book1.setAvailable(false);
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
