package daos;

import Business.Playlist;
import Business.Song;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Playlist DAO implementation class used to interact with the database
 *
 * @Author: Dmytro Drahan
 * @Author: Aloysius Wilfred Pacheco D00253302
 */

public class PlaylistDAOimpl extends Dao implements PlaylistDAO {

    public PlaylistDAOimpl(String databaseName) {
        super(databaseName);
    }

    @Override
    public boolean createPlaylist(Playlist playlist) {

            String query = "INSERT INTO Playlists (userID, name, isPublic) VALUES (?, ?, ?)";

            try (Connection con = super.getConnection();
                 PreparedStatement ps = con.prepareStatement(query)
            ) {
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
    public boolean deletePlaylistByID(int playlistId) {


        String query = "DELETE FROM Playlists WHERE playlistID = ?";

        try (Connection con = super.getConnection();
             PreparedStatement ps = con.prepareStatement(query)
        ){

            ps.setInt(1, playlistId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while adding the song.");
            e.printStackTrace();
        }

        return false;
    }

    //Done by Aloysius Wilfred Pacheco D00253302
    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) {

        String query = "INSERT INTO PlaylistSongs (playlistID, songID) VALUES (?, ?)";

        try(Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while adding the song.");
            e.printStackTrace();
            return false;
        }
    }

    //Done by Aloysius Wilfred Pacheco D00253302
    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {

        String query = "DELETE FROM PlaylistSongs WHERE playlistID = ? AND songID = ?";

        try(Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setInt(1, playlistId);
            ps.setInt(2, songId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while removing the song.");
            e.printStackTrace();
            return false;
        }
    }

    //Done by Aloysius Wilfred Pacheco D00253302
    @Override
    public boolean renamePlaylist(int playlistId, String newName) {

        String query = "UPDATE Playlists SET name = ? WHERE playlistID = ?";

        try(Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(query)
        ) {
            ps.setString(1, newName);
            ps.setInt(2, playlistId);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while renaming the song.");
            e.printStackTrace();
            return false;
        }
    }

    //Done by Aloysius Wilfred Pacheco D00253302
    //This method only Displays logged User Playlists and public playlists
    @Override
    public List<Playlist> getPlaylists(int playlistId) {

        List<Playlist> playlists = new ArrayList<>();


        String querry = "SELECT * FROM Playlists WHERE userID = ? OR isPublic = 1";

        try(Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(querry)
        ) {
            ps.setInt(1, playlistId);

            try(ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    playlists.add(Playlist.builder()
                            .playlistId(rs.getInt("playlistID"))
                            .userId(rs.getInt("userID"))
                            .name(rs.getString("name"))
                            .isPublic(rs.getBoolean("isPublic"))
                            .build());
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": SQLException occurred while running the query.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while preparing the SQL query");
            e.printStackTrace();
        }

        return playlists;
    }

    @Override
    public List<Song> getSongsInPlaylistByID(int playlistId) {

        Song s;
        List<Song> result = new ArrayList<>();

        String query = """
                    SELECT s.songID, s.title, s.albumID, s.artistID, s.length, s.ratingCount, s.averageRating, s.ratingsSum
                    FROM Songs s
                    JOIN PlaylistSongs ps ON s.songID = ps.songID
                    WHERE ps.playlistID = ?;
                    """;

        try(Connection con = super.getConnection();
            PreparedStatement ps = con.prepareStatement(query)
        ) {

            ps.setInt(1, playlistId);

            try(ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    s = Song.builder()
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
                System.out.println("Exception occurred in the getSongsInPlaylistByID() method: " + e.getMessage());
                e.printStackTrace();
            }



        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
            e.printStackTrace();
        }

        return result;
    }



}
