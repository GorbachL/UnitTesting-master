package tests.jUnit;

import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;


class WriteToFileUsingTagsTest {

    @Utils.InitTest
    void shouldWriteDataToFileTest() {
        Gson gson = new Gson();
        Cart cartWithAllData = new Cart("lena-With Data-cart-junit5");

        RealItem realItem = new RealItem();
        realItem.setName("Real");
        realItem.setPrice(100);
        realItem.setWeight(230.023);

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName("Virtual");
        virtualItem.setPrice(777);
        virtualItem.setSizeOnDisk(45.45);

        cartWithAllData.addRealItem(realItem);
        cartWithAllData.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cartWithAllData);

        String resultWithData = gson.toJson(cartWithAllData);
        System.out.println(resultWithData);
        String expectedResultWithData = "{\"cartName\":\"lena-With Data-cart-junit5\",\"realItems\":[{\"weight\":230.023,\"name\":\"Real\",\"price\":100.0}],\"virtualItems\":[{\"sizeOnDisk\":45.45,\"name\":\"Virtual\",\"price\":777.0}],\"total\":1052.4}";

        Assertions.assertEquals(expectedResultWithData, resultWithData);
    }

    @Utils.InitTest
    void shouldWriteEmptyCartTest() {
        Gson gson = new Gson();
        Cart cartEmpty = new Cart("lena-Empty-cart-junit5");

        Parser parser = new JsonParser();
        parser.writeToFile(cartEmpty);

        String resultEmptyCart = gson.toJson(cartEmpty);
        System.out.println(resultEmptyCart);
        String expectedResultEmptyCart = "{\"cartName\":\"lena-Empty-cart-junit5\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";

        Assertions.assertEquals(expectedResultEmptyCart, resultEmptyCart);
    }
}
