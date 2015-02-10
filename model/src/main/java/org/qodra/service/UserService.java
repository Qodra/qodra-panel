package org.qodra.service;

import com.google.common.base.Preconditions;
import org.apache.ibatis.session.SqlSession;
import org.qodra.dao.User;
import org.qodra.database.MyBatisUtil;
import org.qodra.exception.NotAuthorizedException;
import org.qodra.mapper.UserMapper;
import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.util.UUID;

public class UserService {

    public User getUserByUsername(String username) {
        Preconditions.checkNotNull(username);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        User result = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            result = userMapper.getUserByUsername(username);

        } finally {
            sqlSession.close();
        }

        return result;
    }

    public User login(User user) {
        Preconditions.checkNotNull(user, user.getUsername(), user.getPassword());

        User result = getUserByUsername(user.getUsername());
        String encriptedPassword = encrypt(user.getPassword());

        if (user != null && !encriptedPassword.equals(result.getPassword())) {
            throw new NotAuthorizedException();
        }

        return result;
    }

    public User createUser(User user) {
        Preconditions.checkNotNull(user);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        User result = null;
        try {
            user.setId(UUID.randomUUID().toString());
            user.setPassword(encrypt(user.getPassword()));
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.createUser(user);
            result = userMapper.getUserByUsername(user.getUsername());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return result;
    }

    public User updateUser(User user) {
        Preconditions.checkNotNull(user);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        User result = null;
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            userMapper.updateUser(user);
            result = userMapper.getById(user.getId());
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }

        return result;

    }

    public void deleteUser(User user) {
        Preconditions.checkNotNull(user);
        SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
        try {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User result = userMapper.getUserByUsername(user.getUsername());
            userMapper.deleteUser(result);
            sqlSession.commit();
        } finally {
            sqlSession.close();
        }


    }

    private synchronized String encrypt(String password) {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA");
            md.update(password.getBytes("UTF-8"));
            byte raw[] = md.digest();
            String hash = (new BASE64Encoder()).encode(raw);
            return hash;
        } catch (Exception e) {

        }

        return password;
    }
}
