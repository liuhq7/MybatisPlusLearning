package com.liuhq7.demo;

import com.liuhq7.demo.pojo.User;
import com.liuhq7.demo.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisPlusServiceTest {
    @Autowired
    private IUserService userService;

    @Test
    public void testGetCount() {
        long result = userService.count();
        System.out.println(result);
    }

    @Test
    public void testInsertMore() {
        List<User> list = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            User user = new User();
            user.setName("test" + "00" + i);
            user.setPassword("test" + "00" + i);
            list.add(user);
        }
        boolean result = userService.saveBatch(list);
        System.out.println(result);
    }
}
