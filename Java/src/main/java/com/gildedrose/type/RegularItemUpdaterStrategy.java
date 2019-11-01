package com.gildedrose.type;

import com.gildedrose.Item;
import com.gildedrose.ItemQualityClamper;
import com.gildedrose.ItemUpdaterStrategy;

public class RegularItemUpdaterStrategy implements ItemUpdaterStrategy {
    private final ItemQualityClamper itemQualityClamper;

    public RegularItemUpdaterStrategy(ItemQualityClamper itemQualityClamper) {
        this.itemQualityClamper = itemQualityClamper;
    }

    @Override
    public void update(final Item item) {
        if (item.sellIn > 0) {
            item.quality -= 1;
        } else {
            item.quality -= 2;
        }
        itemQualityClamper.clampItemQuality(item);
        item.sellIn--;
    }
}
