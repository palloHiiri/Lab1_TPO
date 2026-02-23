package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.exception.NotTooHotException;

public interface Action {
    String happen(String name) throws IncorrectActionParticipantException, NotTooHotException, NoStateForSituationException;
}
