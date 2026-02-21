package ru.itmo.entity;
import ru.itmo.action.ActionBeingUnimaginable;
import ru.itmo.exception.IncorrectActionParticipantException;

public class HeatAndNoise {
    public String name;

    public HeatAndNoise(String name) {
        this.name = name;
    }

    public void being(String name) throws IncorrectActionParticipantException {
        ActionBeingUnimaginable action = new ActionBeingUnimaginable();
        action.happen(name);
    }


}
