package mk.finki.ukim.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@AllArgsConstructor
@Getter
@Setter
public class Album {
    private Long id;
    private String name;
    private String genre;
    private String releaseYear;
    private List<Song> songs;


}
