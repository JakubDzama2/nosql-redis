package sk.upjs.nosql_redis.hash;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import sk.upjs.nosql_data_source.entity.SimpleStudent;

public class SimpleStudentDao {

	private static final String KEY = "SimpleStudent_JDZ";
	private HashOperations<String, Long, SimpleStudent> hashOps;
	private RedisTemplate<String, SimpleStudent> redisTemplate;
	
	public SimpleStudentDao(RedisTemplate<String, SimpleStudent> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOps = redisTemplate.opsForHash();
	}
	
	public void saveStudent(SimpleStudent s) {
		hashOps.put(KEY, s.getId(), s);
	}
	
	public SimpleStudent getStudentById(Long id) {
		return hashOps.get(KEY, id);
	}
	
	public Map<Long, SimpleStudent> getAllStudentsMap() {
		return hashOps.entries(KEY);
	}
	
	public List<SimpleStudent> getAllStudents() {
		return new ArrayList(getAllStudentsMap().values());
	}
	
	public void removeStudent(Long id) {
		hashOps.delete(KEY, id);
	}
	
	public void removeAll() {
		redisTemplate.delete(KEY);
	}
	
}
