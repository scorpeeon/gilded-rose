package com.gildedrose.type;

import com.gildedrose.Item;
import com.gildedrose.ItemQualityClamper;
import com.gildedrose.ItemUpdaterStrategy;

public class BackstagePassesItemUpdaterStrategy implements ItemUpdaterStrategy {
    private final ItemQualityClamper itemQualityClamper;

    public BackstagePassesItemUpdaterStrategy(ItemQualityClamper itemQualityClamper) {
        this.itemQualityClamper = itemQualityClamper;
    }

    @Override
    public void update(final Item item) {
        if (item.sellIn > 10) {
            item.quality += 1;
        } else if (item.sellIn > 5) {
            item.quality += 2;
        } else if (item.sellIn > 0) {
            item.quality += 3;
        } else {
            item.quality = 0;
        }
        itemQualityClamper.clampItemQuality(item);
        item.sellIn--;
    }
}
