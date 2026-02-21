import org.junit.jupiter.api.Test;

import ru.itmo.entity.*;
import ru.itmo.exception.BombingNotResumedException;
import ru.itmo.exception.IncorrectActionParticipantException;

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
}
