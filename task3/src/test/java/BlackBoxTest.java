import org.junit.jupiter.api.Test;
import ru.itmo.entity.*;
import ru.itmo.exception.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BlackBoxTest {
    Bombing bombing = new Bombing("Бомбардировка");
    HeatAndNoise heatAndNoise = new HeatAndNoise("Жара и шум");
    ComputerBank computerBank = new ComputerBank("Компьютерный банк");
    Corner corner = new Corner(10.0, 20.0);
    Metal metal = new Metal(0.0, 0.0);
    FrontSide frontSide = new FrontSide("Лицевая сторона его", metal, corner, 900.0);
    People people = new People(List.of("Генри", "Алекс"), frontSide, "Они");

    @Test
    public void testScenario() throws Exception {

        String result = bombing.resume(bombing.getName());
        result += heatAndNoise.being();
        result += computerBank.destroy(bombing);
        result += frontSide.melt(computerBank);
        result += frontSide.flow();
        result += people.sit();
        result += people.act();
        assertEquals("Бомбардировка возобновилась. Жара и шум были невообразимыми. " +
                "Компьютерный банк начал понемногу разваливаться на куски. " +
                "Лицевая сторона его почти вся расплавилась," +
                " и густые ручейки расплавленного металла начали заползать в угол, " +
                "в котором Они сидели. Они сгрудились плотнее и стали ждать конца.", result);
    }
}
