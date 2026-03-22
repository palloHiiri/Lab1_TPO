package ru.itmo.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.entity.*;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.exception.NotTooHotException;

@Getter
@Setter
@AllArgsConstructor
public class ActionMelt implements Action {
    private final FrontSide frontSide;

    @Override
    public String happen() throws IncorrectActionParticipantException, NotTooHotException, NoStateForSituationException {

        if (!frontSide.isMelted()) {
            frontSide.setMelted(true);
            frontSide.setTemperature(frontSide.getTemperature() + 400.0);
            return frontSide.getName() + " почти вся расплавилась,";
        } else {
            throw new NoStateForSituationException();
        }

    }
}

