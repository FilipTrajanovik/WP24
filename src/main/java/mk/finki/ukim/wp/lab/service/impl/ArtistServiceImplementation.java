package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.repository.InMemoryArtistRepository;
import mk.finki.ukim.wp.lab.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ArtistServiceImplementation implements ArtistService {
    private final InMemoryArtistRepository inMemoryArtistRepository;

    public ArtistServiceImplementation(InMemoryArtistRepository inMemoryArtistRepository) {
        this.inMemoryArtistRepository = inMemoryArtistRepository;
    }

    @Override
    public List<Artist> listArtists() {
        return inMemoryArtistRepository.findAll();
    }

    @Override
    public Artist ArtistfindById(Long id) {
        return inMemoryArtistRepository.findById(id);
    }
}
