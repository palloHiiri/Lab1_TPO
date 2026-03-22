package ru.itmo.action;

import lombok.*;
import ru.itmo.entity.People;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.util.State;

@AllArgsConstructor
@Setter
@Getter
public class ActionSit implements  Action {
    private final People people;

    @Override
    public String happen() throws NoStateForSituationException, IncorrectActionParticipantException {

        if (people.getFrontSide().isMelted() && people.getFrontSide().isMetalFlowing()) {
            people.setState(State.SITTING);
            return  "в котором " + people.getName() + State.SITTING.getDescription();
        }else{
            throw new NoStateForSituationException();
        }
    }
}
