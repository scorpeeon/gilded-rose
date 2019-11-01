package com.gildedrose;

public class ItemQualityClamper {
    static final int ITEM_QUALITY_MIN = 0;
    static final int ITEM_QUALITY_MAX = 50;


    public void clampItemQuality(final Item item) {
        if (item.quality > ITEM_QUALITY_MAX) {
            item.quality = ITEM_QUALITY_MAX;
        } else if (item.quality < ITEM_QUALITY_MIN) {
            item.quality = ITEM_QUALITY_MIN;
        }
    }
}
