package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionPanic;
import ru.itmo.action.ActionSit;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.exception.NotEnoughPeopleException;
import ru.itmo.util.NameValidation;
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

    private void numberOfPeople() throws NotEnoughPeopleException {
        if(names.size() < 2) {
            throw new NotEnoughPeopleException();
        }
    }

    public String act() throws NotEnoughPeopleException, NoStateForSituationException, IncorrectActionParticipantException {
        NameValidation.validateName(name, "Они");

        ActionPanic actionPanic = new ActionPanic( this);
        numberOfPeople();
        return actionPanic.happen();
    }

    public String sit() throws NoStateForSituationException, IncorrectActionParticipantException, NotEnoughPeopleException {
        NameValidation.validateName(name, "Они");
        ActionSit actionSit = new ActionSit(this);
        numberOfPeople();
        return actionSit.happen();
    }




}
