package ru.itmo.exception;

public class BombingNotResumedException extends Exception {
    public BombingNotResumedException() {
        super("Бомбардировка еще не возобновилась");
    }
}
