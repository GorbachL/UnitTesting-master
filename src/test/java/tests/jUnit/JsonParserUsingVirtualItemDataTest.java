package tests.jUnit;

import com.google.gson.Gson;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.VirtualItem;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class JsonParserUsingVirtualItemDataTest {


    @ParameterizedTest
    @MethodSource("virtualItemStream")
    public void verifyVirtualItemWithAllDataInFile(VirtualItem virtualItem) {

        Gson gson = new Gson();
        Cart cart = new Cart("Cart with Virtual Item jUnit5");

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        assertAll("Should return 4 carts with Virtual Items",
                () -> assertEquals("Cart with Virtual Item jUnit5", cart.getCartName())
        );
    }

    static Stream<VirtualItem> virtualItemStream() {
        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(120.0);
        virtualItem.setName("Virtual Items - All Data");
        virtualItem.setPrice(115.0);
        VirtualItem virtualItem1 = new VirtualItem();
        virtualItem1.setSizeOnDisk(444);
        virtualItem1.setPrice(555);
        VirtualItem virtualItem2 = new VirtualItem();
        virtualItem2.setName("Virtual Items without sizeOnDisk");
        virtualItem2.setPrice(123.0);
        VirtualItem virtualItem3 = new VirtualItem();
        virtualItem3.setSizeOnDisk(4000);
        virtualItem3.setName("Virtual Items without price");
        return Stream.of(virtualItem, virtualItem1, virtualItem2, virtualItem3);
    }
}
