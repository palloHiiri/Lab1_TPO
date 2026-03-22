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
        assertThrows(ComputerBankNotDestroyedException.class, () -> frontSide.melt(new ComputerBank("Компьютерный банк")));
    }

    @Test
    public void testNotTooHot() throws Exception{
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0), 800.0);
        assertThrows(NotTooHotException.class, () -> frontSide.melt(computerBank));
    }

    @Test
    public void testIncorrectParticipant() throws Exception {
        Bombing bombing = new Bombing("Не та бомбардировка");
        assertThrows(IncorrectActionParticipantException.class,() -> bombing.resume(bombing.getName()));
    }

    @Test
    public void testOnlyOnePerson() throws Exception {
        People people = new People(List.of("Генри"), new FrontSide("Лицевая сторона его"), "Они");
        assertThrows(NotEnoughPeopleException.class, people::act);
    }

    @Test
    public void testStrangeState() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0), 900.0);
        People people = new People(List.of("Генри", "Алекс"), frontSide, "Они");
        assertThrows(NoStateForSituationException.class, people::sit);
        frontSide.setMelted(true);
        assertThrows(NoStateForSituationException.class, people::sit);
        frontSide.setMetalFlowing(true);
        frontSide.setMelted(false);
        assertThrows(NoStateForSituationException.class, people::sit);
    }

    @Test
    public void testStrangeStateWhilePanic() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0), 900.0);
        People people = new People(List.of("Генри", "Алекс"), frontSide, "Они");
        assertThrows(NoStateForSituationException.class, people::act);
        frontSide.setTemperature(1400.0);
        assertThrows(NoStateForSituationException.class, people::act);
        frontSide.setTemperature(1000.0);
        frontSide.getCorner().setFilled(true);
        assertThrows(NoStateForSituationException.class, people::act);

    }

    @Test
    public void testMelted() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0), 900.0);
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        frontSide.setMelted(true);
        assertThrows(NoStateForSituationException.class, () -> frontSide.melt(computerBank));
    }

    @Test
    public void testFlowing() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0), 1300.0);
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        frontSide.setMelted(true);
        computerBank.setDestroyed(true);
        frontSide.setMetalFlowing(true);
        assertThrows(NoStateForSituationException.class, frontSide::flow);
    }

    @Test
    public void testNotMelted() throws Exception {
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0), 1300.0);
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        assertThrows(NotMeltedException.class, frontSide::flow);
        frontSide.setMelted(false);
        frontSide.setTemperature(1000.0);
        assertThrows(NotMeltedException.class, frontSide::flow);
    }
}
