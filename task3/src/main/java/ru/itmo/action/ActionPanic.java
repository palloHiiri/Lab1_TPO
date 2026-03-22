package ru.itmo.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.itmo.entity.Corner;
import ru.itmo.entity.FrontSide;
import ru.itmo.entity.People;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.util.State;

@Getter
@Setter
@RequiredArgsConstructor
public class ActionPanic implements Action {

    private final State state;
    private final FrontSide frontSide;
    private final  People people;
    private final Corner corner;

    @Override
    public String happen(String name) throws NoStateForSituationException, IncorrectActionParticipantException {
        if (!name.equals("Они")){
            throw new IncorrectActionParticipantException();
        }

        String result = "";

        if (frontSide.isMelted() && frontSide.isMetalFlowing()) {
            people.setState(State.SITTING);
            result += "в котором " + name + State.SITTING.getDescription();

            if(frontSide.getTemperature() >= 1200 && corner.isFilled()) {
                people.setState(State.PANIC);
                result += name + State.PANIC.getDescription() + " и" + State.WAITING.getDescription();
            }else {
                throw new NoStateForSituationException();
            }
        }

        if (result.isEmpty()) {
            throw new NoStateForSituationException();
        }

        return result;
    }

}
