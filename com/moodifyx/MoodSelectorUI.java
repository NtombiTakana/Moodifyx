package com.moodifyx;

import java.util.LinkedList;
import java.util.ArrayList;


import javax.swing.*;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class MoodSelectorUI extends JFrame{
    private final MoodRepository repository;


    private final LinkedList<String> recentMoods = new LinkedList<>();
    private final ArrayList<String> songHistory = new ArrayList<>();

   public MoodSelectorUI(MoodRepository repository) {
    this.repository = repository;

    setTitle("Moodifyx");
    setSize(400, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    setupUI();
    setVisible(true);
}


    private void setupUI(){
        Set<String> moods = repository.getAllMoods();
        JComboBox<String> moodDropdown = new JComboBox<>(moods.toArray(new String[0]));
        JButton detectMoodBtn = new JButton("Recommend Music");

        detectMoodBtn.addActionListener(e ->{
            String mood = (String) moodDropdown.getSelectedItem();

            if (recentMoods.size() >= 5) recentMoods.removeFirst();
             recentMoods.add(mood);


            while(true){
                List<Song> songs = repository.getSongsMood(mood);
                Song selected = songs.get(ThreadLocalRandom.current().nextInt(songs.size()));

                songHistory.add(selected.getTitle());

                System.out.println("Recent Moods: " + recentMoods);
                System.out.println("Song History: " + songHistory);

                SongPlayer.play(selected.getFilePath());

                Object[] options = {"Another Song", "Change Mood", "Stop Music and Exit"};
                int choice = JOptionPane.showOptionDialog(
                this,
                "🎵 Now Playing 🎵\n" +
                "Title: " + selected.getTitle() + "\n" +
                "Artist: " + selected.getArtist() + "\n" +
                "Duration: " + selected.getDuration() + "s\n" +
                "Mood: " + mood,
                 "Your Mood Music",

                    
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
                );
                SongPlayer.stop();

                if(choice == JOptionPane.YES_OPTION){
                    continue;
                }else if(choice == JOptionPane.NO_OPTION){
                    break;
                }else{
                    System.exit(0);
                }

            }
        });
        JPanel panel = new JPanel();
            panel.add(new JLabel("Select Your Mood: "));
            panel.add(moodDropdown);
            panel.add(detectMoodBtn);

            JButton showHistoryBtn = new JButton("View History");
            showHistoryBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(
            this,
            "Recent Moods: " + recentMoods + "\nSongs Played: " + songHistory,
             "History",
            JOptionPane.INFORMATION_MESSAGE
       );
   });
            panel.add(showHistoryBtn);


            add(panel);

        
    }
}
