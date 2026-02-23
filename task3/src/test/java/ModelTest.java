import org.junit.jupiter.api.Test;

import ru.itmo.entity.*;
import ru.itmo.exception.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    @Test
    public void testBombingResume() throws IncorrectActionParticipantException {
        Bombing bombing = new Bombing("Бомбардировка");
        String result = bombing.resume(bombing.getName());
        assertEquals("Бомбардировка возобновилась", result);
        assertTrue(bombing.isResumed());
    }

    @Test
    public void testHeatAndNoiseBeing() throws IncorrectActionParticipantException {
        HeatAndNoise heatAndNoise = new HeatAndNoise("Жара и шум");
        String result = heatAndNoise.being(heatAndNoise.getName());
        assertEquals("Жара и шум были невообразимыми", result);
    }

    @Test
    public void testComputerBankDestroy() throws Exception{
        Bombing bombing = new Bombing("Бомбардировка");
        bombing.setResumed(true);
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        String result = computerBank.destroy(computerBank.getBankName(), bombing);
        assertEquals("Компьютерный банк начал понемногу разваливаться на куски", result);
        assertTrue(computerBank.isDestroyed());

    }

    @Test
    public void testComputerBankDestroyFalse() throws Exception{
        Bombing bombing = new Bombing("Бомбардировка");
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        assertThrows(BombingNotResumedException.class, () -> computerBank.destroy(computerBank.getBankName(), bombing));
    }

    @Test
    public void testMelting() throws IncorrectActionParticipantException, NotTooHotException,
            ComputerBankNotDestroyedException {
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        FrontSide frontSide = new FrontSide("Лицевая сторона его");
        Corner corner = new Corner(10.0, 20.0);
        Metal metal = new Metal(0.0, 0.0);
        frontSide.setCorner(corner);
        frontSide.setMetal(metal);
        frontSide.setTemperature(900.0);
        String result = frontSide.melt(frontSide.getTemperature(), computerBank);
        assertEquals("Лицевая сторона его почти вся расплавилась, и густые ручейки расплавленного " +
                "металла начали заползать в угол,", result);
        assertTrue(frontSide.isMelted());
        assertTrue(frontSide.isMetalFlowing());
        assertEquals(10.0, metal.getCurrentX());
        assertEquals(20.0, metal.getCurrentY());
    }

    @Test
    public void testPeople() throws NotEnoughPeopleException, NoStateForSituationException,
            IncorrectActionParticipantException {
        FrontSide frontSide = new FrontSide("Лицевая сторона его");
        List<String> names = List.of("Генри", "Алекс", "Кира");
        People people = new People(names, frontSide);
        String result = people.act("они");
        assertEquals("в котором они сидели.", result);
        frontSide.setMetalFlowing(true);
        frontSide.setMelted(true);
        result = people.act("Они");
        people.setFrontSide(frontSide);
        assertEquals("Они сгрудились плотнее и стали ждать конца.", result);
    }
}
