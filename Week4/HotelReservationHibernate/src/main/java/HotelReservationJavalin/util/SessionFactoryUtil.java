package HotelReservationJavalin.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import HotelReservationJavalin.pojos.Guest;

public class SessionFactoryUtil {
	
	private static SessionFactoryUtil sfu;
	
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}



	private SessionFactoryUtil() {
		
		if (sessionFactory == null) {
			
			Map<String, String> settings = new HashMap<>();
			settings.put("hibernate.connection.url", System.getenv("HOTEL_URL"));
			settings.put("hibernate.connection.username", System.getenv("HOTEL_USERNAME"));
			settings.put("hibernate.connection.password", System.getenv("HOTEL_PASSWORD"));
			settings.put("hibernate.connection.driver_class", "org.postgresql.Driver");
			settings.put("hibernate.connection.dialect", "org.hibernate.dialect.PostgreSQLDialect");
			
			StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();
			
			Metadata metadata = new MetadataSources(standardRegistry)
						.addAnnotatedClass(Guest.class)
						.getMetadataBuilder()
						.applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE).build();
			
			sessionFactory = metadata.getSessionFactoryBuilder().build();
			
		}
		
	}
	
	public static SessionFactoryUtil getSessionFactoryUtil() {
		if (sfu == null) {
			sfu = new SessionFactoryUtil();
		}
		
		return sfu;
	}

}
