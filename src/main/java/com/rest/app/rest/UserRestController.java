package com.rest.app.rest;

import com.rest.app.model.Product;
import com.rest.app.model.Users;
import com.rest.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UserRestController {
    @Autowired
    private UserService service;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<Users>> getAllCustomers() {
        List<Users> usersList = this.service.getAll();

        if (usersList.isEmpty()) {
            return new ResponseEntity<List<Users>>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<List<Users>>(usersList, HttpStatus.OK);
    }



    @RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Users> getUser(@PathVariable("id") int Id) {
        if (Id == 0) {
            return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
        }

        Users users = (Users)this.service.getById(Id);

        if (users == null) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Users>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Users> saveUser(@RequestBody @Valid Users users) {
        HttpHeaders headers = new HttpHeaders();

        if (users == null) {
            return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
        }

        this.service.saveUser(users);
        return new ResponseEntity<Users>(users, headers, HttpStatus.CREATED);
    }

  @RequestMapping(value = "{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Users> updateUser(@RequestBody  Users users, @PathVariable("id") int id) {
        HttpHeaders headers = new HttpHeaders();

        if (users == null) {
            return new ResponseEntity<Users>(HttpStatus.BAD_REQUEST);
        }

users.setId(id);
      System.out.println("useri  "+users.toString());
      System.out.println("Idisi   "+ id);
        this.service.updateUser(users);

        return new ResponseEntity<Users>(users, headers, HttpStatus.OK);
    }
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Users> deleteUser(@PathVariable("id") int id) {
        Users users = this.service.getById(id);

        if (users == null) {
            return new ResponseEntity<Users>(HttpStatus.NOT_FOUND);
        }

        this.service.deleteUsers(id);

        return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
    }

}
