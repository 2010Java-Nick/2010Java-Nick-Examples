package MoneyAppServices;


public interface CacheService <T> {
	
	public void addToCache(String key, T obj);
	
	
	public T retrieveItemFromCache(String key);
	

}
