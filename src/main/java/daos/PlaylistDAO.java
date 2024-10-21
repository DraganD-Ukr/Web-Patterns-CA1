package daos;

import Business.Playlist;
import Business.Song;

import java.util.List;

public interface PlaylistDAO {

    boolean createPlaylist(Playlist playlist);

    boolean deletePlaylist(int playlistId);

    boolean addSongToPlaylist(int playlistId, int songId);

    boolean removeSongFromPlaylist(int playlistId, int songId);

    boolean renamePlaylist(int playlistId, String newName);

    List<Playlist> getPlaylists(int playlistId);

    List<Song> getSongsInPlaylist(int playlistId);

}
