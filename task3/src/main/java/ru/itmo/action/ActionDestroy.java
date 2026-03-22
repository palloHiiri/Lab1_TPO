package ru.itmo.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.entity.ComputerBank;
import ru.itmo.exception.IncorrectActionParticipantException;

@AllArgsConstructor
@Getter
@Setter
public class ActionDestroy implements  Action {
    private final ComputerBank computerBank;

    @Override
    public String happen() throws IncorrectActionParticipantException {
        if(computerBank.getBankName().equals("Компьютерный банк")){

            return (computerBank.getBankName() + " начал понемногу разваливаться на куски. ");
        }else {
            throw new IncorrectActionParticipantException();
        }
    }


}
