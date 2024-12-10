package mk.finki.ukim.wp.lab.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Song {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String trackId;
    private String title;
    private String genre;
    private int releaseYear;
    @ManyToMany
    @JoinTable(
            name = "song_artist",
            joinColumns = @JoinColumn(name = "song_id"),
            inverseJoinColumns = @JoinColumn(name = "artist_id")
    )
    private List<Artist> performers;

    private Long id;
    @ManyToOne
    @JoinColumn(name = "album_id")
    private Album album;
}
