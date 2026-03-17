package datetime;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import static datetime.SimpleDateFormatter.parseDate;
import static datetime.SimpleDateFormatter.today;
import static java.time.LocalDate.of;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class SimpleDateFormatterTest {

    @Test
    void parseDateTest() {
        assertEquals(parseDate("1920-01-03"), of(1920, 1, 3));
    }
    @Test
    void parseDateStrictTest() {
        DateTimeParseException dateTimeParseException =
                assertThrows(DateTimeParseException.class, () -> parseDate("1920-1-03"));
        assertEquals(5, dateTimeParseException.getErrorIndex());
    }

    @Test
    void formatDate() {
    }
}