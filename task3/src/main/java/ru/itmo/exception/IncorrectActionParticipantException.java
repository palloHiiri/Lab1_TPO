package ru.itmo.exception;

public class IncorrectActionParticipantException extends Exception {
  public IncorrectActionParticipantException() {
    super("Неверный участник действия");
  }
}
