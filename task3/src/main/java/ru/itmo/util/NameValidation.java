package ru.itmo.util;

import ru.itmo.exception.IncorrectActionParticipantException;

public class NameValidation {
    public static void validateName(String actualName, String expectedName)
            throws IncorrectActionParticipantException {
        if (!actualName.equals(expectedName)) {
            throw new IncorrectActionParticipantException();
        }
    }
}
