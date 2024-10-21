package daos;

import Business.Playlist;
import Business.Song;

import java.util.List;

public class PlaylistDAOimpl extends Dao implements PlaylistDAO {

    public PlaylistDAOimpl(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean deletePlaylist(int playlistId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean renamePlaylist(int playlistId, String newName) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Playlist> getPlaylists(int playlistId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Song> getSongsInPlaylist(int playlistId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }



}
