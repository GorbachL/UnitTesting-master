package tests.testNG;

import com.google.gson.Gson;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;


import static org.testng.Assert.assertEquals;

public class WriteToFileTest {

    @Test(groups = {"init"})
    public void shouldWriteDataToFile() {
        Gson gson = new Gson();
        Cart cartWithAllData = new Cart("lena-With Data-cart");

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
        String expectedResultWithData = "{\"cartName\":\"lena-With Data-cart\",\"realItems\":[{\"weight\":230.023,\"name\":\"Real\",\"price\":100.0}],\"virtualItems\":[{\"sizeOnDisk\":45.45,\"name\":\"Virtual\",\"price\":777.0}],\"total\":1052.4}";

        assertEquals(resultWithData, expectedResultWithData);
        System.out.println(resultWithData);
    }

    @Test(dependsOnMethods = {"shouldWriteDataToFile"}, groups = {"init"})
    public void shouldWriteEmptyCartToFile() {
        Gson gson = new Gson();
        Cart cartEmpty = new Cart("lena-Empty-cart");
        Parser parser = new JsonParser();

        parser.writeToFile(cartEmpty);

        String resultEmptyCart = gson.toJson(cartEmpty);
        String expectedResultEmptyCart = "{\"cartName\":\"lena-Empty-cart\",\"realItems\":[],\"virtualItems\":[],\"total\":0.0}";

        assertEquals(resultEmptyCart, expectedResultEmptyCart);
        System.out.println(resultEmptyCart);
    }
}

