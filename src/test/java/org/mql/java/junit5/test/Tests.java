package org.mql.java.junit5.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mql.java.junit5.livre.exception.BookNotFoundException;
import org.mql.java.junit5.livre.model.Book;
import org.mql.java.junit5.livre.service.BookService;

@TestInstance(Lifecycle.PER_CLASS)
public class Tests {
	
	private BookService bookService;
	private Book headFirstJavaBook;
	private Book headFirstDesignPatternBook;
	
	@BeforeAll
	public void beforeAll() {
		bookService = new BookService();
		headFirstJavaBook = new Book("1", "Head First Java", "Wrox");
		headFirstDesignPatternBook = new Book("2", "Head First Design Pattern", "Packt");
		bookService.addBook(headFirstJavaBook);
		bookService.addBook(headFirstDesignPatternBook);
	}
	
	@AfterAll
	public void afterAll() {
		System.out.println("Tous les tests réussis! (^-^ ! ");
	}
	
	@BeforeEach
	public void beforeEach() {
		System.out.println(">>> Le début du test  :");
	}
	
	@AfterEach
	public void afterEach() {
		System.out.println("Le test passé avec succès !");
	}
	
	@Test
	public void assertion() {
		List<Book> list = bookService.getList();

		Book actualBook = bookService.getById("1");
		
		assertFalse(list.isEmpty());
		assertNotNull(actualBook);
		assertEquals("Head First Java", actualBook.getTitle(), "Book title didnt match!");
	}
	
	@Test
	public void exception() {
		BookNotFoundException bookNotFoundException = assertThrows(BookNotFoundException.class, 
				() -> bookService.getByTitle("Head First Spring"));

		assertEquals("Book not found in Bookstore!", bookNotFoundException.getMessage());
	}
	
	@Test
	public void exceptions() {
		BookNotFoundException bookNotFoundException = assertThrows(BookNotFoundException.class, 
				() -> bookService.getByTitle("Head First Spring"));

		assertEquals("Book not found in Bookstore!", bookNotFoundException.getMessage());
	}
}
