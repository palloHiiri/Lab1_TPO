package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FrontSide {
    private String name;

    public FrontSide(String name) {
        this.name = name;
    }


}
