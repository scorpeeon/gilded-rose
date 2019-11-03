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
        int updatedQuality = item.quality;
        if (item.sellIn > 10) {
            updatedQuality += 1;
        } else if (item.sellIn > 5) {
            updatedQuality += 2;
        } else if (item.sellIn > 0) {
            updatedQuality += 3;
        } else {
            updatedQuality = 0;
        }
        item.quality = itemQualityClamper.getClampedItemQuality(updatedQuality);
        item.sellIn--;
    }
}
