package com.liuhq7.demo;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.liuhq7.demo.mapper.UserMapper;
import com.liuhq7.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPlusWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test01() {
        // 查询用户名包含‘1’，年龄在22到24之间，地址信息不为空的用户数据
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "1").between("age", 22, 24).isNotNull("address");
        List<User> result = userMapper.selectList(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test02() {
        // 查询用户数据，按照年龄的降序排序，若年龄相同，则按照id升序排列
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> result = userMapper.selectList(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test03() {
        // 删除年龄为空的用户数据
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("age");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test04() {
        // 修改性别为空的用户数据中的性别为女（使用QueryWrapper实现修改功能）
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("sex");
        User user = new User();
        user.setSex("女");
        int result = userMapper.update(user, queryWrapper);
        System.out.println(result);
    }

    @Test
    public void test05() {
        // 查询用户名中包含‘1’并且（年龄大于22或地址为空）的用户数据
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "1").and(i -> i.gt("age", 22).or().isNull("address"));
        List<User> result = userMapper.selectList(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test06() {
        // 查询所有用户数据的用户名和年龄
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");
        List<Map<String, Object>> result = userMapper.selectMaps(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test07() {
        // 查询用户id为10的用户数据（使用子查询实现）
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id <= 10").gt("id", 9);
        List<Map<String, Object>> result = userMapper.selectMaps(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test08() {
        // 修改性别为空的用户数据中的性别为女（使用UpdateWrapper实现修改功能）
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.isNull("sex").set("sex", "女");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }

    @Test
    public void test09() {
        // 查询用户名包含‘1’，年龄在22到24之间的用户数据（模拟开发过程中组装条件）
        String username = "1";
        Integer ageBegin = 22;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("name", username);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> result = userMapper.selectList(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test10() {
        // 查询用户名包含‘1’，年龄在22到24之间的用户数据（使用condition参数动态组装条件）
        String username = "1";
        Integer ageBegin = 22;
        Integer ageEnd = 24;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "name", username)
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        List<User> result = userMapper.selectList(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test11() {
        // 查询用户名包含‘1’，年龄在22到24之间的用户数据（使用LambdaQueryWrapper实现）
        String username = "1";
        Integer ageBegin = 22;
        Integer ageEnd = 24;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getName, username)
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        List<User> result = userMapper.selectList(queryWrapper);
        result.forEach(System.out::println);
    }

    @Test
    public void test12() {
        // 修改性别为空的用户数据中的性别为女（使用LambdaUpdateWrapper实现）
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.isNull(User::getSex).set(User::getSex, "女");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result);
    }
}
