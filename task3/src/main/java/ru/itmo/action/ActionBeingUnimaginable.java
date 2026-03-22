package ru.itmo.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.entity.*;

@AllArgsConstructor
@Getter
@Setter
public class ActionBeingUnimaginable implements Action {
    private final HeatAndNoise heatAndNoise;

    public String happen() throws IncorrectActionParticipantException {
        String result = "";
        if(heatAndNoise.getName().equals("Жара и шум")) {
            result = heatAndNoise.getName() + " были невообразимыми. ";
            return result;
        }else {
            throw new IncorrectActionParticipantException();
        }
    }
}
