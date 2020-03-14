package tests.jUnit;

import com.google.gson.Gson;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import shop.Cart;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadFromFileJUnitTest {

    @Test
    public void shouldParseFile() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String filePath = "src/main/resources/andrew-cart.json";
        Cart myCart = parser.readFromFile(new File(filePath));

        String result = gson.toJson(myCart);
        String expectedResult = "{\"cartName\":\"andrew-cart\",\"realItems\":[{\"weight\":1560.0,\"name\":\"Audi\",\"price\":32026.9}],\"virtualItems\":[{\"sizeOnDisk\":20000.0,\"name\":\"Windows\",\"price\":11.0}],\"total\":38445.479999999996}";
        assertEquals(expectedResult, result);
        System.out.println(result);
    }

    @Test
    @Disabled("Disabled until ..")
    public void verifyNotSuchFileException() {
        JsonParser parser = new JsonParser();
        String fileName = "\"src\\main\\resources\\lena-cart.json\"";
        parser.readFromFile(new File(fileName));
    }
}
