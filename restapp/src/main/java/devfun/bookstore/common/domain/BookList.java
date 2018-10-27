package devfun.bookstore.common.domain;
// 도서 정보 목록을 담을 BookList 클래스

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "books")
public class BookList {
	private List<Book> books;
	
	public BookList() { }
	
	public BookList(List<Book> books) {
		setBooks(books);
	}

	@XmlElement(name = "book")
	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
