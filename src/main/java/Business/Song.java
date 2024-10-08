package Business;


import lombok.*;

import java.time.LocalTime;




/**
 * Song DTO used to hold song data from database
 *
 * @author Aloysius Wilfred Pacheco D00253302
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@EqualsAndHashCode

public class Song {
    @NonNull
    private int songID;
    @NonNull
    private String title;
    @NonNull
    private int albumID;
    @NonNull
    private int artistID;
    private LocalTime length;
    private int ratingCount;
    private double averageRating;
    private int ratingsSum;

}
