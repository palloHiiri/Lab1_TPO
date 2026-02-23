package ru.itmo.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.entity.*;
import ru.itmo.exception.NotTooHotException;

@Getter
@Setter
@AllArgsConstructor
public class ActionMelt implements Action {
    private final FrontSide frontSide;
    private final Metal metal;
    private final Corner corner;

    @Override
    public String happen(String name) throws IncorrectActionParticipantException, NotTooHotException {
        String result = "";
        if(!name.equals("Лицевая сторона его")){
            throw new IncorrectActionParticipantException();
        }

        if (frontSide.getTemperature() > 800 && !frontSide.isMelted()) {
            frontSide.setMelted(true);
            result += name + " почти вся расплавилась,";
            frontSide.setTemperature(frontSide.getTemperature()+400.0);
        }

        if (frontSide.getTemperature() > 1200.0 && frontSide.isMelted() && !frontSide.isMetalFlowing()) {
            frontSide.setMetalFlowing(true);
            metal.setCurrentX(metal.getCurrentX()+ corner.getX());
            metal.setCurrentY(metal.getCurrentY()+ corner.getY());
            result += " и густые ручейки расплавленного металла начали заползать в угол,";
        }

        return result;
    }
}

