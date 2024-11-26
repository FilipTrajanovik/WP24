package mk.finki.ukim.wp.lab.service;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;

import java.util.List;

public interface SongService {
    List<Song> listSongs();

    Artist addArtistToSong(Artist artist, Song song);

    Song findByTrackId(String trackId);

    List<Song> searchSongsByTitle(String title);

    void saveSong(Song song);
    void deleteSong(String trackId);
    void updateSong(String trackId,Song song);
}
