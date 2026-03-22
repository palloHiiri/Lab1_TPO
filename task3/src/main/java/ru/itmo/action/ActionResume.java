package ru.itmo.action;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.entity.Bombing;
import ru.itmo.exception.IncorrectActionParticipantException;

@AllArgsConstructor
@Setter
@Getter
public class ActionResume implements Action {
    private final Bombing bombing;
    @Override
    public String happen() throws IncorrectActionParticipantException {
        return bombing.getName() + " возобновилась. ";
    }
}
