import org.junit.Test;
import ru.vladrus13.parser.Parser;

import java.util.HashMap;

import static org.junit.Assert.*;

public class OfflineTestParser {
    private void emptytest(int expected, String parsed) {
        assertEquals(expected, new Parser().parse(parsed).evaluate(new HashMap<>()));
    }

    @Test
    public void test01() {
        emptytest(4, "2 + 2");
    }

    @Test
    public void test02() {
        emptytest(6, "2 + 2 * 2");
    }

    @Test
    public void test03() {
        emptytest(16, "(2 + 2) * (2 + 2 * (2 - 1))");
    }

    @Test
    public void test04() {
        emptytest(1, "2 / 2 * 2 / 2 * 2 / 2");
    }
}
