package com.panem.panem_backend.models;

import jakarta.persistence.*;
import lombok.Data;

/**
 * класс является сущностью которая будет храниться в базе данных.
 *
 * @author Anisimov Petr (tg: @petr_anisimov)
 * @Since 18.03.2024
 */
@Entity
@Table(name = "user_counter")
@Data
public class UserCounter {

    @Id //аннотация JPA, которая указывает, что поле id является первичным ключом.
    @GeneratedValue(strategy = GenerationType.AUTO)
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
