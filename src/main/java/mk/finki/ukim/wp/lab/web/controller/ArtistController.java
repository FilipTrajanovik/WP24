package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Artist;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.ArtistService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/artist")
public class ArtistController {
    private final ArtistService artistService;
    private final SongService songService;

    public ArtistController(ArtistService artistService, SongService songService) {
        this.artistService = artistService;
        this.songService = songService;
    }


    @GetMapping
    public String listArtists(@RequestParam String trackId, Model model) {
        Song song = songService.findByTrackId(trackId);
        if (song == null) {
            return "redirect:/songs?error=Song not found";
        }

        model.addAttribute("artists", artistService.listArtists());
        model.addAttribute("trackId", trackId);
        return "artistsList"; // Thymeleaf template for listing artists
    }

    // POST method to add an artist to the song
    @PostMapping
    public String addArtistToSong(@RequestParam Long artistId, @RequestParam String trackId) {
        Artist artist = artistService.ArtistfindById(artistId);
        Song song = songService.findByTrackId(trackId);

        if (artist == null || song == null) {
            return "redirect:/songs?error=Artist or Song not found";
        }

        // Add the artist to the song's performers
        songService.addArtistToSong(artist, song);

        // Redirect to the song details page (or wherever you want)
        return "redirect:/songDetails?trackId=" + trackId;
    }
}
