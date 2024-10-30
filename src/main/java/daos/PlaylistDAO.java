package daos;

import Business.Playlist;
import Business.Song;

import java.util.List;

/**
 * Playlist DAO interface used to define the methods that will be implemented in the PlaylistDAOImpl class
 * @author Dmytro Drahan
 **/

public interface PlaylistDAO {
    
    /**
     * Creates a playlist in the database (Does not include Id as it is auto-generated)
     * @param playlist The playlist to be created
     * @return The id of the playlist created
     */
    int createPlaylist(Playlist playlist);
    
    /**
     * Deletes a playlist from the database by its id
     * @param playlistId The id of the playlist to be deleted
     * @return {@code true} if the playlist was deleted successfully, if not {@code false} 
     */
    boolean deletePlaylistByID(int playlistId);
    
    /**
     * Adds a song to a playlist
     * @param playlistId The id of the playlist to add the song to
     * @param songId The id of the song to be added to the playlist
     * @return {@code true} if the song was added successfully, if not {@code false}
     */
    boolean addSongToPlaylist(int playlistId, int songId);
    
    /**
     * Removes a song from a playlist
     * @param playlistId The id of the playlist to remove the song from
     * @param songId The id of the song to be removed from the playlist
     * @return {@code true} if the song was removed successfully, if not {@code false}
     */
    boolean removeSongFromPlaylist(int playlistId, int songId);
    
    /**
     * Renames a playlist
     * @param playlistId The id of the playlist to be renamed
     * @param newName The new name of the playlist
     * @return {@code true} if the playlist was renamed successfully, if not {@code false}
     */
    boolean renamePlaylist(int playlistId, String newName);
    
    /**
     * Gets all playlists from the database by the user id,
     * also returns all platylists that are public as well
     * 
     * @param UserID The id of the user to get the playlists from
     * @return A list of {@link Playlist} objects from the user
     */
    List<Playlist> getPlaylists(int UserID);
    
    /**
     * Gets a playlist by its id
     * @param playlistId The id of the playlist to get
     * @return The {@link Playlist} object if found, if not {@code null}
     */
    Playlist getPlaylistByID(int playlistId);
    
    /**
     * Gets a playlist by its name
     * @param playlistName The name of the playlist to get
     * @return The {@link Playlist} object if found, if not {@code null}
     */
    Playlist getPlaylistByName(String playlistName);

}
