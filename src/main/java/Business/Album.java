package Business;

import lombok.*;

import java.time.LocalDateTime;

/**
 * @author: Dmytro Drahan
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
public class Album {

    private Integer albumId;
    private String title;
    private Integer artistId;
    private LocalDateTime releaseDate;

}
