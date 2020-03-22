package tests.testNG;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.testng.Assert.assertEquals;

public class RealItemTest {

    @Test
    public void verifyCartWithRealItemWriteInFileTest() {
        Gson gson = new Gson();
        Cart cart = new Cart("RealItemOnly-cart");

        RealItem realItem = new RealItem();
        realItem.setName("Name of new Real Item");
        realItem.setPrice(123.456);
        realItem.setWeight(777.777);

        cart.addRealItem(realItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        assertAll("Should return the cart with Real Item",
                () -> assertEquals(cart.getCartName(), "RealItemOnly-cart"),
                () -> assertEquals(realItem.getName(), "Name of new Real Item"),
                () -> assertEquals(realItem.getPrice(), 123.456),
                () -> assertEquals(realItem.getWeight(), 777.777),
                () -> assertEquals(cart.getTotalPrice(), (123.456 * 0.2) + 123.456)
        );
    }
}
