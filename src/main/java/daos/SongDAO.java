package daos;

import Business.Song;
import java.util.List;
import java.time.LocalTime;

/**
 * Song DAO interface used to define the methods that will be implemented in the SongDAOImpl class
 *
 * @author Aloysius Wilfred Pacheco D00253302
 */
public interface SongDAO {
    /**
     * Gets A song by its title from the database
     * @param title The title of the song to find
     * @return The {@link Song} object if found, otherwise {@code null}
     */
    public Song findSongByTitle(String title);
    /**
     * Gets all songs from a specific artist using name
     * @param artist The name of the artist to find songs from
     * @return A list of {@link Song} objects from the artist
     */
    public List<Song> findSongsFromArtist(String artist);
    /**
     * Gets all songs from a specific album using name
     * @param albumName The name of the album to find songs from
     * @return A list of {@link Song} objects from the album
     */
    public List<Song> findSongsFromAlbumByName(String albumName);
    /**
     * Gets all songs from a specific album using id
     * @param albumId The id of the album to find songs from
     * @return A list of {@link Song} objects from the album
     */
    public List<Song> findSongsFromAlbumById(int albumId);
    /**
     * Adds a song to the database
     * @param song The song to add to the database
     * @return {@code true} if the song was added successfully, otherwise {@code false}
     */
    public boolean addSong(Song song);

    /**
     * removes a song from the database
     * @param id The id of the song to remove
     * @return {@code true} if the song was removed successfully, otherwise {@code false}
     */
    public boolean deleteSong(int id);

    /**
     * Gets all songs in a playlist by the playlist name
     * @param name The name of the playlist to get songs from
     * @return A list of {@link Song} objects from the playlist
     */
    List<Song> getSongsInPlaylistByPlaylistName(String name);

    /**
     * Gets top-rated song
     * @return  {@link Song} object with the biggest average rating
     */
    Song getTopRatedSong();

    /**
     * Gets the most popular song(appears most times in playlists)
     * @return {@link Song} object with the most appearances in playlists
     */
    Song getMostPopularSong();

}
