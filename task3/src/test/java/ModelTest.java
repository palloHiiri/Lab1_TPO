import org.junit.jupiter.api.Test;

import ru.itmo.entity.*;
import ru.itmo.exception.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class ModelTest {
    @Test
    public void testBombingResume() throws Exception {
        Bombing bombing = new Bombing("Бомбардировка");
        String result = bombing.resume(bombing.getName());
        assertEquals("Бомбардировка возобновилась. ", result);
        assertTrue(bombing.isResumed());
    }

    @Test
    public void testHeatAndNoiseBeing() throws Exception {
        HeatAndNoise heatAndNoise = new HeatAndNoise("Жара и шум");
        String result = heatAndNoise.being();
        assertEquals("Жара и шум были невообразимыми. ", result);
    }

    @Test
    public void testComputerBankDestroy() throws Exception{
        Bombing bombing = new Bombing("Бомбардировка");
        bombing.setResumed(true);
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        String result = computerBank.destroy(bombing);
        assertEquals("Компьютерный банк начал понемногу разваливаться на куски. ", result);
        assertTrue(computerBank.isDestroyed());

    }

    @Test
    public void testMelting() throws Exception {
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        FrontSide frontSide = new FrontSide("Лицевая сторона его");
        Corner corner = new Corner(10.0, 20.0);
        Metal metal = new Metal(0.0, 0.0);
        frontSide.setCorner(corner);
        frontSide.setMetal(metal);
        frontSide.setTemperature(900.0);
        String result = frontSide.melt(computerBank);
        assertEquals("Лицевая сторона его почти вся расплавилась,", result);
        assertTrue(frontSide.isMelted());
        assertEquals(1300.0, frontSide.getTemperature());
    }

    @Test
    public void testFlowing() throws Exception {
        ComputerBank computerBank = new ComputerBank("Компьютерный банк");
        computerBank.setDestroyed(true);
        FrontSide frontSide = new FrontSide("Лицевая сторона его", new Metal(0.0, 0.0), new Corner(10.0, 20.0), 900.0);
        frontSide.melt(computerBank);
        String result = frontSide.flow();
        assertEquals(" и густые ручейки расплавленного металла начали заползать в угол, ", result);
        assertTrue(frontSide.isMetalFlowing());
        assertEquals(10.0, frontSide.getMetal().getCurrentX());
        assertEquals(20.0, frontSide.getMetal().getCurrentY());
        assertTrue(frontSide.getCorner().isFilled());
    }

    @Test
    public void testPeoplePanic() throws Exception {
        Corner corner = new Corner(10.0, 20.0);
        Metal metal = new Metal(0.0, 0.0);
        FrontSide frontSide = new FrontSide("Лицевая сторона его", metal, corner, 1200.0);
        List<String> names = List.of("Генри", "Алекс", "Кира");
        frontSide.setMetalFlowing(true);
        frontSide.setMelted(true);
        frontSide.getCorner().setFilled(true);
        People people = new People(names, frontSide, "Они");
        String result = people.act();
        assertEquals("Они сгрудились плотнее и стали ждать конца.", result);
    }

    @Test
    public void testPeopleSit() throws Exception {
        Corner corner = new Corner(10.0, 20.0);
        Metal metal = new Metal(0.0, 0.0);
        FrontSide frontSide = new FrontSide("Лицевая сторона его", metal, corner, 1200.0);
        List<String> names = List.of("Генри", "Алекс", "Кира");
        People people = new People(names, frontSide, "Они");
        frontSide.setMelted(true);
        frontSide.setMetalFlowing(true);
        String result = people.sit();
        assertEquals("в котором Они сидели. ", result);
    }
}
