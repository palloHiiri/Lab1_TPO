package ru.itmo.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.itmo.entity.People;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.util.State;

@Getter
@Setter
@RequiredArgsConstructor
public class ActionPanic implements Action {

    private final People people;

    @Override
    public String happen() throws NoStateForSituationException, IncorrectActionParticipantException {

        if(people.getFrontSide().getTemperature() >= 1200 && people.getFrontSide().getCorner().isFilled()) {
            people.setState(State.PANIC);
            return people.getName() + State.PANIC.getDescription() + " и" + State.WAITING.getDescription();
        }else {
            throw new NoStateForSituationException();
        }
    }

}
