package ru.itmo.entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.itmo.action.ActionBeingUnimaginable;
import ru.itmo.exception.IncorrectActionParticipantException;
import ru.itmo.util.NameValidation;

@Getter
@AllArgsConstructor
public class HeatAndNoise {
    private String name;

    public String being() throws IncorrectActionParticipantException {
        NameValidation.validateName(name, "Жара и шум");
        ActionBeingUnimaginable action = new ActionBeingUnimaginable(this);
        return action.happen();
    }


}
