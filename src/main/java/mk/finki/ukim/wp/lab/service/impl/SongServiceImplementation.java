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
        return songRepository.findAll();
    }

    @Override
    public Artist addArtistToSong(Artist artist, Song song) {
        return songRepository.addArtistToSong(artist, song);
    }

    @Override
    public Song findByTrackId(String trackId) {
        return songRepository.findByTrackId(trackId);
    }

    @Override
    public List<Song> searchSongsByTitle(String title) {
        return songRepository.searchSongsByTitle(title);
    }

    @Override
    public void saveSong(Song song) {
        songRepository.save(song);
    }

    @Override
    public void deleteSong(String trackId) {
        songRepository.delete(trackId);
    }

    @Override
    public void updateSong(String trackId, Song song) {
        Song existingSong = songRepository.findByTrackId(trackId);
        if (existingSong != null) {
            song.setTrackId(trackId);
            songRepository.update(song);
        }
    }
}
