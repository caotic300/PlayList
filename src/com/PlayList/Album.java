package com.PlayList;


import java.util.LinkedList;



public class Album {
    private LinkedList<Song> songsList;

    public Album(LinkedList<Song> songsList) {
        this.songsList = songsList;
    }

    public boolean addSong(String songTitle, double duration) {
        if (isSong(songTitle)) {
            System.out.println("song already in the list of songs");
            return false;
        }

        songsList.add(new Song(songTitle, duration));
        return true;
    }

    public boolean removeSong(String songTitle) {
        if (isSong(songTitle)) {
            songsList.remove(findIndex(songTitle));
            return true;
        }

        return false;
    }

    private boolean isSong(String songTitle) {
        for (Song song : songsList) {
            if (song.getTitle().equals(songTitle)) {
                return true;
            }
        }
        return false;
    }

    private int findIndex(String songTitle) {

        for (Song song : songsList) {
            if (song.getTitle().equals(songTitle)) {
                return songsList.indexOf(song);
            }
        }

        return -1;
    }

    public LinkedList<Song> getSongsList() {
        return songsList;
    }



}

