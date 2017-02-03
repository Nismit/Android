package ca.ciccc.simplerss.rss;


import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class AtomFeedParser {

    private static final int TAG_ID = 1;
    private static final int TAG_TITLE = 2;
    private static final int TAG_SUMMARY = 3;
    private static final int TAG_LINK = 4;
    private static final int TAG_THUMBNAIL = 5;
    private static final int TAG_PUBLISHED = 6;
    private static final String ns = null;

    public List parse(InputStream in) throws XmlPullParserException, IOException, ParseException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return reedFeed(parser);
        }finally {
            in.close();
        }
    }

    private List reedFeed(XmlPullParser parser) throws XmlPullParserException, IOException, ParseException {
        List entries = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while (parser.next() != XmlPullParser.END_TAG) {
            if(parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            if(name.equals("entry")) {
                entries.add(readEntry(parser));
            }else {
                skip(parser);
            }
        }

        return entries;
    }



    private Entry readEntry(XmlPullParser parser) throws XmlPullParserException, IOException, ParseException {
        parser.require(XmlPullParser.START_TAG, ns, "entry");
        String id = null;
        String title = null;
        String summary = null;
        String link = null;
        String thumbnail = null;
        long publishedOn = 0;

        while (parser.next() != XmlPullParser.END_TAG) {
            if(parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            if(name.equals("id")) {
                id = readTag(parser, "id", TAG_ID);
            }else if(name.equals("title")) {
                title = readTag(parser, "title", TAG_TITLE);
            }else if(name.equals("summary")) {
                summary = readTag(parser, "summary", TAG_SUMMARY);
            }else if(name.equals("link")) {
                link = readTag(parser, "link", TAG_LINK);
            }else if(name.equals("thumbnail")) {
                thumbnail = readTag(parser, "thumbnail", TAG_THUMBNAIL);
            }else {
                skip(parser);
            }
        }

        return new Entry(id, title, summary, link, thumbnail, publishedOn);
    }

    private String readTag(XmlPullParser parser, String tagName, int tagType) throws XmlPullParserException, IOException {

        switch (tagType) {
            case TAG_ID:
                return readBasicTag(parser, tagName);
            case TAG_TITLE:
                return readBasicTag(parser, tagName);
            case TAG_SUMMARY:
                return readBasicTag(parser, tagName);
            case TAG_LINK:
                return readBasicTag(parser, tagName);
            case TAG_THUMBNAIL:
                return readBasicTag(parser, tagName);
            case TAG_PUBLISHED:
                return readBasicTag(parser, tagName);
            default:
                throw new IllegalArgumentException("Unknown tag type:" + tagType);
        }
    }

    private String readBasicTag(XmlPullParser parser, String tag)
            throws IOException, XmlPullParserException {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        String result = readText(parser);
        parser.require(XmlPullParser.END_TAG, ns, tag);
        return result;
    }

    private String readText(XmlPullParser parser) throws XmlPullParserException, IOException {
        String result = null;
        if(parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }


    private void skip(XmlPullParser parser) throws XmlPullParserException, IOException {
        if(parser.getEventType() != XmlPullParser.START_TAG) {
            throw new IllegalStateException();
        }

        int depth = 1;
        while (depth != 0) {
            switch (parser.next()) {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }

    public static class Entry {
        public final String id;
        public final String title;
        public final String link;
        public final String summary;
        public final String thumbnail;
        public final long published;

        Entry(String id, String title, String summary, String link, String thumbnail, long published) {
            this.id = id;
            this.title = title;
            this.summary = summary;
            this.link = link;
            this.thumbnail = thumbnail;
            this.published = published;
        }
    }

}
