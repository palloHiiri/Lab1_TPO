import org.junit.jupiter.api.Test;
import ru.itmo.entity.*;
import ru.itmo.exception.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ExceptionTest {
    @Test
    public void testComputerBankDestroyFalse() throws Exception{
        Bombing bombing = new Bombing("Бомбардировка");
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        assertThrows(Exception.class, () -> computerBank.destroy(computerBank.getBankName(), bombing));
    }

    @Test
    public void testFrontSideMeltingFalse() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его");
        assertThrows(ComputerBankNotDestroyedException.class, () -> frontSide.melt(900.0, new ComputerBank("Компьютерный банк")));
    }

    @Test
    public void testNotTooHot() throws Exception{
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        FrontSide frontSide = new FrontSide("Лицевая сторона его");
        assertThrows(NotTooHotException.class, () -> frontSide.melt(700.0, computerBank));
    }

    @Test
    public void testIncorrectParticipant() throws Exception {
        Bombing bombing = new Bombing("Не та бомбардировка");
        assertThrows(IncorrectActionParticipantException.class,() -> bombing.resume(bombing.getName()));
    }

    @Test
    public void testOnlyOnePerson() throws Exception {
        People people = new People(List.of("Генри"), new FrontSide("Лицевая сторона его"));
        assertThrows(NotEnoughPeopleException.class, () -> people.act("Они"));
    }

    @Test
    public void testStrangeState() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его");
        frontSide.setMelted(true);
        People people = new People(List.of("Генри", "Алекс"), frontSide);

        assertThrows(NoStateForSituationException.class, () -> people.act("Они"));
    }

}
