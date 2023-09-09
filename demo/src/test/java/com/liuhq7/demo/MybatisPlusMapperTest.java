package com.liuhq7.demo;

import com.liuhq7.demo.mapper.UserMapper;
import com.liuhq7.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user1 = new User();
        user1.setName("test001");
        user1.setPassword("test001");
        user1.setAddress("Beijing");
        user1.setAge(25);
        user1.setSex("男");
        user1.setDeptId(2);
        int result = userMapper.insert(user1);
        System.out.println(result);

        User user2 = new User();
        user2.setName("test002");
        user2.setPassword("test002");
        user2.setAddress("Beijing");
        user2.setAge(24);
        user2.setSex("女");
        user2.setDeptId(1);
        int result2 = userMapper.insert(user2);
        System.out.println(result2);
    }

    @Test
    public void testDelete(){
        int result = userMapper.deleteById(1);
        System.out.println(result);

        /*Map<String, Object> map = new HashMap<>();
        map.put("name", "test001");
        map.put("age", 25);
        int result = userMapper.deleteByMap(map);
        System.out.println(result);*/

        /*List<Integer> list = Arrays.asList(1, 2, 3);
        int result = userMapper.deleteBatchIds(list);
        System.out.println(result);*/
    }

    @Test
    public void testUpdate(){
        User user = new User();
        user.setId(1);
        user.setAge(25);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }

    @Test
    public void testSelect(){
        List<User> results = userMapper.selectList(null);
        results.forEach(System.out::println);

        /*User user = userMapper.selectById(1);
        System.out.println(user);*/

        /*Map<String, Object> map = new HashMap<>();
        map.put("address", "Beijing");
        map.put("age", "25");
        List results = userMapper.selectByMap(map);
        results.forEach(System.out::println);*/

        /*List<Integer> list = Arrays.asList(1, 2, 3);
        List<User> results = userMapper.selectBatchIds(list);
        results.forEach(System.out::println);*/
    }

    @Test
    public void testDIYMethod(){
        Map<String, Object> result = userMapper.selectMapById(1);
        System.out.println(result);
    }
}
