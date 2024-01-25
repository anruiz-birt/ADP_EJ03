package ejercicios;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import entidades.Author;
import entidades.Book;

public class DeleteBook {

	/**
	 * 4. OneToMany bidireccional entre entidades Book y Author
	 * Borra una Libro y no su autor
	 */
	public static void main(String[] args) {

		// crea sessionFactory y session
		StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder()
			    .configure( "hibernate.cfg.xml" )
			    .build();

		Metadata metadata = new MetadataSources( standardRegistry )
			    .addAnnotatedClass( Book.class )			  
			    .addAnnotatedClass( Author.class )
			    .getMetadataBuilder()
			    .build();

		SessionFactory sessionFactory = metadata.getSessionFactoryBuilder()
			    .build();    
		
		Session session = sessionFactory.openSession();
		
		try {			
			// crea un objeto Student
						
			int book_id = 3;
			
			Book dbBook = session.get(Book.class, book_id);
			// comienza la transacción
			session.beginTransaction();
		
			// borra el libro pero no su autor
			session.remove(dbBook);
			
			// hace commit de la transaccion
			session.getTransaction().commit();
					
			System.out.println("Libro borrado!");
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




