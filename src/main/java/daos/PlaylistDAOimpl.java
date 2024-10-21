package daos;

import Business.Playlist;
import Business.Song;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class PlaylistDAOimpl extends Dao implements PlaylistDAO {

    public PlaylistDAOimpl(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {

            Connection con = super.getConnection();

            String query = "INSERT INTO Playlists (userID, name, isPublic) VALUES (?, ?, ?)";

            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, playlist.getUserId());
                ps.setString(2, playlist.getName());
                ps.setBoolean(3, playlist.isPublic());

                int rowsAffected = ps.executeUpdate();
                return rowsAffected > 0;

            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": SQLException occurred while adding the song.");
                e.printStackTrace();
            }

        return false;
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
