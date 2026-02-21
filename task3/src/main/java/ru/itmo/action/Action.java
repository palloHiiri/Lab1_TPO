package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public interface Action {
    void happen(String name) throws IncorrectActionParticipantException;
}
