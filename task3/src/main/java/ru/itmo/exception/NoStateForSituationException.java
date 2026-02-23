package ru.itmo.exception;

public class NoStateForSituationException extends Exception {
    public NoStateForSituationException() {
        super("Нет состояния для данной ситуации");
    }
}
