package ru.job4j.tracker.lombok;

import ru.job4j.tracker.model.Item;

import java.time.LocalDateTime;

public class LombokUsage {
    public static void main(String[] args) {
        var bird = new BirdData();
        bird.setAge(1);
        System.out.println(bird);

        var item = new Item(1, "name", LocalDateTime.now());
        item.setName("newName");
        System.out.println(item);
    }
}