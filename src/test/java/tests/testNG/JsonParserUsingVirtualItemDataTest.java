package tests.testNG;

import com.google.gson.Gson;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import parser.JsonParser;
import parser.Parser;
import shop.Cart;
import shop.VirtualItem;


public class JsonParserUsingVirtualItemDataTest {


    @DataProvider(name = "test Virtual Items with All Data", parallel = true)
    public Object[][] createVirtualItemWithAllData() {
        return new Object[][]{
                {new Cart("Virtual-1-cart"), 0.2, "First Virtual Item", 2000},
                {new Cart("Virtual-2-cart"), 200.0022, "Second Virtual Item", 0},
                {new Cart("Virtual-3-cart"), 0, "Third Virtual Item", 0.202}
        };
    }

    @Test(dataProvider = "test Virtual Items with All Data", groups = {"data test"})
    public void verifyVirtualItemWithAllDataInFile(Cart cart, double sizeOnDisk, String name, double price) {

        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(sizeOnDisk);
        virtualItem.setName(name);
        virtualItem.setPrice(price);

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);

        final Thread thread = Thread.currentThread();
        System.out.printf("#%d %s: %s : %s : %s : %s", thread.getId(), thread.getName(), result, sizeOnDisk, name, price);
        System.out.println();
    }

    @DataProvider(name = "test Virtual Items without Name")
    public Object[][] createVirtualItemWithoutName() {
        return new Object[][]{
                {new Cart("Virtual-4-cart"), 2000, 0.2},
                {new Cart("Virtual-5-cart"), 0, 200.0022},
                {new Cart("Virtual-6-cart"), 1001, 0.102}
        };
    }

    @Test(dataProvider = "test Virtual Items without Name", groups = {"data test"}, enabled = false)
    public void verifyVirtualItemWithoutNameInFile(Cart cart, double sizeOnDisk, double price) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(sizeOnDisk);
        virtualItem.setPrice(price);

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);
    }

    @DataProvider(name = "test Virtual Items without sizeOnDisk")
    public Object[][] createVirtualItemWithoutSizeOnDisk() {
        return new Object[][]{
                {new Cart("Virtual-7-cart"), "Seven", 123},
                {new Cart("Virtual-8-cart"), "Eight", 1.01},
                {new Cart("Virtual-9-cart"), "Nine", 12.21}
        };
    }

    @Test(dataProvider = "test Virtual Items without sizeOnDisk", groups = {"data test"})
    public void verifyVirtualItemWithoutSizeOnDiskInFile(Cart cart, String name, double price) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setName(name);
        virtualItem.setPrice(price);

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);
    }

    @DataProvider(name = "test Virtual Items without Price")
    public Object[][] createVirtualItemWithoutPrice() {
        return new Object[][]{
                {new Cart("Virtual-10-cart"), 1, "Ten"},
                {new Cart("Virtual-11-cart"), 1000, "Eleven"},
                {new Cart("Virtual-12-cart"), 0.1, "Twelve"}
        };
    }

    @Test(dataProvider = "test Virtual Items without Price", groups = {"data test"})
    public void verifyVirtualItemWithoutPriceInFile(Cart cart, double sizeOnDisk, String name) {
        Gson gson = new Gson();

        VirtualItem virtualItem = new VirtualItem();
        virtualItem.setSizeOnDisk(sizeOnDisk);
        virtualItem.setName(name);

        cart.addVirtualItem(virtualItem);

        Parser parser = new JsonParser();
        parser.writeToFile(cart);

        String result = gson.toJson(cart);
        System.out.println(result);
    }
}

