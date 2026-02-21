package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionBeingUnimaginable implements Action {

    @Override
    public void happen(String name) throws IncorrectActionParticipantException {
        if(name.equals("Жара и шум")) {
            System.out.println(name + " были невообразимыми");
        }else {
            throw new IncorrectActionParticipantException();
        }
    }
}
