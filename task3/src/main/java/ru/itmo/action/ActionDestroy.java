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
         return (computerBank.getBankName() + " начал понемногу разваливаться на куски. ");
    }


}
