package ru.job4j.tracker.model;

import lombok.Data;
import javax.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "items")
@Data
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private LocalDateTime created = LocalDateTime.now();

    public Item() {
    }

    public Item(int i, String name, LocalDateTime now) {
    }
}