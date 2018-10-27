package devfun.bookstore.common.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "book")
@XmlType(propOrder = {"id", "title", "creator", "type", "date"})
public class Book {
	
	private Long id;
	private String title;
	private String creator;
	private String type;
	private Date date;	
	
}
