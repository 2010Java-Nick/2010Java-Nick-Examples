package HotelReservation;

import java.lang.reflect.Field;

public class ReflectionExample {
	
	public static void main(String[] args) {
		
		String myString = "Happy Halloween";
		
		System.out.println("Inital value: " + myString);
		
		Class<String> string = String.class;
		
		try {
			Field field = string.getDeclaredField("value");
			field.setAccessible(true);
			field.set(myString, "Boo!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!".toCharArray());
			System.out.println("New value: " + myString);
			System.out.println("Happy Halloween");
		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
