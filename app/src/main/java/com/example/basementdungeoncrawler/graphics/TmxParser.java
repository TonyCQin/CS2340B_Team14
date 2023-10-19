package com.example.basementdungeoncrawler.graphics;
import com.example.basementdungeoncrawler.R;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.util.Xml;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class TmxParser {
    private Context context;

    public TmxParser(Context context) {
        this.context = context;
    }

    public List<List<Integer>> parseTmxFile(int tmxResourceId) {
        List<List<Integer>> tileIdLists = new ArrayList<>();

        try {
            // Open the XML resource as an InputStream
            InputStream inputStream = context.getResources().openRawResource(tmxResourceId);

            // Create an XmlPullParser to parse the XML
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser parser = factory.newPullParser();

            // Set the input stream for the parser
            parser.setInput(inputStream, null);

            List<Integer> currentTileIdList = null;
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    String tagName = parser.getName();
                    if ("layer".equals(tagName)) {
                        currentTileIdList = new ArrayList<>();
                    } else if ("data".equals(tagName) && currentTileIdList != null) {
                        String tileData = parser.nextText();
                        currentTileIdList.addAll(parseTileIds(tileData));
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    String tagName = parser.getName();
                    if ("layer".equals(tagName) && currentTileIdList != null) {
                        tileIdLists.add(currentTileIdList);
                        currentTileIdList = null;
                    }
                }
                eventType = parser.next();
            }

            // Close the input stream when done
            inputStream.close();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

        return tileIdLists;
    }

    private List<Integer> parseTileIds(String tileIdsString) {
        List<Integer> tileIds = new ArrayList<>();
        String[] tileIdStrings = tileIdsString.trim().split(",");
        for (String tileIdString : tileIdStrings) {
            try {
                int tileId = Integer.parseInt(tileIdString.trim());
                tileIds.add(tileId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return tileIds;
    }
}
