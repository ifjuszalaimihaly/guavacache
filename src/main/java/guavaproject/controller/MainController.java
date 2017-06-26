package guavaproject.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletRequest;

import org.eclipse.jdt.internal.compiler.util.HashtableOfObjectToIntArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;

import guavaproject.model.Person;
import guavaproject.service.GuavaService;

@Controller
public class MainController {
	
	@Autowired
	private GuavaService guavaservise;

	//getting home rute
	@GetMapping("/")
	public String home(HttpServletRequest request){
		goHome(request);
		return "index"; //redirect to index.jsp
		
	}
	
	//list people rute
	@GetMapping("/list-people")
	public String listPeople(HttpServletRequest request){
		listingPeople(request);
		return "index";
	}
	
	//route of app person form
	@GetMapping("/add-person")
	public String addPerson(HttpServletRequest request){
		//set the subpage mode
		request.setAttribute("mode", "MODE_ADD_PERSON");
		return "index";
	}
	
	//route of adding new person process
	@PostMapping("/add-person")
	public String savePerson(@ModelAttribute Person person, HttpServletRequest request){
		guavaservise.getGuavacache().put(person.getId(), person);
		listingPeople(request);
		return "index";
	}
	
	//route of delete one person form
	@GetMapping("/del-person")
	public String deletePerson(HttpServletRequest request){
		listingPeople(request);
		//redefine subpagemode
		request.setAttribute("mode", "MODE_DEL_PERSON");
		return "index";
	}
	
	
	//route of deleting one person process
	@PostMapping("/del-person")
	public String deletePersonById(@RequestParam Integer id, HttpServletRequest request){
		guavaservise.getGuavacache().invalidate(id);
		listingPeople(request);
		return "index";
	}
	
	//delete all people route
	@GetMapping("/del-all-people")
	public String deleteAllPeople(HttpServletRequest request){
		guavaservise.getGuavacache().invalidateAll();
		goHome(request);	
		return "index";
		
	}
	
	
	//preventing the code repeat
	private void listingPeople(HttpServletRequest request){
		//get the keyset to use getAllIfPresent method
		Set<Integer> keySet = guavaservise.getGuavacache().asMap().keySet();
		//get the people from the cache to a map, and increment hintcount statistics
		Map<Integer, Person> pMap = guavaservise.getGuavacache().getAllPresent(keySet);
		//convert to treemap, to order by id
		Map<Integer, Person> people = new TreeMap<>(pMap);
		//give person to the view
		request.setAttribute("people", people);		
		request.setAttribute("mode", "MODE_LIST_PEOPLE");		
	
	}
	
	//preventing the code repeat
	private void goHome(HttpServletRequest request){
		CacheStats statistics = guavaservise.getGuavacache().stats();
		//convert statistics to a hashmap, to can iterate it in the view
		Map<String, Number> stats = new HashMap<>();
		stats.put("hitcount", statistics.hitCount());
		stats.put("missCount", statistics.missCount());
		stats.put("loadCount", statistics.loadCount());
		stats.put("loadSuccessCount", statistics.loadSuccessCount());
		stats.put("loadExceptionCount", statistics.loadExceptionCount());
		stats.put("loadTIme", statistics.totalLoadTime());
		stats.put("evictionCount", statistics.evictionCount());
		request.setAttribute("mode", "MODE_HOME");
		//give the the stats to view
		request.setAttribute("stats", stats);
	}
	
	
	
	
}
