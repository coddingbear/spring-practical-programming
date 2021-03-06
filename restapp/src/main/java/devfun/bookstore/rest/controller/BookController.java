package devfun.bookstore.rest.controller;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import devfun.bookstore.common.domain.Book;
import devfun.bookstore.common.domain.BookList;
import devfun.bookstore.common.service.BookService;
import devfun.bookstore.rest.exception.ResourceNotFoundException;

// 프리젠테이션 계층 구현  - XML 표현
@Controller
@RequestMapping("/books")
public class BookController {
	private final Logger logger = LoggerFactory.getLogger(BookController.class); 
	
	@Autowired
	BookService bookService;
	
	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public BookList getBooks() {
		logger.info("getBooks ........................");
		List<Book> books = bookService.getBooks();
		return new BookList(books);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Book getBook(@PathVariable("id") Long id) {
		logger.info("getBook ........................");
		Book book = bookService.getBook(id);
		if (book == null) { // 도서 상세 정보를 찾을 수 없을 때
			throw new ResourceNotFoundException();
		}
		return book;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public Book createBook(@RequestBody Book book) {
		bookService.createBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book updateBook(@PathVariable("id") Long id, @RequestBody Book book) {
		bookService.updateBook(book);
		Book selectedBook = bookService.getBook(book.getId());
		return selectedBook;
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public Book deleteBook(@PathVariable("id") Long id) {
		bookService.deleteBook(id);
		Book deletedBook = new Book();
		deletedBook.setId(id);
		return deletedBook;
	}
	
	// 특정 Controller 수준에서 예외 처리하는 방법
//	@ExceptionHandler({ResourceNotFoundException.class})
//	public void handleException() {
//		
//	}
}












