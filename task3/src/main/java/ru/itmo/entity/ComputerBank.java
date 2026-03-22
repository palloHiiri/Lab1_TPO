package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionDestroy;
import ru.itmo.exception.BombingNotResumedException;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.util.NameValidation;

@Getter
@Setter
public class ComputerBank {

    private boolean isDestroyed;
    private String bankName;

    public ComputerBank(String bankName) {
        this.bankName = bankName;
        this.isDestroyed = false;
    }

    public String destroy(Bombing bombing) throws BombingNotResumedException, IncorrectActionParticipantException {
        NameValidation.validateName(bankName, "Компьютерный банк");
        ActionDestroy action = new ActionDestroy(this);
        if(bombing.isResumed()){
            setDestroyed(true);
            return action.happen();

        }else {
            throw new BombingNotResumedException();
        }
    }


}


