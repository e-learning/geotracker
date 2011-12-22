package ru.eltech.elerning.geotracker.core.model;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/22/11
 */
public class RssRow {
    private final String channelName;
    private final String pubDate;
    private final String title;
    private final String descr;
    private final String link;
    private final String lat;
    private final String lon;
    private final String user;

    public RssRow(final String channelName, final String pubDate, final String title, final String descr, final String link, final String lat, final String lon, final String user) {
        this.channelName = channelName;
        this.pubDate = pubDate;
        this.title = title;
        this.descr = descr;
        this.link = link;
        this.lat = lat;
        this.lon = lon;
        this.user = user;
    }

    public String getChannelName() {
        return channelName;
    }

    public static int getColsCount(){
        return 8;
    }

    public String getPubDate() {
        return pubDate;
    }

    public String getTitle() {
        return title;
    }

    public String getDescr() {
        return descr;
    }

    public String getLink() {
        return link;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public String getUser() {
        return user;
    }
}
