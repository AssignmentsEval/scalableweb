import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


public class MessageServiceTest {

  @DisplayName("Test MessageService.get()")
  @Test
  void testGet() {
    assertEquals("Hello JUnit 5", "Hello JUnit 5");
  }

}
