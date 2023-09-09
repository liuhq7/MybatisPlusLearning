package com.liuhq7.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liuhq7.demo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface UserMapper extends BaseMapper<User> {
    /**
     * 自定义方法，根据id查询用户信息map
     * @param id
     * @return 用户信息map
     */
    Map<String, Object> selectMapById(Integer id);
}
