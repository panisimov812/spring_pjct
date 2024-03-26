package com.panem.panem_backend.models;

import jakarta.persistence.*;
import lombok.Data;

/**
 * класс является сущностью которая будет храниться в базе данных.
 *
 * @author Anisimov Petr (tg: @petr_anisimov)
 * @Since 24.03.2024
 */
@Entity
@Table(name = "user_attribute")
@Data
public class UserAttribute {

    @Id //аннотация JPA, которая указывает, что поле id является первичным ключом.
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String account;
    private int type;
    private String name;
    private int value;

    @Override
    public String toString() {
        return "UserAttribute{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
