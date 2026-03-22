package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionResume;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.util.NameValidation;

@Setter
@Getter
public class Bombing {
    private String name;
    private boolean isResumed;


    public Bombing(String name) {
        this.name = name;
        this.isResumed = false;
    }

    public String resume(String name) throws IncorrectActionParticipantException {
        NameValidation.validateName(name, "Бомбардировка");
        ActionResume action = new ActionResume(this);
        setResumed(true);
        return action.happen();

    }

}
