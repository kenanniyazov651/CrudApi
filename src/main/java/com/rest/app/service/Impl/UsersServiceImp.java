package com.rest.app.service.Impl;

import com.rest.app.model.Users;
import com.rest.app.repository.UserRepo;
import com.rest.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsersServiceImp implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Override
    public List<Users> getAll() {
        List<Users> us=userRepo.getUsers();
        return us;

    }

    @Override
    public Users getById(int id) {
        Users users=userRepo.findById(id);
        return users;
    }

    @Override
    public boolean saveUser(Users users) {
       return userRepo.save(users);
    }

    @Override
    public boolean updateUser(Users users) {
        userRepo.update(users);
        return false;
    }

    @Override
    public boolean deleteUsers(int id) {
      return   userRepo.deleteById(id);
    }
}
