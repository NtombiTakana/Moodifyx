package com.moodifyx;

import java.util.List;

public class Main {
    public static void main(String[]args){
       // new MoodSelectorUI();
        MoodRepository repo = new MoodRepository();
List<Song> customSongs = List.of(
    new Song("User Vibe", "music/uservibe.wav", "User Artist", 120, "art/uservibe.jpg")
);
repo.addCustomMood("UserMood", customSongs);
System.out.println("Custom Mood Added: UserMood");

new MoodSelectorUI(repo);

    }
}
