package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SongRepository extends JpaRepository<Song, Long> {
    Song findByTrackId(String trackId);

    List<Song> findByTitleContainingIgnoreCase(String title);

    List<Song> findAllByAlbum_Id(Long albumId);
}
