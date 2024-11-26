package mk.finki.ukim.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class DataHolder {
    public static List<Artist> artists;
    public static List<Song> songs;
    public static List<Album> albums;

    @PostConstruct
    public void init() {
        artists = new ArrayList<>(5);
        songs = new ArrayList<>(5);

        artists.add(new Artist(new Random().nextLong(), "Bon", "Jove", "The Best"));
        artists.add(new Artist(new Random().nextLong(), "Axl", "Rose", "The Worst"));
        artists.add(new Artist(new Random().nextLong(), "David", "Bowie", "The Best"));
        Album album1=new Album(new Random().nextLong(), "Album11", "Pop", "2019", new ArrayList<>());
        Song song1 = new Song("1", "Song One", "Pop", 2021, artists, new Random().nextLong(), album1);
        Song song2 = new Song("2", "Song Two", "Rock", 2019, artists, new Random().nextLong(), album1);
        Song song3 = new Song("3", "Song Three", "Jazz", 2020, artists, new Random().nextLong(), album1);
        songs.add(song1);
        songs.add(song2);
        songs.add(song3);

        albums.add(new Album(new Random().nextLong(), "Album 1", "Pop","2024", songs));
        albums.add(new Album(new Random().nextLong(), "Album 2", "Rock","2024", songs));
        albums.add(new Album(new Random().nextLong(), "Album 3", "Pop-Rock","2024", songs));
        albums.add(new Album(new Random().nextLong(), "Album 4", "Jazz","2024", songs));
        albums.add(new Album(new Random().nextLong(), "Album 5", "Hip-Hop","2024", songs));

    }

}
