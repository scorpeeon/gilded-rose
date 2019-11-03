package com.gildedrose.type;

import com.gildedrose.Item;
import com.gildedrose.ItemQualityClamper;
import com.gildedrose.ItemUpdaterStrategy;

public class AgedBrieItemUpdaterStrategy implements ItemUpdaterStrategy {
    private final ItemQualityClamper itemQualityClamper;

    public AgedBrieItemUpdaterStrategy(ItemQualityClamper itemQualityClamper) {
        this.itemQualityClamper = itemQualityClamper;
    }

    @Override
    public void update(final Item item) {
        int updatedQuality = item.quality;
        if (item.sellIn > 0) {
            updatedQuality += 1;
        } else {
            updatedQuality += 2;
        }
        item.quality = itemQualityClamper.getClampedItemQuality(updatedQuality);
        item.sellIn--;
    }
}
