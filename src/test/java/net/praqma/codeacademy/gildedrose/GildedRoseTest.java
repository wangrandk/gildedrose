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

    // Once the sell by date has passed, Quality degrades twice as fast
    @Test
    public void double_quality_degrade_after_passed_sell_by_date () {
    
        int quality = 50;
        Item i = new Item ("foo", 1, quality);
        Item[] items = new Item[] { i };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(0, app.items[0].sellIn);
        // degrade by 1
        assertEquals(49, app.items[0].quality);

        app.updateQuality();

        // dobelt degrade   
        assertEquals(-1, app.items[0].sellIn);
        // degrade by 2
        assertEquals(47, app.items[0].quality);

    }

    // - The Quality of an item is never negative
    @Test
    public void never_negative_quality (){

        int quality = 0;
        Item i = new Item ("foo", 1, quality);
        Item[] items = new Item[] { i };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(0, app.items[0].sellIn);
        // NOT degrade by 1
        assertEquals(0, app.items[0].quality);
 
        app.updateQuality();
   
        assertEquals(-1, app.items[0].sellIn);
        // NOT degrade by 1
        assertEquals(0, app.items[0].quality);

    }

    // - "Aged Brie" actually increases in Quality the older it gets
    @Test
    public void aged_brie_increases_in_quality (){

        int quality = 0;
        Item i = new Item ("Aged Brie", 10, quality);
        Item[] items = new Item[] { i };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(9, app.items[0].sellIn);
        // increased by 1
        assertEquals(1, app.items[0].quality);
 
        app.updateQuality();
   
        assertEquals(8, app.items[0].sellIn);
        // Increased by 1
        assertEquals(2, app.items[0].quality);

    }

    // - The Quality of an item is never more than 50
    // TODO: fix that you cannot add an item with more than 50 in quality
    @Test
    public void quality_cant_be_more_than_50 (){

        //Aged Brie increases
        Item i = new Item ("Aged Brie", 10, 50);
        Item i2 = new Item ("Foo", 10, 50);

        Item[] items = new Item[] { i, i2 };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        assertEquals(9, app.items[1].sellIn);
        assertEquals(49, app.items[1].quality);

 
        app.updateQuality();
   
        assertEquals(8, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);

        assertEquals(8, app.items[1].sellIn);
        assertEquals(48, app.items[1].quality);

    }

    // - "Sulfuras", being a legendary item, never has to be sold or decreases in Quality
    @Test
    public void sulfuras_never_sold_or_decreases (){

        int quality = 80;
        Item i = new Item ("Sulfuras, Hand of Ragnaros", 0, quality);

        Item[] items = new Item[] { i};
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);

        app.updateQuality();
   
        assertEquals(0, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);

    }



    // - "Backstage passes", like aged brie, increases in Quality as its SellIn value approaches;
    // Quality increases by 2 when there are 10 days or less and by 3 when there are 5 days or less but
    // Quality drops to 0 after the concert

    @Test
    public void backstage_increases_in_quality (){

        int quality = 10;
        Item i = new Item ("Backstage passes to a TAFKAL80ETC concert", 30, quality);

        Item[] items = new Item[] { i };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(29, app.items[0].sellIn);
        assertEquals(11, app.items[0].quality);

        app.updateQuality();
   
        assertEquals(28, app.items[0].sellIn);
        assertEquals(12, app.items[0].quality);

    }

    @Test
    public void backstage_increases_by_2_in_quality_when_10_or_less (){

        int quality = 0;
        Item i = new Item ("Backstage passes to a TAFKAL80ETC concert", 11, quality);

        Item[] items = new Item[] { i };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(10, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);

        app.updateQuality();
   
        assertEquals(9, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);

    }

    @Test
    public void backstage_increases_by_3_in_quality_when_5_or_less (){

        int quality = 0;
        Item i = new Item ("Backstage passes to a TAFKAL80ETC concert", 6, quality);

        Item[] items = new Item[] { i };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(5, app.items[0].sellIn);
        assertEquals(2, app.items[0].quality);

        app.updateQuality();
   
        assertEquals(4, app.items[0].sellIn);
        assertEquals(5, app.items[0].quality);

    }


    // 	- "Conjured" items degrade in Quality twice as fast as normal items

    @Test
    public void conjured_items_degrade_twice_as_fast (){

        int quality = 50;
        Item i = new Item ("Conjured", 10, quality);

        Item[] items = new Item[] { i };
    
        GildedRose app = new GildedRose(items);
        app.updateQuality();
   
        assertEquals(9, app.items[0].sellIn);
        assertEquals(48, app.items[0].quality);

        app.updateQuality();
   
        assertEquals(8, app.items[0].sellIn);
        assertEquals(46, app.items[0].quality);

    }

}
