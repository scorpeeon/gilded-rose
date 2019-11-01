package com.gildedrose;


import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GildedRoseTest {

    public static final String ITEM_NAME_AGED_BRIE = "Aged Brie";
    public static final String ITEM_NAME_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String ITEM_NAME_SULFURAS = "Sulfuras, Hand of Ragnaros";

    public static final int ITEM_QUALITY_MAX = 50;
    public static final int ITEM_QUALITY_SULFURAS = 80;

    /**
     * NOTE: not in specification, just a basic sanity test
     */
    @Test
    public void updateDoesNotChangeItemName() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].name).isEqualTo("foo");
    }

    /**
     * NOTE: not in specification, just a basic sanity test
     */
    @Test
    public void updateDoesNotChangeItemsSize() {
        Item[] items = new Item[] { new Item("foo", 0, 0), new Item("foo2", 0, 0), new Item("foo3", 0, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items.length).isEqualTo(items.length);
    }

    @Test
    public void qualityIsLowered() {
        Item[] items = new Item[] { new Item("foo", 5, 1), new Item("foo2", 1, 2) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
        assertThat(app.items[1].quality).isEqualTo(1);
    }

    @Test
    public void sellInIsLowered() {
        Item[] items = new Item[] { new Item("foo", 5, 1), new Item("foo2", 1, 2) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(4);
        assertThat(app.items[1].sellIn).isEqualTo(0);
    }

    @Test
    public void qualityDegradesTwiceAsFastAfterSellInDatePassed() {
        Item[] items = new Item[] { new Item("foo", 0, 10), new Item("foo2", -10, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(8);
        assertThat(app.items[1].quality).isEqualTo(3);
    }

    @Test
    public void qualityCanNotBecomeNegative() {
        Item[] items = new Item[] { new Item("foo", 5, 0), new Item("foo2", -5, 0) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(0);
        assertThat(app.items[1].quality).isEqualTo(0);
    }

    /**
     * NOTE: not explicitly in specification, assuming this is the expected behaviour based on current implementation
     */
    @Test
    public void sellInCanBecomeNegative() {
        Item[] items = new Item[] { new Item("foo", 0, 5), new Item("foo2", -10, 5) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(-1).isEqualTo(app.items[0].sellIn);
        assertThat(-11).isEqualTo(app.items[1].sellIn);
    }

    @Test
    public void agedBrieQualityIncreases() {
        Item[] items = new Item[] { new Item(ITEM_NAME_AGED_BRIE, 5, 1), new Item(ITEM_NAME_AGED_BRIE, 1, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(2);
        assertThat(app.items[1].quality).isEqualTo(11);
    }

    /**
     * NOTE: not explicitly in specification, assuming this is the expected behaviour based on current implementation
     */
    @Test
    public void agedBrieQualityIncreasesFasterAfterSellInDatePassed() {
        Item[] items = new Item[] { new Item(ITEM_NAME_AGED_BRIE, 0, 1), new Item(ITEM_NAME_AGED_BRIE, -10, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(3);
        assertThat(app.items[1].quality).isEqualTo(12);
    }

    @Test
    public void qualityCanNotExceedMaximum() {
        Item[] items = new Item[] { new Item(ITEM_NAME_AGED_BRIE, 5, 49), new Item(ITEM_NAME_AGED_BRIE, 5, 50),
                new Item(ITEM_NAME_AGED_BRIE, -5, 49), new Item(ITEM_NAME_AGED_BRIE, -5, 50),
                new Item(ITEM_NAME_BACKSTAGE_PASSES, 15, 49), new Item(ITEM_NAME_BACKSTAGE_PASSES, 15, 50),
                new Item(ITEM_NAME_BACKSTAGE_PASSES, 5, 49), new Item(ITEM_NAME_BACKSTAGE_PASSES, 5, 50),
                new Item(ITEM_NAME_BACKSTAGE_PASSES, 2, 49), new Item(ITEM_NAME_BACKSTAGE_PASSES, 2, 50),
                new Item(ITEM_NAME_BACKSTAGE_PASSES, 1, 49), new Item(ITEM_NAME_BACKSTAGE_PASSES, 1, 50),
                new Item(ITEM_NAME_BACKSTAGE_PASSES, 0, 49), new Item(ITEM_NAME_BACKSTAGE_PASSES, 0, 50),
                new Item(ITEM_NAME_BACKSTAGE_PASSES, -2, 49), new Item(ITEM_NAME_BACKSTAGE_PASSES, -2, 50)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items).allMatch(item -> item.quality <= ITEM_QUALITY_MAX);
    }

    @Test
    public void sulfurasQualityDoesNotChange() {
        Item[] items = new Item[] { new Item(ITEM_NAME_SULFURAS, 10, ITEM_QUALITY_SULFURAS), new Item(ITEM_NAME_SULFURAS, 0, ITEM_QUALITY_SULFURAS),
                new Item(ITEM_NAME_SULFURAS, -10, ITEM_QUALITY_SULFURAS)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items).allMatch(item -> item.quality == ITEM_QUALITY_SULFURAS);
    }

    @Test
    public void sulfurasSellInDoesNotChange() {
        Item[] items = new Item[] { new Item(ITEM_NAME_SULFURAS, 10, ITEM_QUALITY_SULFURAS), new Item(ITEM_NAME_SULFURAS, 0, ITEM_QUALITY_SULFURAS),
                new Item(ITEM_NAME_SULFURAS, -10, ITEM_QUALITY_SULFURAS)};
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].sellIn).isEqualTo(10);
        assertThat(app.items[1].sellIn).isEqualTo(0);
        assertThat(app.items[2].sellIn).isEqualTo(-10);
    }

    @Test
    public void backStagePassQualityIncreases() {
        Item[] items = new Item[] { new Item(ITEM_NAME_BACKSTAGE_PASSES, 15, 1), new Item(ITEM_NAME_BACKSTAGE_PASSES, 11, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(2);
        assertThat(app.items[1].quality).isEqualTo(11);
    }

    @Test
    public void backStagePassQualityIncreasesBy2Within10Days() {
        Item[] items = new Item[] { new Item(ITEM_NAME_BACKSTAGE_PASSES, 10, 1), new Item(ITEM_NAME_BACKSTAGE_PASSES, 6, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(3);
        assertThat(app.items[1].quality).isEqualTo(12);
    }

    @Test
    public void backStagePassQualityIncreasesBy3Within5Days() {
        Item[] items = new Item[] { new Item(ITEM_NAME_BACKSTAGE_PASSES, 5, 1), new Item(ITEM_NAME_BACKSTAGE_PASSES, 1, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items[0].quality).isEqualTo(4);
        assertThat(app.items[1].quality).isEqualTo(13);
    }

    @Test
    public void backStagePassQualitySetTo0AfterSellInDatePassed() {
        Item[] items = new Item[] { new Item(ITEM_NAME_BACKSTAGE_PASSES, 0, 5), new Item(ITEM_NAME_BACKSTAGE_PASSES, -10, 10) };
        GildedRose app = new GildedRose(items);

        app.updateQuality();

        assertThat(app.items).allMatch(item -> item.quality == 0);
    }

}
