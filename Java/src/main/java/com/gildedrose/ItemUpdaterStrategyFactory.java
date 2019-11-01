package com.gildedrose;

import com.gildedrose.type.*;

public class ItemUpdaterStrategyFactory {
    static final String ITEM_NAME_AGED_BRIE = "Aged Brie";
    static final String ITEM_NAME_BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    static final String ITEM_NAME_SULFURAS = "Sulfuras, Hand of Ragnaros";
    static final String ITEM_NAME_PREFIX_CONJURED = "Conjured";

    public static ItemUpdaterStrategy getStrategyForItem(final Item item) {
        if (item.name.equals(ITEM_NAME_AGED_BRIE)) {
            return new AgedBrieItemUpdaterStrategy(new ItemQualityClamper());
        } else if (item.name.equals(ITEM_NAME_BACKSTAGE_PASSES)) {
            return new BackstagePassesItemUpdaterStrategy(new ItemQualityClamper());
        } else if (item.name.equals(ITEM_NAME_SULFURAS)) {
            return new SulfurasItemUpdaterStrategy();
        } else if (item.name.startsWith(ITEM_NAME_PREFIX_CONJURED)) {
            return new ConjuredItemUpdaterStrategy(new ItemQualityClamper());
        } else {
            return new RegularItemUpdaterStrategy(new ItemQualityClamper());
        }
    }
}
