package devfun.bookstore.common.domain;

import java.util.Date;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {
	
	private Long id;
	private String title;
	private String creator;
	private String type;
	private Date date;	
	
}
