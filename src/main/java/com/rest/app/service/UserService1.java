package com.rest.app.service;

import com.rest.app.model.Admin;
import com.rest.app.model.User1;
import com.rest.app.model.Users;

import java.util.List;

public interface UserService1 {
    List<User1>getAll();
    public User1 getById(int id);
    public boolean saveUser(User1 users);
    public boolean updateUser(User1 users);
    public  boolean deleteUsers(int id);
    public Admin getAuth(String username ,String password);


}
