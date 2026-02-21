package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionDestroy implements  Action {

    @Override
    public void happen(String name) throws IncorrectActionParticipantException {
        if(name.equals("Компьютерный банк ")){

            System.out.println(name + "начал понемногу разваливаться на куски");
        }else {
            throw new IncorrectActionParticipantException();
        }
    }


}
