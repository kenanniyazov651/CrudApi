package com.rest.app.service.Impl;

import com.rest.app.model.Admin;
import com.rest.app.model.User1;
import com.rest.app.model.Users;
import com.rest.app.repository.UserRepo;
import com.rest.app.repository.UserRepo1;
import com.rest.app.service.UserService;
import com.rest.app.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServiceImp1 implements UserService1 {
    @Autowired
    private UserRepo1 userRepo;

    @Override
    public List<User1> getAll() {
        List<User1> us=userRepo.getUsers();
        return us;

    }

    @Override
    public User1 getById(int id) {
        User1 users=userRepo.findById(id);
        return users;
    }

    @Override
    public boolean saveUser(User1 users) {
        return userRepo.save(users);
    }

    @Override
    public boolean updateUser(User1 users) {
        userRepo.update(users);
        return false;
    }

    @Override
    public boolean deleteUsers(int id) {
        return   userRepo.deleteById(id);
    }

    @Override
    public Admin getAuth(String username,String password) {
     Admin admin1=userRepo.auth(username,password);
        return admin1;
    }
}
