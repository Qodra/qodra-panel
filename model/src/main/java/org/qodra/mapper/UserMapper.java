package org.qodra.mapper;

import org.qodra.dao.User;

public interface UserMapper {

    public User getById(String id);

    public User getUserByUsername(String username);

    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(User user);

}
