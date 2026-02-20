import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import ru.itmo.SeriesExpansion;
import java.util.List;


public class SeriesExpansionTest {
    SeriesExpansion seriesExpansion = new SeriesExpansion();

    @Test
    @DisplayName("Тест граничных значений")
    public void testBorder() {
        List<Double> x = List.of(-1.0, 1.0, 0.999999, -0.999999, 0.95, -0.95);
        for (double value : x) {

            assertEquals(Math.acos(value), seriesExpansion.expand(value, 50), 0.1);
        }
    }

    @Test
    @DisplayName("Тест близких к границе значений со сходящимся рядом")
    public void tesCloseToBorderTrue(){
        List<Double> x = List.of(0.95, -0.95, 0.9, -0.9, 0.85, -0.85, 0.8, -0.8);
        for (double value : x) {
            assertEquals(Math.acos(value), seriesExpansion.expand(value, 50), 0.01);
        }

    }

    @Test
    @DisplayName("Тест серединных значений")
    public void testMiddle() {
        List<Double> x = List.of(0.0, 0.5, -0.5, 0.25, -0.25, 0.75, -0.75);
        List<Integer> n = List.of(20, 30, 40);
        for (double value : x){
            for (int i : n){
                assertEquals(Math.acos(value), seriesExpansion.expand(value, i), 1e-7);
            }
        }
    }

    @Test
    @DisplayName("Тест близких к границе значений с расходящимся рядом")
    public void tesCloseToBorderFalse(){
        List<Double> x = List.of(1.0000001, -1.0000001, 1.00001, -1.00001, 1.0001, -1.0001);
        for (double value : x) {
            assertThrows(IllegalArgumentException.class, () -> seriesExpansion.expand(value, 50));
        }
    }

    @Test
    @DisplayName("Тест недопустимых значений n")
    public void testInvalidN() {
        List<Integer> n = List.of(-1, -10, -100);
        for (int value : n) {
            assertThrows(IllegalArgumentException.class, () -> seriesExpansion.expand(0.5, value));
        }
    }

    @Test
    @DisplayName("Тест недопустимых значений x")
    public void testInvalidX() {
        List <Double> x = List.of(Double.NaN, Double.POSITIVE_INFINITY, Double.NEGATIVE_INFINITY, 3.0, -3.0, 5.0, -5.0);
            for (double value : x) {
                assertThrows(IllegalArgumentException.class, () -> seriesExpansion.expand(value, 10));
            }
    }
}
