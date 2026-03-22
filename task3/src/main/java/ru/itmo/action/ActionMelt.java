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
    private final Metal metal;
    private final Corner corner;

    @Override
    public String happen(String name) throws IncorrectActionParticipantException, NotTooHotException, NoStateForSituationException {
        String result = "";
        if(!name.equals("Лицевая сторона его")){
            throw new IncorrectActionParticipantException();
        }

        if (!frontSide.isMelted()) {
            frontSide.setMelted(true);
            result += name + " почти вся расплавилась,";
            frontSide.setTemperature(frontSide.getTemperature() + 400.0);

            if(!frontSide.isMetalFlowing()){
                frontSide.setMetalFlowing(true);
                metal.setCurrentX(metal.getCurrentX()+ corner.getX());
                metal.setCurrentY(metal.getCurrentY()+ corner.getY());
                corner.setFilled(true);
                result += " и густые ручейки расплавленного металла начали заползать в угол, ";
            } else {
                throw new NoStateForSituationException();
            }
        } else {
            throw new NoStateForSituationException();
        }

        return result;
    }
}

