package com.liuhq7.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liuhq7.demo.mapper.UserMapper;
import com.liuhq7.demo.pojo.User;
import com.liuhq7.demo.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
