import com.fasterxml.jackson.databind.ObjectMapper;
import data.Friend;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Map;

public class ParsingJsonTests {

    @Test
    void parseJson() throws IOException {
        ObjectMapper mapper = new ObjectMapper().setLocale(new Locale("ru", "RU"));
        Friend friend = mapper.readValue(new File("src/test/resources/Friend.json"), Friend.class);
        Map<String, String> relatives = Map.of("Отец", "Александр", "Сестра", "Маша");

        Assertions.assertEquals("Саня", friend.getName());
        Assertions.assertEquals(30, friend.getAge());
        Assertions.assertEquals("М", friend.getGender());
        Assertions.assertEquals(true, friend.isNitPicker());
        Assertions.assertArrayEquals( new String[] { "бухать", "смотреть аниме", "душнить" }, friend.getHobbies());
        Assertions.assertEquals(relatives, friend.getRelatives());
    }
}