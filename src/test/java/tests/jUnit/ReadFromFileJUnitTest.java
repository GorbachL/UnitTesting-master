package tests.jUnit;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ReadFromFileJUnitTest {

    @Test
    void shouldParseFileTest() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String filePath = "src/main/resources/andrew-cart.json";
        Cart myCart = parser.readFromFile(new File(filePath));

        String result = gson.toJson(myCart);
        //System.out.println(result);
        String expectedResult = "{\"cartName\":\"andrew-cart\",\"realItems\":[{\"weight\":1560.0,\"name\":\"Audi\",\"price\":32026.9}],\"virtualItems\":[{\"sizeOnDisk\":20000.0,\"name\":\"Windows\",\"price\":11.0}],\"total\":38445.479999999996}";
        assertEquals(expectedResult, result);
    }


    @Test
    void verifyNotSuchFileExceptionIsThrownTest() {
        JsonParser parser = new JsonParser();
        String fileName = "\"src\\main\\resources\\lena-cart.json\"";

        NoSuchFileException thrown =
                assertThrows(NoSuchFileException.class,
                        () -> parser.readFromFile(new File(fileName)),
                        "NoSuchFileException expected to be thrown"
                );
        assertTrue(thrown.getMessage().contains("not found"));
    }
}
