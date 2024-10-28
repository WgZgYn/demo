package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_id_gen")
    @SequenceGenerator(name = "account_id_gen", sequenceName = "account_id_seq", allocationSize = 1)
    @Column(name = "account_id", nullable = false)
    private Integer id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Column(name = "salt", nullable = false)
    private String salt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "created_at")
    private Instant createdAt;

    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "updated_at")
    private Instant updatedAt;

    @Column(name = "last_login")
    private Instant lastLogin;

    @ColumnDefault("'active'")
    @Column(name = "status", length = 50)
    private String status;

/*
TODO [逆向工程]会创建字段来映射 'role' 列
可用操作: 定义目标 Java 类型 | 按原样取消注释 | 移除列映射
    @ColumnDefault("'user'")
    @Column(name = "role", columnDefinition = "account_role not null")
    private Object role;
*/
}