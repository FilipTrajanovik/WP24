package mk.finki.ukim.wp.lab.service.impl;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.repository.AlbumRepository;
import mk.finki.ukim.wp.lab.repository.InMemoryAlbumRepository;
import mk.finki.ukim.wp.lab.service.AlbumService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class AlbumServiceImplementation implements AlbumService {
    private final AlbumRepository repository;

    public AlbumServiceImplementation(AlbumRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Album> findAll() {
        return repository.findAll();
    }

    @Override
    public Album findById(Long albumId) {
        return repository.findById(albumId).orElse(null);
    }
}
