package com.example.h2id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "age")
    private int age;
    public User() {
    }
    public User(String username, String password, int age){
        this.username=username;
        this.password=password;
        this.age=age;
    }
    public long getId() {
        return id;
    }
    public String getName(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    public int getAge(){
        return age;
    }

    public void setName(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAge(int age) {
        this.age = age;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", password=" +
                password + ", age=" + age + "]";
    }


}