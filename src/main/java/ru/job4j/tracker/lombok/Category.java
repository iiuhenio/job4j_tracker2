package ru.job4j.tracker.lombok;

import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@RequiredArgsConstructor
@ToString
public class Category {
    @Getter
    @EqualsAndHashCode.Include
    @NonNull
    private int id;
    @Getter
    @Setter
    private String name;
}