package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionMelted implements  Action {

    @Override
    public void happen(String name) throws IncorrectActionParticipantException {
        if(name.equals("Лицевая сторона его")){
            System.out.println(name + " почти вся расплавилась, ");
        } else{
            throw new IncorrectActionParticipantException();
        }
    }
}
