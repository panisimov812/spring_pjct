package com.panem.panem_backend.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity //аннотация JPA, которая указывает, что класс является сущностью, которая будет храниться в базе данных.
@Table(name = "user_counter")
@Data
public class UserCounter {

    @Id //аннотация JPA, которая указывает, что поле id является первичным ключом.
    @GeneratedValue(strategy = GenerationType.AUTO)
    //аннотация JPA, которая указывает, что значение поля id будет автоматически генерироваться.
    private Long id;
    private String account;
    private String name;
    private int value;

    @Override
    public String toString() {
        return "UserCounter{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
/*
Также можно настроить наименование таблицы в файле конфигурации, например,
application.properties или application.yml, используя параметры spring.jpa.hibernate.naming.physical-strategy или
spring.jpa.hibernate.naming.implicit-strategy.
 Настройки могут варьироваться в зависимости от версии Spring Boot и используемой базы данных.
 */