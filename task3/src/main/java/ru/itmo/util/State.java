package ru.itmo.util;

import lombok.Getter;

@Getter
public enum State {
    DEFAULT(""),
    SITTING(" сидели. "),
    WAITING(" стали ждать конца."),
    PANIC(" сгрудились плотнее");

    State(String description) {
        this.description = description;
    }
    private final String description;
}
