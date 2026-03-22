package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionPanic;
import ru.itmo.action.ActionSit;
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
    private final String name;

    public People(List<String> names, FrontSide frontSide, String name) {
        this.names = names;
        this.frontSide = frontSide;
        this.name = name;
    }

    public String act() throws NotEnoughPeopleException, NoStateForSituationException, IncorrectActionParticipantException {
        ActionPanic actionPanic = new ActionPanic( this);
        if (names.size() < 2){
            throw new NotEnoughPeopleException();
        }
        return actionPanic.happen();
    }

    public String sit() throws NoStateForSituationException, IncorrectActionParticipantException, NotEnoughPeopleException {
        ActionSit actionSit = new ActionSit(this);
        if (names.size() < 2){
            throw new NotEnoughPeopleException();
        }
        return actionSit.happen();
    }




}
