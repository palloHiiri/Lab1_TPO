package ru.itmo.entity;

import lombok.Getter;
import ru.itmo.action.ActionResume;
import ru.itmo.exception.IncorrectActionParticipantException;

@Getter
public class Bombing {
    public String name;
    public boolean isResumed;


    public Bombing(String name) {
        this.name = name;
        this.isResumed = false;
    }

    public void resume(String name) throws IncorrectActionParticipantException {
        ActionResume action = new ActionResume();
        action.happen(name);
        isResumed(true);
    }

    public void isResumed(boolean resumed){
        isResumed = resumed;
    }
}
