package com.moodifyx;

public class Song {
    private final String title;
    private final String artist;
    private final int duration; // in seconds
    private final String albumArtPath;
    private final String filePath;

 public Song(String title, String filePath, String artist, int duration, String albumArtPath) {
        this.title = title;
        this.filePath = filePath;
        this.artist = artist;
        this.duration = duration;
        this.albumArtPath = albumArtPath;
    }

 public Song(String title, String filePath) {
        this(title, filePath, "Unknown Artist", 0, null);
    }

    public String getTitle() {
        return title;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getArtist() {
        return artist;
    }

    public int getDuration() {
        return duration;
    }

    public String getAlbumArtPath() {
        return albumArtPath;
    }
}
