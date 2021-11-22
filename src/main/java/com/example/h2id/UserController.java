package com.example.h2id;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    public static class _400_ErrorException extends ResponseStatusException {
        public _400_ErrorException() {
            super(HttpStatus.BAD_REQUEST, "bad_request");
        }
    }

    public static class _404_ErrorException extends ResponseStatusException {
        public _404_ErrorException() {
            super(HttpStatus.NOT_FOUND, "not found");
        }
    }

    public static class _409_ErrorException extends ResponseStatusException {
        public _409_ErrorException() {
            super(HttpStatus.CONFLICT, "conflict");
        }
    }

    private final List<User> users = new ArrayList<>();
    @GetMapping("users/{age}")
    public List<User> getUsers(@PathVariable("age") int age) {
        final List<User> usersage = new ArrayList<>();
        for (User user : users) {
            for (int j = age - 5; j <= age + 5; j++) {
                if (user.getAge() == j) {
                    usersage.add(user);
                }
            }
        }
        return usersage;
    }

    @PostMapping("users")
    public void addUser(@RequestBody String username, @RequestBody String password, @RequestBody int age,@RequestBody String repeatpassword) {
        try {
            if (users.size() >= 1) {
                for (User user : users) {
                    if (user.getName().equals(username)) {
                        throw new _409_ErrorException();
                    }
                }
            }
            if (!password.equals(repeatpassword)){
                throw new _400_ErrorException();
            }
            users.add(new User(username, password, age));
        } catch (_409_ErrorException e) {
            System.out.println("error409");
        }
        catch (_400_ErrorException e) {
            System.out.println("error400");
        }
    }
    @GetMapping("users/{id}")
    public User getUser(@PathVariable("id") long id) {
        int n = -1;
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId()==(id)) {
                    n = i;
                }
            }
            if (n == -1) {
                throw new _404_ErrorException();
            }
        } catch (_404_ErrorException e) {
            System.out.println("error404");
        }
        return users.get(n);
    }
    @DeleteMapping("users/{id}")
    public void DeleteUser(@PathVariable("id") long id) {
        int n = -1;
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId()==(id)) {
                    n = i;
                }
            }
            if (n == -1) {
                throw new _404_ErrorException();
            }
            users.remove(n);
        } catch (_404_ErrorException e) {
            System.out.println("error404");
        }
    }
    @PutMapping("users/{name}")
    public void updatePassword(@PathVariable("id") long id, @RequestBody String username, @RequestBody String password, @RequestBody int age,@RequestBody String repeatpassword){
        int n = -1;
        try {
            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getId()==(id)) {
                    n = i;
                }
            }
            if (n == -1) {
                throw new _404_ErrorException();
            }
            if (!password.equals(repeatpassword)){
                throw new _400_ErrorException();
            }
            users.get(n).setName(username);
            users.get(n).setPassword(password);
            users.get(n).setAge(age);
        } catch (_404_ErrorException e) {
            System.out.println("error404");
        } catch (_400_ErrorException e) {
            System.out.println("error400");
        }
    }
}
