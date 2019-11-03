package com.gildedrose;

public class ItemQualityClamper {
    static final int ITEM_QUALITY_MIN = 0;
    static final int ITEM_QUALITY_MAX = 50;


    public int getClampedItemQuality(final int quality) {
        return Math.max(ITEM_QUALITY_MIN, Math.min(ITEM_QUALITY_MAX, quality));
    }
}
