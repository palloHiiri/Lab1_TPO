package ru.itmo.entity;

import ru.itmo.action.ActionDestroy;
import ru.itmo.exception.BombingNotResumedException;
import ru.itmo.exception.IncorrectActionParticipantException;

public class ComputerBank {

    public boolean isDestroyed;
    public String bankName;

    public ComputerBank(String bankName) {
        this.bankName = bankName;
        this.isDestroyed = false;
    }

    public void destroy(String bankName, Bombing bombing) throws BombingNotResumedException, IncorrectActionParticipantException {
        ActionDestroy action = new ActionDestroy();
        if(bombing.isResumed){
            action.happen(bankName);
            isDestroyed(true);
        }else {
            throw new BombingNotResumedException();
        }
    }

    public void isDestroyed(boolean destroyed) {
        isDestroyed = destroyed;
    }


}
