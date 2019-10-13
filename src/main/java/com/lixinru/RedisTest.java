package com.lixinru;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:redis.xml")
public class RedisTest {
	@Autowired
	RedisTemplate redisTemplate;
	
	/**
	 * 
	    * @Title: testRedisString
	    * @Description: 测试String类型
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	@Test
	public void testRedisString() {
		
		//redisTemplate.opsForValue().set("1705", "hello redis", 5, TimeUnit.SECONDS);
		//redisTemplate.opsForValue().set("spring_redis", "hello redis!");
		//redisTemplate.opsForValue().append("spring_redis", "1705E");
		
		String value = (String) redisTemplate.opsForValue().get("spring_redis");
		//String value = redisTemplate.opsForValue().get("1705");
		System.out.println(value);
	}
	
	/**
	 * 
	    * @Title: testRedisString
	    * @Description: 测试list类型
	    * @param     参数
	    * @return void    返回类型
	    * @throws
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testRedisList() {
//		redisTemplate.opsForList().leftPush("mylist", "aaa");
//		String ele = redisTemplate.opsForList().rightPop("mylist");
//		System.out.println(ele);
		
		ArrayList<Types> types=new ArrayList<Types>();
		
		Types t1=new Types();
		t1.setId(001);
		t1.setName("张三");
		Types t2=new Types();
		t2.setId(002);
		t2.setName("李四");
		Types t3=new Types();
		t3.setId(003);
		t3.setName("王五");
		
		types.add(t1);
		types.add(t2);
		types.add(t3);
		
		redisTemplate.opsForList().leftPush("mylist", types);
		ArrayList<Types> redisList = (ArrayList<Types>) redisTemplate.opsForList().rightPop("mylist");
		for (Types types2 : redisList) {
			System.out.println(types2);
		}
 }
	
	
		/**
		 * 
		    * @Title: testRedisHash
		    * @Description: 测试hash类型
		    * @param     参数
		    * @return void    返回类型
		    * @throws
		 */
		@SuppressWarnings("unchecked")
		@Test
		public void testRedisHash() {
			Map<String,Types> map=new HashMap<String,Types>();
				Types t1=new Types();
				t1.setId(001);
				t1.setName("张三");
				Types t2=new Types();
				t2.setId(002);
				t2.setName("李四");
				Types t3=new Types();
				t3.setId(003);
				t3.setName("王五");
				
				map.put(1+"",t1);
				map.put(2+"",t2);
				map.put(3+"",t3);
				
			redisTemplate.opsForHash().putAll("myhash", map);
			Map entries = redisTemplate.opsForHash().entries("myhash");
			Set entrySet = entries.entrySet();
			for (Object object : entrySet) {
				System.out.println(object);
			}
		}
		

		/**
		 * 
		    * @Title: testRedisSet
		    * @Description: 测试set类型
		    * @param     参数
		    * @return void    返回类型
		    * @throws
		 */
		@SuppressWarnings("unchecked")
		@Test
		public void testRedisSet() {
			
			Types t1=new Types();
			t1.setId(001);
			t1.setName("张三");
			Types t2=new Types();
			t2.setId(002);
			t2.setName("李四");
			Types t3=new Types();
			t3.setId(003);
			t3.setName("王五");
			
			
			redisTemplate.opsForSet().add("myset", t1,t2,t3);
			Set members = redisTemplate.opsForSet().members("myset");
			for (Object object : members) {
				System.out.println(object);
			}
		}
}