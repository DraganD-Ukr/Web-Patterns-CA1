package Business;

import lombok.*;

import java.util.Date;

/**
 * @author: Dmytro Drahan
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Album {

    @EqualsAndHashCode.Exclude
    private Integer albumId;
    private String title;
    private Integer artistId;
    private Date releaseDate;

    @Override
    public String toString() {
        return
                "Id=" + albumId +
                ", title='" + title + '\'' +
                ", artistId=" + artistId +
                ", Release date=" + releaseDate;
    }
}
