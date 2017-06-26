package guavaproject.service;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import guavaproject.model.Person;

@Service
public class GuavaService {

	private LoadingCache<Integer, Person> guavacache;

	public GuavaService() {
		
		//init the cache builder, when app booting
		this.guavacache = CacheBuilder.newBuilder().recordStats()
				.build(new CacheLoader<Integer, Person>() {
					public Person load(Integer id) {
						return guavacache.getIfPresent(id);
					}
				});
		//and puting some person to that, to not be empty when strating 
		Person p1 = new Person(1, "Mihaly", 24);
		Person p2 = new Person(2, "Gergo", 22);
		Person p3 = new Person(3, "Miklos", 13);
		guavacache.put(p1.getId(), p1);
		guavacache.put(p2.getId(), p2);
		guavacache.put(p3.getId(), p3);
	}

	public LoadingCache<Integer, Person> getGuavacache() {
		return guavacache;
	}

	public void setGuavacache(LoadingCache<Integer, Person> guavacache) {
		this.guavacache = guavacache;
	}
	
	

}
