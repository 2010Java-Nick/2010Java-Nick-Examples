package HotelReservationSpring.config;

import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import HotelReservationSpring.util.SessionFactoryUtil;

//configuration can also be done in a spring-beans.xml file
@Configuration
@ComponentScan(value = "HotelReservationSpring")
@EnableAspectJAutoProxy
@EnableWebMvc
@EnableTransactionManagement // Enables @Transactional part of Spring ORM [P2OFFLIMITS]
public class AppConfig implements WebApplicationInitializer {

	@Autowired
	SessionFactory sessionFactory;

	@Override
	public void onStartup(ServletContext container) {
		// create the 'root' Spring Application Context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(AppConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// create dispatcher servlet's context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();

		// register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet("dispatcher",
				new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

	}

	@Bean()
	@Scope(value = "singleton")
	public SessionFactory sessionFactory() {
		return SessionFactoryUtil.getSessionFactoryUtil().getSessionFactory();
	}

	// Set up our Spring ORM Configuration [P2OFFLIMITS]
	@Bean
	public BasicDataSource myDataSource() {
		BasicDataSource data = new BasicDataSource();
		data.setUrl(System.getenv("HOTEL_URL"));
		data.setUsername(System.getenv("HOTEL_USERNAME"));
		data.setPassword(System.getenv("HOTEL_PASSWORD"));
		data.setDriver(new org.postgresql.Driver());
		return data;
	}
	
	@Bean
	public LocalSessionFactoryBean mySessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		sessionFactory.setDataSource(myDataSource());
		sessionFactory.setPackagesToScan("HotelReservationSpring");
		Properties props = new Properties();
		props.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
		props.setProperty("hibernate.show_sql", "true");
		props.setProperty("hibernate.format_sql", "true");
		sessionFactory.setHibernateProperties(props);
		
		return sessionFactory;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager tx = new HibernateTransactionManager();
		tx.setSessionFactory(mySessionFactory().getObject());
		
		return tx;
	}

	/*
	 * @Bean
	 * 
	 * @Scope(value = "prototype") public Logger logger(final InjectionPoint ip) {
	 * return LoggerFactory.getLogger(of(ip.getMethodParameter())
	 * .<Class>map(MethodParameter::getContainingClass) .orElseGet( () ->
	 * ofNullable(ip.getField()) .map(Field::getDeclaringClass) .orElseThrow
	 * (IllegalArgumentException::new))); }
	 */

	// Bean wiring
	/*
	 * @Bean(autowireCandidate = false) public GuestDao guestDaoHibernate() {
	 * GuestDaoHibernate guestDao = new GuestDaoHibernate();
	 * guestDao.setSessionFactory(sessionFactory); return guestDao; }
	 */

}
