package ru.itmo.action;

import ru.itmo.exception.IncorrectActionParticipantException;

public class ActionBeingUnimaginable implements Action {

    @Override
    public String happen(String name) throws IncorrectActionParticipantException {
        String result = "";
        if(name.equals("Жара и шум")) {
            result = name + " были невообразимыми";
            return result;
        }else {
            throw new IncorrectActionParticipantException();
        }
    }
}
