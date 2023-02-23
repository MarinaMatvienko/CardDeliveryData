package ru.netology;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data // Аннотация применяет несколько аннотаций Lombok
@RequiredArgsConstructor // Создаёт конструктор с требуемыми аргументами

public class UserInfo{
    private final String city;
    private final String data;
    private final String name;
    private final String phone;
}
