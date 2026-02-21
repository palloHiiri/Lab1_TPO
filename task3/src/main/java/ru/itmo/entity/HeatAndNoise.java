package ru.itmo.entity;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionBeingUnimaginable;
import ru.itmo.exception.IncorrectActionParticipantException;

@Setter
@Getter
public class HeatAndNoise {
    private String name;

    public HeatAndNoise(String name) {
        this.name = name;
    }

    public String being(String name) throws IncorrectActionParticipantException {
        ActionBeingUnimaginable action = new ActionBeingUnimaginable();
        return action.happen(name);
    }


}
