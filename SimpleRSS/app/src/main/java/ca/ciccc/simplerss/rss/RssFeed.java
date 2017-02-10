package ca.ciccc.simplerss.rss;

import java.util.ArrayList;

public class RssFeed {
    private String id;
    private String title;
    private String link;
    private String summary;
    private String content;
    private String thumbnail;
    private long published;

    public RssFeed(String id, String title, String summary, String content, String link, String thumbnail, long published) {
        this.id = id;
        this.title = title;
        this.summary = summary;
        this.content = content;
        this.link = link;
        this.thumbnail = thumbnail;
        this.published = published;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public long getPublished() {
        return published;
    }

    public void setPublished(long published) {
        this.published = published;
    }
}
