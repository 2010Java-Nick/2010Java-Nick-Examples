package HotelReservationSpring.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

//configuration can also be done in a spring-beans.xml file
@Configuration
@ComponentScan(value = "HotelReservationSpring")
public class AppConfig {

}
