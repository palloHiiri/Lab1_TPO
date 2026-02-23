package ru.itmo.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.itmo.action.ActionPanic;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.exception.NotEnoughPeopleException;
import ru.itmo.util.State;

import java.util.List;

@Setter
@Getter
public class People {
    private List<String> names;
    private FrontSide frontSide;
    private State state;

    public People(List<String> names, FrontSide frontSide) {
        this.names = names;
        this.frontSide = frontSide;
    }

    public String act(String globalName) throws NotEnoughPeopleException, NoStateForSituationException, IncorrectActionParticipantException {
        ActionPanic action = new ActionPanic(state, frontSide);
        if (names.size() < 2){
            throw new NotEnoughPeopleException();
        }
        return action.happen(globalName);
    }


}
