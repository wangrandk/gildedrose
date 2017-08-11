package net.praqma.codeacademy.gildedrose;


import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GildedRoseTest {

    @Test
    public void foo() {
 
        Item i = new Item("foo", 0,50);

        Item[] items = new Item[] { i };


        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        // System.out.println(i.toString());

        assertEquals("foo", app.items[0].name);
    }
}
