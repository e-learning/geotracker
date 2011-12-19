package ru.eltech.elerning.geotracker.core.model;

import java.util.List;

/**
 * Author: Kirill Korgov (korgov@yandex-team.ru)
 * Date: 12/19/11
 */
public class Channel {
    private final List<String> tags;
    private final String name;
    private final String description;

    public Channel(final List<String> tags, final String name, final String description) {
        this.tags = tags;
        this.name = name;
        this.description = description;
    }

    public List<String> getTags() {
        return tags;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @SuppressWarnings({"RedundantIfStatement"})
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Channel channel = (Channel) o;

        if (description != null ? !description.equals(channel.description) : channel.description != null) return false;
        if (name != null ? !name.equals(channel.name) : channel.name != null) return false;
        if (tags != null ? !tags.equals(channel.tags) : channel.tags != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = tags != null ? tags.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "\n\t tags=" + tags +
                ",\n\t name='" + name + '\'' +
                ",\n\t description='" + description + '\'' +
                "\n}";
    }
}
