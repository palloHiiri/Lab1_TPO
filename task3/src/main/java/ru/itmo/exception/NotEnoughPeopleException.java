package ru.itmo.exception;

public class NotEnoughPeopleException extends Exception {
    public NotEnoughPeopleException() {
        super("Недостаточно людей сидит в углу");
    }
}
