package sk.upjs.nosql_redis.hash;

import java.util.List;

import sk.upjs.nosql_data_source.entity.SimpleStudent;
import sk.upjs.nosql_data_source.persist.DaoFactory;
import sk.upjs.nosql_data_source.persist.StudentDao;
import sk.upjs.nosql_redis.RedisFactory;

public class TestHash {
	
	public static void main(String[] args) {
		SimpleStudentDao redisDao = RedisFactory.INSTANCE.getSimpleStudentDao();
		StudentDao studentDao = DaoFactory.INSTANCE.getStudentDao();
		List<SimpleStudent> simpleStudents = studentDao.getSimpleStudents();
		redisDao.saveStudent(simpleStudents.get(0));
		redisDao.saveStudent(simpleStudents.get(1));
		System.out.println(redisDao.getAllStudents());
		redisDao.removeStudent(simpleStudents.get(0).getId());
		System.out.println(redisDao.getAllStudents());
		redisDao.removeAll();
		System.out.println(redisDao.getAllStudents());
	}

}
