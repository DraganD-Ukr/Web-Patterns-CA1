package Business;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Album {

    private Integer albumId;
    private String title;
    private Integer artistId;
    private LocalDateTime releaseDate;

}
