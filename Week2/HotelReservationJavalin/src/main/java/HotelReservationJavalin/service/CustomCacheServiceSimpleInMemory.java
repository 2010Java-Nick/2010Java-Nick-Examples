package HotelReservationJavalin.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CustomCacheServiceSimpleInMemory <T> implements CustomCacheService<T> {
	
	private Set<T> cache = new HashSet<T>();
	
	public CustomCacheServiceSimpleInMemory(Set<T> cache) {
		super();
		this.cache = cache;
	}

	public CustomCacheServiceSimpleInMemory() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void setCache(Set<T> cache) {
		this.cache = cache;
	}

	@Override
	public void addToCache(T obj) {
		System.out.println("Adding object " + obj + " to cache");
		cache.add(obj);
	}

	@Override
	public void removeFromCache(T obj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T retrieveItem(T obj) {
		return null;
	}

	@Override
	public void emptyCache() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean contains(T obj) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void updateFromCache(T org, T upd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<T> retrieveAllItems() {
		// TODO Auto-generated method stub
		return new ArrayList<T>(cache);
	}

	@Override
	public List<T> retrieveMatching(Predicate<T> p) {
		return cache.stream().filter(p).collect(Collectors.toList());
	}
	
}
