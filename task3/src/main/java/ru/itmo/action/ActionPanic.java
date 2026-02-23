package ru.itmo.action;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.itmo.entity.FrontSide;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.exception.NoStateForSituationException;
import ru.itmo.util.State;

@Getter
@Setter
@RequiredArgsConstructor
public class ActionPanic implements Action {

    private final State state;
    private final FrontSide frontSide;

    @Override
    public String happen(String name) throws NoStateForSituationException, IncorrectActionParticipantException {
        if (!name.equals("Они") && !name.equals("они")){
            throw new IncorrectActionParticipantException();
        }
        if (!frontSide.isMelted() && !frontSide.isMetalFlowing()) {
            return ("в котором " + name + State.BEFORE_BOMBING.getDescription());
        } else if(frontSide.isMelted() && frontSide.isMetalFlowing()) {
            return (name + State.PANIC.getDescription() + " и" + State.WAITING.getDescription());
        } else {
            throw new NoStateForSituationException();
        }
    }
}
