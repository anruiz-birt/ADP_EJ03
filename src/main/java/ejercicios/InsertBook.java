package ejercicios;

import java.time.LocalDate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidades.Author;
import entidades.Book;

public class InsertBook {

	/**
	 * 1. OneToMany bidireccional entre entidades Book y Author
	 */
	public static void main(String[] args) {

		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Book.class )			   
			    .addAnnotatedClass( Author.class)
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		
		
		try {			
			// crea un objeto Book
			System.out.println("Creando un nuevo objeto Book");
		
			Book book = new Book("tituloLibro",LocalDate.now(),"generoLibro");
					
			// comienza la transacción
			session.beginTransaction();
			
			Author dbAuthor = (Author)session.get(Author.class,2);
			
			book.setAuthor(dbAuthor);
			
			// guarda el objeto libro			
			session.persist(book);
			
			// hace commit de la transaccion
			session.getTransaction().commit();
			
			System.out.println("Libro creado correctamente!");
		}
		catch ( Exception e ) {
			// rollback ante alguna excepci n
			System.out.println("Realizando Rollback");
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
			sessionFactory.close();
		}
	}
}










