package tests.jUnit;

import com.google.gson.Gson;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

import static org.junit.jupiter.api.Assertions.assertEquals;


class DeleteItemJunitTest {

    //TODO - update the method so that the total value is recounted after deleting the items
    // -> This test will fail, because the Total value is NOT recounted after deleting the items.

    @Test
    @Disabled
    void verifyDeleteItemTest() {
        Gson gson = new Gson();
        Cart cart = new Cart("cartName-deleteItemTest");

        RealItem realItem = new RealItem();
        realItem.setName("realItemName-new Real Item");
        realItem.setPrice(10);
        realItem.setWeight(20);

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("virtualItemName-new Virtual Item");
        virtualItem.setPrice(30);
        virtualItem.setSizeOnDisk(40);

        cart.addRealItem(realItem);
        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        String expectedResultWithData = "{\"cartName\":\"cartName-deleteItemTest\",\"realItems\":[{\"weight\":20.0,\"name\":\"realItemName-new Real Item\",\"price\":10.0}],\"virtualItems\":[{\"sizeOnDisk\":40.0,\"name\":\"virtualItemName-new Virtual Item\",\"price\":30.0}],\"total\":48.0}";
        assertEquals(expectedResultWithData, result);

        cart.deleteVirtualItem(virtualItem);
        cart.deleteRealItem(realItem);

        parser.writeToFile(cart);

        result = gson.toJson(cart);
        System.out.println(result);

        String expectedResultAfterDeleteItems = "{\"cartName\":\"cartName-deleteItemTest\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";

        assertEquals(expectedResultAfterDeleteItems, result);
    }
}
