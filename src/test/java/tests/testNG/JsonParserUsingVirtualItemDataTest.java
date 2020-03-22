package tests.testNG;

import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.VirtualItem;

import static org.testng.Assert.*;


public class JsonParserUsingVirtualItemDataTest {

    @Test(dataProvider = "test Virtual Item with All Data", groups = {"data test"})
    public void verifyVirtualItemWithAllDataInFileTest(Cart newCart, double sizeOnDisk, String name, double price) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(sizeOnDisk);
        virtualItem.setName(name);
        virtualItem.setPrice(price);

        newCart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(newCart);

        String result = gson.toJson(newCart);
        String expectedCart = "{\"cartName\":\"Virtual-1-cart\",\"realItems\":[],\"virtualItems\":[{\"sizeOnDisk\":2000.0,\"name\":\"Second Virtual Item\",\"price\":200.0}],\"total\":240.0}";
        System.out.println(result);

        assertEquals(result, expectedCart);
    }

    @Test(dataProvider = "test Virtual Items without Name", groups = {"data test"})
    public void verifyVirtualItemWithoutNameInFileTest(Cart cart, double sizeOnDisk, double price) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(sizeOnDisk);
        virtualItem.setPrice(price);

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        assertFalse(result.contains("\"virtualItems\":\"name\""));
    }


    @Test(dataProvider = "test Virtual Items without sizeOnDisk", groups = {"data test"})
    public void verifyVirtualItemWithoutSizeOnDiskInFileTest(Cart cart, String name, double price) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(name);
        virtualItem.setPrice(price);

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        assertTrue(result.contains("\"sizeOnDisk\":0.0"));
    }

    @Test(dataProvider = "test Virtual Items without Price", groups = {"data test"})
    public void verifyVirtualItemWithoutPriceInFileTest(Cart cart, double sizeOnDisk, String name) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(sizeOnDisk);
        virtualItem.setName(name);

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);

        assertTrue(result.contains("\"price\":0.0"));
    }

    @Test(dataProvider = "test Cart without Name", groups = {"data test"})
    public void verifyCartWithoutNameTest(Cart newCart, double sizeOnDisk, String name, double price) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(sizeOnDisk);
        virtualItem.setName(name);
        virtualItem.setPrice(price);

        newCart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(newCart);

        String result = gson.toJson(newCart);
        String expectedCart = "{\"cartName\":\"\",\"realItems\":[],\"virtualItems\":[{\"sizeOnDisk\":10.0,\"name\":\"VItemOnly\",\"price\":1.0}],\"total\":1.2}";
        System.out.println(result);

        assertEquals(result, expectedCart);
    }

    @DataProvider(name = "test Virtual Item with All Data", parallel = true)
    public Object[][] createVirtualItemWithAllData() {
        return new Object[][]{
                {new Cart("Virtual-1-cart"), 2000, "Second Virtual Item", 200}
        };
    }

    @DataProvider(name = "test Virtual Items without Name")
    public Object[][] createVirtualItemWithoutName() {
        return new Object[][]{
                {new Cart("Virtual-2-cart"), 200, 0.22},
                {new Cart("Virtual-3-cart"), 0, 20.022}
        };
    }

    @DataProvider(name = "test Virtual Items without sizeOnDisk")
    public Object[][] createVirtualItemWithoutSizeOnDisk() {
        return new Object[][]{
                {new Cart("Virtual-4-cart"), "Four", 123},
                {new Cart("Virtual-5-cart"), "Five", 1.01}
        };
    }

    @DataProvider(name = "test Virtual Items without Price")
    public Object[][] createVirtualItemWithoutPrice() {
        return new Object[][]{
                {new Cart("Virtual-6-cart"), 1, "Six"},
                {new Cart("Virtual-7-cart"), 1000, "Seven"},
        };
    }

    @DataProvider(name = "test Cart without Name")
    public Object[][] createCartWithoutName() {
        return new Object[][]{
                {new Cart(""), 10, "VItemOnly", 1}
        };
    }
}

