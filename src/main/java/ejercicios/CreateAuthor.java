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

public class CreateAuthor {

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
			// crea un objeto Author
			System.out.println("Creando un nuevo objeto Author");
		
			Author author = createAuthor();
					
			// comienza la transacción
			session.beginTransaction();
			
			// guarda el objeto Author
			System.out.println("Guardando el autor");
			session.persist(author);
			
			// hace commit de la transaccion
			session.getTransaction().commit();
			
			System.out.println("Autor creado correctamente!");
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
	private static Author createAuthor() {
		Author tempAuthor = new Author();	
		tempAuthor.setFirstName("Nombreautor");
		tempAuthor.setLastName("Apellidoautor");
		tempAuthor.setNationality("nacionalidadautor");
		tempAuthor.setBirthDate(LocalDate.now());	
		return tempAuthor;		
	}
}










