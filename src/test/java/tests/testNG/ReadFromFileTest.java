package tests.testNG;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.NoSuchFileException;
import shop.Cart;

import java.io.File;

import static org.testng.Assert.*;

public class ReadFromFileTest {

    @Test(groups = {"init"})
    public void shouldParseFileTest() {
        Gson gson = new Gson();
        JsonParser parser = new JsonParser();
        String filePath = "src/main/resources/andrew-cart.json";
        Cart myCart = parser.readFromFile(new File(filePath));

        String result = gson.toJson(myCart);
        System.out.println(result);
        String expectedResult = "{\"cartName\":\"andrew-cart\",\"realItems\":[{\"weight\":1560.0,\"name\":\"Audi\",\"price\":32026.9}],\"virtualItems\":[{\"sizeOnDisk\":20000.0,\"name\":\"Windows\",\"price\":11.0}],\"total\":38445.479999999996}";

        assertEquals(result, expectedResult);
    }

    @Test(expectedExceptions = NoSuchFileException.class, expectedExceptionsMessageRegExp = ".+ not found!", groups = {"init"})
    public void verifyNotSuchFileExceptionTest() {
        JsonParser parser = new JsonParser();
        String fileName = "\"src\\main\\resources\\lena-cart.json\"";
        parser.readFromFile(new File(fileName));
    }
}



