package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionMelted implements  Action {

    @Override
    public String happen(String name) throws IncorrectActionParticipantException {
        if(name.equals("Лицевая сторона его ")){
            return (name + "почти вся расплавилась, ");
        } else{
            throw new IncorrectActionParticipantException();
        }
    }
}
