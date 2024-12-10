package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class InMemorySongRepository {
    public List<Song> findAll(){
        return DataHolder.songs;
    }
    public  Song findByTrackId(String trackId){
        return DataHolder.songs.stream().filter(song -> song.getTrackId().equals(trackId)).findFirst().orElse(null);
    }
    public Artist addArtistToSong(Artist artist, Song song){
        song.getPerformers().add(artist);
        return artist;
    }
    public List<Song> searchSongsByTitle(String query) {
        return DataHolder.songs.stream().filter(song -> song.getTitle().toLowerCase().contains(query.toLowerCase())).collect(Collectors.toList());
    }
    public void save(Song song) {
        DataHolder.songs.add(song);
    }
    public void update(Song updatedSong) {
        DataHolder.songs = DataHolder.songs.stream()
                .map(song -> song.getTrackId().equals(updatedSong.getTrackId()) ? updatedSong : song)
                .collect(Collectors.toList());
    }
    public void delete(String trackId) {
        DataHolder.songs.removeIf(song -> song.getTrackId().equals(trackId));
    }



}
