package com.smu.forum.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "accounts")
public class Account {
    @Id
//    @Column(name = "id", nullable = false)
    private Long id;

//    @Column(name = "username")
//    private String username;

//    @Column(name = "password")
    private String password;

//    @Column(name = "email")
    private String email;

//    @Column(name = "salt")
    private String salt;

//    @Column(name = "comfirm_code")
    private String confirmCode;

//    @Column(name = "activation_time")
    private LocalDateTime activationTime;

//    @Column(name = "is_vaild")
    private Byte isVaild;

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
