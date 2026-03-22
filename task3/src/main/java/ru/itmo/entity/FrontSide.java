package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionMelt;
import ru.itmo.action.ActionFlowing;
import ru.itmo.exception.*;
import ru.itmo.util.NameValidation;

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

    public FrontSide(String name, Metal metal, Corner corner, Double temperature) {
        this.name = name;
        this.metal = metal;
        this.corner = corner;
        this.temperature = temperature;
    }

    public String melt(ComputerBank computerBank) throws IncorrectActionParticipantException, NotTooHotException, ComputerBankNotDestroyedException, NoStateForSituationException {
        NameValidation.validateName(name, "Лицевая сторона его");

        if(!computerBank.isDestroyed()){
            throw new ComputerBankNotDestroyedException();
        }

        ActionMelt action = new ActionMelt(this);
        if(temperature > 800.0){
            return action.happen();
        } else {
            throw new NotTooHotException();
        }
    }

    public String flow() throws NoStateForSituationException, IncorrectActionParticipantException, NotMeltedException {
        NameValidation.validateName(name, "Лицевая сторона его");

        ActionFlowing action = new ActionFlowing(this);
        if(temperature >= 1200.0 && isMelted()){
            return action.happen();
        }else {
            throw new NotMeltedException();
        }
    }

}
