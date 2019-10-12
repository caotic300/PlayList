package com.PlayList;


import javax.print.DocFlavor;
import java.io.IOException;
import java.util.*;

public class Main {


    public static void main(String[] args) {
        // write your code here
        ArrayList<Album> albums = new ArrayList<Album>();
        LinkedList<Song> playList = new LinkedList<Song>();

        addSong(playList, albums, "We love you", 1.35);
        addSong(playList, albums, "we want you", 4.5);
        addSong(playList, albums, "In the name of Love", 3.5);
        addSong(playList, albums, "I need your love", 3.20);
        printMenu();
        playSong(playList);

    }

    private static void printMenu() {
        System.out.println("0 - Quit\n" +
                "1 - Skip to the next song\n" +
                "2 - Skip backwards to a previous song\n" +
                "3 - Replay the current song\n" +
                "4 - List the songs in full\n");
    }

    private static boolean addSong(LinkedList<Song> playList, ArrayList<Album> albums, String song, double duration) {
        ListIterator<Song> listIterator = playList.listIterator();
        while (listIterator.hasNext()) {
            int comparison = listIterator.next().getTitle().compareTo(song);
            if (comparison == 0) {
                System.out.println(song + " is already in the playlist");
                return false;
            } else if (comparison > 0 && isSongInAlbum(albums, song)) {
                //this song should be added before this one
                listIterator.previous();
                listIterator.add(new Song(song, duration));
                return true;
            } else if (comparison < 0 && isSongInAlbum(albums, song)) {
                //move to next Song

            }
        }

        listIterator.add(new Song(song, duration));
        return true;
    }

    private boolean removeSong(LinkedList<Song> playList, String songTitle) {
        ListIterator<Song> listIterator = playList.listIterator();
        while (listIterator.hasNext()) {
            int comparison = listIterator.next().getTitle().compareTo(songTitle);
            if (comparison == 0 && isSongInPlayList(playList, songTitle)) {
                listIterator.remove();
                System.out.println(songTitle + " has been removed from the playlist");
                return true;
            }
        }

        return false;
    }

    private static boolean isSongInAlbum(ArrayList<Album> albums, String songTitle) {

        for (Album album : albums) {
            boolean songFound = album.getSongsList().element().getTitle().equals(songTitle);
            if (songFound) {

                return true;
            }
        }
        return false;

    }

    private static boolean isSongInPlayList(LinkedList<Song> playList, String songTitle) {

        boolean songFound = playList.element().getTitle().equals(songTitle);

        if (songFound) {

            return true;
        }

        return false;

    }

    public static void printList(LinkedList<Song> playList) {
        Iterator<Song> i = playList.iterator();
        System.out.println("===========================");
        while (i.hasNext()) {
            int num = 1;

            System.out.println(num + ": " + i.next());

        }
        System.out.println("===========================");
    }

    public static void playSong(LinkedList playList) {
       Scanner scanner = new Scanner(System.in);
       ListIterator<Song> listIterator = playList.listIterator();
       boolean quit = false;
       boolean forward = true;
       while (!quit) {
           int action = scanner.nextInt();
           scanner.nextLine();

           switch (action) {
               case 0:
                   System.out.println("PlayList complete.");
                   quit = true;
                   break;
               case 1:
                   if (!forward) {
                       if (listIterator.hasNext()) {
                           listIterator.next();
                       }
                       forward = true;
                   }

                   if (listIterator.hasNext()) {
                       System.out.println("Now playing " + listIterator.next().toString());
                   } else {
                       System.out.println("We are at the end of the playlist");
                   }
                   break;

               case 2:
                   if (forward) {
                       if (listIterator.hasPrevious()) {
                           listIterator.previous();
                       }
                       forward = false;
                   }

                   if (listIterator.hasPrevious()) {
                       System.out.println("Now playing " + listIterator.previous().toString());
                   } else {
                       System.out.println("We are at the start of the list");
                   }

                   break;

               case 3:
                   if (forward) {
                       if (listIterator.hasPrevious()) {
                           System.out.println("Now replaying " + listIterator.previous().toString());
                       } else {
                           System.out.println("We are at the start of the list");
                       }
                       forward = false;
                   } else {
                       if(listIterator.hasNext()) {
                           System.out.println("Now replaying " + listIterator.next().toString());
                       } else {
                           System.out.println("We are at the end of the playlist");
                       }
                       forward = true;
                   }

                   break;

               case 4:
                   printList(playList);
                   break;

               case 5:
                   printMenu();
                   break;

               case 6:

                   if (playList.size() > 0) {
                       listIterator.remove();
                       if (listIterator.hasNext()) {
                           System.out.println("Now playing " + listIterator.next().toString());
                       } else if (listIterator.hasPrevious()) {
                           System.out.println("Now playing " + listIterator.previous().toString());
                       }
                   }

                   break;


           }
       }
    }

}




