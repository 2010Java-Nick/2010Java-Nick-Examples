package HotelReservationSpring.service;

import java.util.List;
import java.util.function.Predicate;

public interface CustomCacheService <T> {

	public void addToCache(T obj);
	
	public void removeFromCache(T obj);
	
	public T retrieveItem(T obj);
	
	public void emptyCache();
	
	public boolean contains(T obj);
	
	public void updateFromCache(T org, T upd);
	
	public List<T> retrieveAllItems();
	
	public List<T> retrieveMatching(Predicate<T> p);
	
}
