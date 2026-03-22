package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionMelt;
import ru.itmo.exception.ComputerBankNotDestroyedException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.exception.NotTooHotException;
import ru.itmo.exception.IncorrectActionParticipantException;

@Getter
@Setter
public class FrontSide {
    private String name;
    private Double temperature;
    private boolean isMelted;
    private boolean isMetalFlowing;
    private Metal metal;
    private Corner corner;

    public FrontSide(String name) {
        this.name = name;
    }

    public FrontSide(String name, Metal metal, Corner corner) {
        this.name = name;
        this.metal = metal;
        this.corner = corner;
    }


    public String melt(double temperature, ComputerBank computerBank) throws IncorrectActionParticipantException, NotTooHotException, ComputerBankNotDestroyedException, NoStateForSituationException {
        if(!computerBank.isDestroyed()){
            throw new ComputerBankNotDestroyedException();
        }
        this.temperature = temperature;

        ActionMelt action = new ActionMelt(this, metal, corner);
        if(temperature > 800.0){
            return action.happen(name);
        } else {
            throw new NotTooHotException();
        }
    }

}
