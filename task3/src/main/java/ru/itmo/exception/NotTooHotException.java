package ru.itmo.exception;

public class NotTooHotException extends Exception {
    public NotTooHotException() {
        super("Не достаточно горячий чтобы расплавиться");
    }
}
