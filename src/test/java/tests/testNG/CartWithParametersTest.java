package tests.testNG;

import com.google.gson.Gson;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.testng.Assert.assertEquals;

public class CartWithParametersTest {

    @Parameters({"cart-name", "realItem-name", "virtualItem-name"})
    @Test(groups = {"param test"})
    public void shouldWriteDataToFileUsingParametersTest(String cartName, String realItemName, String virtualItemName) {
        Gson gson = new Gson();
        Cart cart = new Cart(cartName);

        RealItem realItem = new RealItem();
        realItem.setName(realItemName);
        realItem.setPrice(100);
        realItem.setWeight(230.023);

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(virtualItemName);
        virtualItem.setPrice(777);
        virtualItem.setSizeOnDisk(45.45);

        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        assertAll("Should return the cart with Real and Virtual Items",
                () -> assertEquals(cartName, cart.getCartName()),
                () -> assertEquals(realItemName, realItem.getName()),
                () -> assertEquals(virtualItemName, virtualItem.getName())
        );
    }
}
