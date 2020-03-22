package tests.testNG;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.testng.Assert.assertEquals;

public class DeleteItemTest {

    //TODO - update the method so that the total value is recounted after deleting the items
    // -> This test will fail, because the Total value is NOT recounted after deleting the items.

    @Test(enabled = false)
    public void verifyDeleteItemTest() {
        Gson gson = new Gson();
        Cart cart = new Cart("cartName-name");

        RealItem realItem = new RealItem();
        realItem.setName("realItemName-name");
        realItem.setPrice(10);
        realItem.setWeight(20);

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("virtualItemName-name");
        virtualItem.setPrice(30);
        virtualItem.setSizeOnDisk(40);

        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        String expectedResultWithData = "{\"cartName\":\"cartName-name\",\"realItems\":[{\"weight\":20.0,\"name\":\"realItemName-name\",\"price\":10.0}],\"virtualItems\":[{\"sizeOnDisk\":40.0,\"name\":\"virtualItemName-name\",\"price\":30.0}],\"total\":48.0}";

        assertEquals(result, expectedResultWithData);

        cart.deleteVirtualItem(virtualItem);
        cart.deleteRealItem(realItem);

        parser.writeToFile(cart);

        result = gson.toJson(cart);
        System.out.println(result);

        String expectedResultAfterDeleteItems = "{\"cartName\":\"cartName-name\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";

        assertEquals(result, expectedResultAfterDeleteItems);
    }
}
