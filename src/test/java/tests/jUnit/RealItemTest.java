package tests.jUnit;

import com.google.gson.Gson;

import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class RealItemTest {

    @Test
    public void verifyRealItemInFile() {
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

        assertAll("Should return the cart with Real Item",
                () -> assertEquals("RealItemOnly-cart", cart.getCartName()),
                () -> assertEquals("Name of new Real Item", realItem.getName()),
                () -> assertEquals(123.456, realItem.getPrice()),
                () -> assertEquals(777.777, realItem.getWeight()),
                () -> assertEquals((123.456 * 0.2) + 123.456, cart.getTotalPrice())
        );

        System.out.println(result);
    }
}
