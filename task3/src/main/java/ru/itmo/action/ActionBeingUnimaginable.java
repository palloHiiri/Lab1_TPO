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
        return heatAndNoise.getName() + " были невообразимыми. ";
    }
}
