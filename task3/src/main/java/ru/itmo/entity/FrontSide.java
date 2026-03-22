package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionMelt;
import ru.itmo.action.ActionMetalFlowing;
import ru.itmo.action.ActionSit;
import ru.itmo.exception.*;

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

        ActionMelt action = new ActionMelt(this);
        if(temperature > 800.0){
            return action.happen();
        } else {
            throw new NotTooHotException();
        }
    }

    public String flow() throws NoStateForSituationException, IncorrectActionParticipantException, NotMeltedException {
        if(!isMelted()){
            throw new NoStateForSituationException();
        }
        ActionMetalFlowing action = new ActionMetalFlowing(this);
        if(temperature > 1200.0){
            return action.happen();
        }else {
            throw new NotMeltedException();
        }
    }

}
