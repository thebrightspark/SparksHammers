package com.brightspark.sparkshammers.item;

public interface IColourable
{
    /**
     * Returns the hexadecimal colour to tint the grey-scale texture with.
     * Return -1 for this item to be excluded from the item colour handler.
     */
    int getTextureColour();
}
