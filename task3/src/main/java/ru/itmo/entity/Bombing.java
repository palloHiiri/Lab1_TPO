package ru.itmo.entity;

import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionResume;
import ru.itmo.exception.IncorrectActionParticipantException;

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
        ActionResume action = new ActionResume();
        setResumed(true);
        return action.happen(name);

    }

}
