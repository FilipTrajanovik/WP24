package mk.finki.ukim.wp.lab.repository;

import mk.finki.ukim.wp.lab.bootstrap.DataHolder;
import mk.finki.ukim.wp.lab.model.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AlbumRepository {
    public List<Album> findAll(){
        return DataHolder.albums;
    }
    public Album findById(long id){
        return DataHolder.albums.stream().filter(album -> album.getId() == id).findFirst().orElse(null);
    }
}
