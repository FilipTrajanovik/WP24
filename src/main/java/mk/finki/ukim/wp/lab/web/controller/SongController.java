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
        // Fetch songs using the injected songService
        List<Song> songs = songService.listSongs();
        model.addAttribute("songs", songs);
        model.addAttribute("error", error);
        return "listSongs";
    }

    @PostMapping("/save")
    public String saveSong(
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId) {
        Album album = albumService.findById(albumId);
        if (album == null) {
            // Redirect with an error if the selected album does not exist
            return "redirect:/songs?error=Album not found";
        }

        // Check if a song with the provided trackId already exists
        Song existingSong = songService.findByTrackId(trackId);
        if (existingSong != null) {
            // Update the existing song
            existingSong.setTitle(title);
            existingSong.setGenre(genre);
            existingSong.setReleaseYear(releaseYear);
            existingSong.setAlbum(album);
            songService.updateSong(trackId, existingSong);
        } else {
            // Create a new song
            Song newSong = new Song(trackId, title, genre, releaseYear, null, null, album);
            songService.saveSong(newSong);
        }

        // Redirect to the list of songs
        return "redirect:/songs";
    }




    @PostMapping("/edit/{songId}")
    public String editSong(
            @PathVariable Long songId,
            @RequestParam String title,
            @RequestParam String trackId,
            @RequestParam String genre,
            @RequestParam int releaseYear,
            @RequestParam Long albumId) {
        Album album = albumService.findById(albumId);
        if (album == null) {
            return "redirect:/songs?error=Album not found";
        }
        Song updatedSong = new Song(trackId, title, genre, releaseYear, null, null, album);
        songService.updateSong(songId.toString(), updatedSong);
        return "redirect:/songs"; // Redirect to the song list
    }


    @GetMapping("/delete/{id}")
    public String deleteSong(@PathVariable Long id) {
        songService.deleteSong(id.toString());
        return "redirect:/songs";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditSongForm(@PathVariable Long id, Model model) {
        // Find the song by ID
        Song song = songService.findByTrackId(id.toString());
        if (song == null) {
            // Redirect to the song list with an error if the song does not exist
            return "redirect:/songs?error=Song not found";
        }
        // Populate model attributes for the form
        model.addAttribute("song", song); // Current song data
        model.addAttribute("albums", albumService.findAll()); // List of albums for <select>
        return "add-song"; // Render the add-song.html page
    }
    @GetMapping("/add-form")
    public String getAddSongPage(Model model) {
        // Populate the model with albums for the dropdown
        model.addAttribute("albums", albumService.findAll());
        model.addAttribute("song", null); // No song data for a new song
        return "add-song"; // Render the add-song.html page
    }




}
