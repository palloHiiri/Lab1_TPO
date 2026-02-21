import org.junit.jupiter.api.Test;

import ru.itmo.entity.*;
import ru.itmo.exception.BombingNotResumedException;
import ru.itmo.exception.IncorrectActionParticipantException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    private String captureOutput(Runnable action) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = System.out;
        System.setOut(new PrintStream(baos));

        action.run();

        System.setOut(ps);
        return baos.toString();
    }
    @Test
    public void testBombingResume(){
        Bombing bombing = new Bombing("Бомбардировка");
        String output = captureOutput(() -> {
            try {
                bombing.resume(bombing.name);
            } catch (IncorrectActionParticipantException e) {
                fail("Название было правильным, исключение не должно быть выброшено");
            }
        });
        assertEquals("Бомбардировка возобновилась\n", output);
        assertTrue(bombing.isResumed);
    }

    @Test
    public void testHeatAndNoiseBeing() {
        HeatAndNoise heatAndNoise = new HeatAndNoise("Жара и шум");
        String output = captureOutput(() -> {
            try {
                heatAndNoise.being(heatAndNoise.name);
            } catch (IncorrectActionParticipantException e) {
                fail("Название было правильным, исключение не должно быть выброшено");
            }
        });
        assertEquals("Жара и шум были невообразимыми\n", output);
    }

    @Test
    public void testComputerBankDestroy() throws Exception{
        Bombing bombing = new Bombing("Бомбардировка");
        bombing.isResumed(true);

        ComputerBank computerBank = new ComputerBank("Компьютерный банк ");
        String output = captureOutput(() -> {
            try {
                computerBank.destroy(computerBank.bankName, bombing);
            } catch (BombingNotResumedException e) {
                fail("Бомбардировка была возобновлена, исключение не должно быть выброшено");
            } catch (IncorrectActionParticipantException e) {
                fail("Название банка было правильным, исключение не должно быть выброшено");
            }
        });

        assertTrue(computerBank.isDestroyed);
        assertEquals("Компьютерный банк начал понемногу разваливаться на куски\n", output);

    }

    @Test
    public void testComputerBankDestroyFalse() throws Exception{
        Bombing bombing = new Bombing("Бомбардировка");
        ComputerBank computerBank = new ComputerBank("Компьютерный банк ");
        assertThrows(BombingNotResumedException.class, () -> computerBank.destroy(computerBank.bankName, bombing));
    }
}
