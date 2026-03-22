package ru.itmo.exception;

public class NotMeltedException extends Exception {
    public NotMeltedException() {
        super("Лицевая сторона еще не расплавилась: ");
    }
}
