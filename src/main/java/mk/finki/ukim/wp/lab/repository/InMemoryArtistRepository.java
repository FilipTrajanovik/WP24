package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Artist;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class InMemoryArtistRepository {

    public List<Artist> findAll(){
        return DataHolder.artists;
    }
    public Artist findById(long id){
        return DataHolder.artists.stream().filter(artist -> artist.getId() == id).findFirst().orElse(null);
    }
}
