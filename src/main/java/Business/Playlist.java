package Business;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode
public class Playlist {

    @EqualsAndHashCode.Exclude
    private int playlistId;
    @NonNull
    private int userId;
    @NonNull
    private String name;
    @NonNull
    private boolean isPublic;

    /**
     * Constructor for creating a playlist object
     * @param userId The id of the user who created the playlist
     * @param name The name of the playlist
     * @param isPublic Whether the playlist is public or not
     */
    public Playlist(int userId, String name, boolean isPublic) {
        this.userId = userId;
        this.name = name;
        this.isPublic = isPublic;
    }

}
