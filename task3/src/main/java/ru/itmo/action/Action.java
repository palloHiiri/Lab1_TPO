package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.exception.NotTooHotException;

public interface Action {
    String happen() throws IncorrectActionParticipantException, NotTooHotException, NoStateForSituationException;
}
