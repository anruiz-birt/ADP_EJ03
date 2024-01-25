package entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table (name="author")
public class Author {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column (name="id")
	private int id;
	
	@Column (name= "first_name")
	private String firstName;
	
	@Column (name= "last_name")
	private String lastName;
	
	@Column(name="birth_date")
	private LocalDate birthDate;
	
	@Column(name="nationality")
	private String nationality;
	
	@OneToMany (mappedBy="author", cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	//se excluye CascadeType.REMOVE
	List <Book> books = new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
}
