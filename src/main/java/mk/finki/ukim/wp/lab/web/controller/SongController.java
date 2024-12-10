package mk.finki.ukim.wp.lab.web.controller;

import mk.finki.ukim.wp.lab.model.Album;
import mk.finki.ukim.wp.lab.model.Song;
import mk.finki.ukim.wp.lab.service.AlbumService;
import mk.finki.ukim.wp.lab.service.SongService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/songs")
public class SongController {
    private final SongService songService;
    private final AlbumService albumService;

    public SongController(SongService songService, AlbumService albumService) {
        this.songService = songService;
        this.albumService = albumService;
    }

    @GetMapping
    public String getSongsPage(@RequestParam(required = false) String error, Model model) {
        // Fetch songs directly from the database via SongService
        List<Song> songs = songService.listSongs(); // Ensure listSongs uses the database
        model.addAttribute("songs", songs);
        model.addAttribute("error", error);
        return "listSongs"; // Render the song listing page
    }

    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        // Populate model with empty song and list of albums
        model.addAttribute("song", null); // No data for a new song
        model.addAttribute("albums", albumService.findAll()); // Fetch albums from the database
        return "add-song"; // Render the add-song.html page
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        // Fetch song by ID for editing
        Song song = songService.listSongs().stream()
                .filter(s -> s.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (song == null) {
            // Redirect to the song list with an error if the song does not exist
            return "redirect:/songs?error=Song not found";
        }

        // Populate model attributes for the edit form
        model.addAttribute("song", song); // Current song data for editing
        model.addAttribute("albums", albumService.findAll()); // Fetch albums from the database
        return "add-song"; // Render the add-song.html page
    }


    @PostMapping("/save")
    public String saveSong(
            @RequestParam(required = false) Long id,
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId) {
        Album album = albumService.findById(albumId);
        if (album == null) {
            return "redirect:/songs?error=Album not found";
        }

        if (id != null) {
            // Updating an existing song
            Song existingSong = songService.findByTrackId(trackId);
            if (existingSong != null) {
                existingSong.setTitle(title);
                existingSong.setGenre(genre);
                existingSong.setReleaseYear(releaseYear);
                existingSong.setAlbum(album);
                songService.saveSong(existingSong);
            }
        } else {
            // Creating a new song
            Song newSong = new Song(trackId, title, genre, releaseYear, null, null, album);
            songService.saveSong(newSong);
        }

        return "redirect:/songs";
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        // Delete the song by its ID
        songService.deleteSong(id.toString());
        return "redirect:/songs"; // Redirect to the song list after deletion
    }
}
