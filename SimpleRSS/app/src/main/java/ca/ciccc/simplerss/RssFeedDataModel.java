package ca.ciccc.simplerss;


public class RssFeedDataModel {
    private String thumbnail;
    private String title;
    private String description;
    private String url;
    private String date;


    public RssFeedDataModel(String thumbnail, String title, String description, String url, String date) {
        this.thumbnail = thumbnail;
        this.title = title;
        this.description = description;
        this.url = url;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
