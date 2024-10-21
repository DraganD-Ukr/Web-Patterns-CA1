package daos;

import Business.Album;
import Business.Playlist;
import Business.Song;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public List<Song> getSongsInPlaylistByID(int playlistId) {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Song song;
        List<Song> result = new ArrayList<>();


        try {
            con = super.getConnection();

            String query = """
                    SELECT s.songID, s.title, s.albumID, s.artistID, s.length, s.ratingCount, s.averageRating, s.ratingsSum
                    FROM Songs s
                    JOIN PlaylistSongs ps ON s.songID = ps.songID
                    WHERE ps.playlistID = ?;
                    """;
            ps = con.prepareStatement(query);
            ps.setInt(1, playlistId);
            rs = ps.executeQuery();

            while (rs.next()) {

                Song s = Song.builder()
                        .songID(rs.getInt("songID"))
                        .title(rs.getString("title"))
                        .albumID(rs.getInt("albumID"))
                        .artistID(rs.getInt("artistID"))
                        .length(rs.getTime("length").toLocalTime())
                        .ratingCount(rs.getInt("ratingCount"))
                        .averageRating(rs.getDouble("averageRating"))
                        .ratingsSum(rs.getInt("ratingsSum"))
                        .build();

                result.add(s);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }

        return result;
    }



}
