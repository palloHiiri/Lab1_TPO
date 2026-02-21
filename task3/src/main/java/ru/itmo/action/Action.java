package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public interface Action {
    String happen(String name) throws IncorrectActionParticipantException;
}
