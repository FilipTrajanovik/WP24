package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.repository.SongRepository;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImplementation implements SongService {

    private final SongRepository songRepository;

    public SongServiceImplementation(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    @Override
    public List<Song> listSongs() {
        // Fetch all songs from the database
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        // Add an artist to the song and save the changes
        song.getPerformers().add(artist);
        songRepository.save(song);
        return artist;
    }

    @Override
    public Song findByTrackId(String trackId) {
        // Fetch a song by its track ID
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public List<Song> searchSongsByTitle(String title) {
        // Search for songs by title using JPA's query derivation
        return songRepository.findByTitleContainingIgnoreCase(title);
    }

    @Override
    public void saveSong(Song song) {
        // Save the song to the database
        songRepository.save(song);
    }

    @Override
    public void deleteSong(String trackId) {
        // Find the song by track ID and delete it if it exists
        Song song = songRepository.findByTrackId(trackId);
        if (song != null) {
            songRepository.delete(song);
        }
    }

    @Override
    public void updateSong(String trackId, Song song) {
        // Update the song if it exists
        Song existingSong = songRepository.findByTrackId(trackId);
        if (existingSong != null) {
            song.setId(existingSong.getId()); // Preserve the existing ID
            songRepository.save(song);
        }
    }
}
