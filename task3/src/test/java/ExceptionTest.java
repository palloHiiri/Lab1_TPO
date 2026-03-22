import org.junit.jupiter.api.Test;
import ru.itmo.entity.*;
import ru.itmo.exception.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionTest {
    @Test
    public void testComputerBankDestroyFalse() throws Exception{
        Bombing bombing = new Bombing("Бомбардировка");
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        assertThrows(Exception.class, () -> computerBank.destroy(bombing));
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
        ComputerBank computerBank = new ComputerBank("Компьютерный не банк");
        FrontSide frontSide = new FrontSide("Не та лицевая сторона его");
        HeatAndNoise heatAndNoise = new HeatAndNoise("Не та жара и шум");
        People people = new People(List.of("Генри", "Алекс"), frontSide, "Не они");
        assertThrows(IncorrectActionParticipantException.class,() -> bombing.resume(bombing.getName()));
        assertThrows(IncorrectActionParticipantException.class,() -> computerBank.destroy(bombing));
        assertThrows(IncorrectActionParticipantException.class,() -> frontSide.melt(900.0, computerBank));
        assertThrows(IncorrectActionParticipantException.class, heatAndNoise::being);
        assertThrows(IncorrectActionParticipantException.class, people::act);
    }

    @Test
    public void testOnlyOnePerson() throws Exception {
        People people = new People(List.of("Генри"), new FrontSide("Лицевая сторона его"), "Они");
        assertThrows(NotEnoughPeopleException.class, people::act);
    }

    @Test
    public void testStrangeState() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0));
        People people = new People(List.of("Генри", "Алекс"), frontSide, "Они");
        assertThrows(NoStateForSituationException.class, people::act);
        frontSide.setMelted(true);
        assertThrows(NoStateForSituationException.class, people::act);
        frontSide.setMetalFlowing(true);
        frontSide.setTemperature(100.0);
        assertThrows(NoStateForSituationException.class, people::act);
        frontSide.setTemperature(1400.0);
        assertThrows(NoStateForSituationException.class, people::act);

    }

    @Test
    public void testMelted() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его");
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        frontSide.setMelted(true);
        assertThrows(NoStateForSituationException.class, () -> frontSide.melt(900.0, computerBank));
        frontSide.setMelted(false);
        frontSide.setMetalFlowing(true);
        assertThrows(NoStateForSituationException.class, () -> frontSide.melt(900.0, computerBank));
    }

}
