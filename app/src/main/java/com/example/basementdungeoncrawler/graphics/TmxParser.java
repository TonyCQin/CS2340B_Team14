package com.example.basementdungeoncrawler.graphics;

import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TmxParser {
    public static List<TmxLayer> parseTmxFile(Resources resources, int tmxResourceId) {
        XmlResourceParser parser = resources.getXml(tmxResourceId);

        List<TmxLayer> layers = new ArrayList<>();
        TmxLayer currentLayer = null;

        try {
            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();
                    if (tagName.equals("layer")) {
                        currentLayer = new TmxLayer(parser.getAttributeValue(null, "name"));
                    } else if (tagName.equals("data") && currentLayer != null) {
                        String tileData = parser.nextText();
                        currentLayer.setTileData(tileData);
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    String tagName = parser.getName();
                    if (tagName.equals("layer") && currentLayer != null) {
                        layers.add(currentLayer);
                        currentLayer = null;
                    }
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        } finally {
            parser.close();
        }

        return layers;
    }
}

