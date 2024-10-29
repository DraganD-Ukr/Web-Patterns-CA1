package daos;

import Business.Playlist;
import Business.Song;

import java.util.List;

public interface PlaylistDAO {

    boolean createPlaylist(Playlist playlist);

    boolean deletePlaylistByID(int playlistId);

    int getNextPlaylistID();

    boolean resetPlatylistAutoIncrementID(int setID);

    boolean addSongToPlaylist(int playlistId, int songId);

    boolean removeSongFromPlaylist(int playlistId, int songId);

    boolean renamePlaylist(int playlistId, String newName);

    List<Playlist> getPlaylists(int UserID);

    List<Song> getSongsInPlaylistByID(int playlistId);

}
