package MoneyAppServices;

import java.util.HashMap;
import java.util.Map;





public class CacheServiceSIM<T> implements CacheService<T> {
	
	/**
	 * Creation of generic map to store user,bank,card objs
	 */
	private Map<String, T> cache = new HashMap<String,T>();

	public CacheServiceSIM(Map<String,T> cache) {
		super();
		this.cache = cache;
	}
	

	public CacheServiceSIM() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Map<String, T> getCache() {
		return cache;
	}


	public void setCache(Map<String, T> cache) {
		this.cache = cache;
	}

	/**
	 * Generic method to call to add type to cache
	 */
	@Override
	public void addToCache(String key, T obj) {
		cache.put(key, obj);
	}

	/**
	 * Given map key, return type
	 * @return T
	 */
	@Override
	public T retrieveItemFromCache(String key) {
		
		if (cache.containsKey(key))
			return cache.get(key);
		else
			return null;		
	}
	 
}
