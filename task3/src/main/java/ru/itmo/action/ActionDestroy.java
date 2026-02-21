package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionDestroy implements  Action {

    @Override
    public String happen(String name) throws IncorrectActionParticipantException {
        if(name.equals("Компьютерный банк")){

            return (name + " начал понемногу разваливаться на куски");
        }else {
            throw new IncorrectActionParticipantException();
        }
    }


}
