package ru.itmo.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.entity.FrontSide;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;

@Getter
@Setter
@AllArgsConstructor
public class ActionMetalFlowing implements Action {
    private final FrontSide frontSide;

    @Override
    public String happen() throws IncorrectActionParticipantException, NoStateForSituationException {
        if(!frontSide.getName().equals("Лицевая сторона его")){
            throw new IncorrectActionParticipantException();
        }

        if(!frontSide.isMetalFlowing()){
            frontSide.setMetalFlowing(true);
            frontSide.getMetal().setCurrentX(frontSide.getMetal().getCurrentX()+ frontSide.getCorner().getX());
            frontSide.getMetal().setCurrentY(frontSide.getMetal().getCurrentY()+ frontSide.getCorner().getY());
            frontSide.getCorner().setFilled(true);
            return  " и густые ручейки расплавленного металла начали заползать в угол, ";
        } else {
            throw new NoStateForSituationException();
        }

    }
}
