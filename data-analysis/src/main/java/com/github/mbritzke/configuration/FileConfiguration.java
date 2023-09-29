package com.github.mbritzke.configuration;

public class FileConfiguration {

    private String attributeSeparator;
    private String listStartSeparator;
    private String listEndSeparator;
    private String itemSeparator;
    private String itemAttributeSeparator;

    FileConfiguration(String attributeSeparator, String listStartSeparator, String listEndSeparator,
                      String itemSeparator, String itemAttributeSeparator) {
        this.attributeSeparator = attributeSeparator;
        this.listStartSeparator = listStartSeparator;
        this.listEndSeparator = listEndSeparator;
        this.itemSeparator = itemSeparator;
        this.itemAttributeSeparator = itemAttributeSeparator;
    }

    public String getAttributeSeparator() {
        return attributeSeparator;
    }

    public String getListStartSeparator() {
        return listStartSeparator;
    }

    public String getListEndSeparator() {
        return listEndSeparator;
    }

    public String getItemSeparator() {
        return itemSeparator;
    }

    public String getItemAttributeSeparator() {
        return itemAttributeSeparator;
    }
}
