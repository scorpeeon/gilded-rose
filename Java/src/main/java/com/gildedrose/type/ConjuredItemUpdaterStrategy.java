package com.gildedrose.type;

import com.gildedrose.Item;
import com.gildedrose.ItemQualityClamper;
import com.gildedrose.ItemUpdaterStrategy;

public class ConjuredItemUpdaterStrategy implements ItemUpdaterStrategy {
    private final ItemQualityClamper itemQualityClamper;

    public ConjuredItemUpdaterStrategy(ItemQualityClamper itemQualityClamper) {
        this.itemQualityClamper = itemQualityClamper;
    }

    @Override
    public void update(final Item item) {
        int updatedQuality = item.quality;
        if (item.sellIn > 0) {
            updatedQuality -= 2;
        } else {
            updatedQuality -= 4;
        }
        item.quality = itemQualityClamper.getClampedItemQuality(updatedQuality);
        item.sellIn--;
    }
}
