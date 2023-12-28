package cn.edu.qfnu.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_user")
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "f_id")
    private Integer id;

    /**
     * 用户名
     */
    @Column(name = "f_username", unique = true, nullable = false, length = 16)
    private  String username;

    /**
     * 登录密码
     */
    @Column(name = "f_password", length = 32, nullable = false)
    private  String password;

    /**
     * 姓名
     */
    @Column(name = "f_name", length = 64)
    private String name;

    /**
     * 年龄
     */
    @Column(name = "f_age")
    private Integer age;

    /**
     * 手机号
     */
    @Column(name = "f_phone", length = 11)
    private String phone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
