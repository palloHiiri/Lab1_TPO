package ru.itmo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionMelt;
import ru.itmo.exception.ComputerBankNotDestroyedException;
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

    public String melt(double temperature, ComputerBank computerBank) throws IncorrectActionParticipantException, NotTooHotException, ComputerBankNotDestroyedException {
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
