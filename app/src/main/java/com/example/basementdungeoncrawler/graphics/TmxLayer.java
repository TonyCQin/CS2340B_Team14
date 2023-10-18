package com.example.basementdungeoncrawler.graphics;

public class TmxLayer {
    private String name;
    private String tileData;  // Tile data for the layer (e.g., CSV or base64-encoded data)

    public TmxLayer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getTileData() {
        return tileData;
    }

    public void setTileData(String tileData) {
        this.tileData = tileData;
    }
}

