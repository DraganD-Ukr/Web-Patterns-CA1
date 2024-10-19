package daos;

import Business.Song;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class SongDAOImpl extends Dao implements SongDAO{

    public SongDAOImpl(String databaseName){
        super(databaseName);
    }


    @Override
    public Song findSongByTitle(String title) {
        Song song = null;
        Connection con = super.getConnection();
        String sql = "SELECT songID, title, albumID, artistID, length, ratingCount, averageRating, ratingsSum " +
                "FROM Songs WHERE title = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, title);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    song = new Song(
                            rs.getInt("songID"),
                            rs.getString("title"),
                            rs.getInt("albumID"),
                            rs.getInt("artistID"),
                            rs.getTime("length").toLocalTime(),
                            rs.getInt("ratingCount"),
                            rs.getDouble("averageRating"),
                            rs.getInt("ratingsSum")
                    );
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": SQLException occurred while processing the result.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while preparing the SQL statement.");
            e.printStackTrace();
        }
        return song;
    }

    @Override
    public List<Song> findSongsFromArtist(String artistName) {
        List<Song> songList = new ArrayList<Song>();

        Connection con = super.getConnection();
        String sql = "SELECT s.songID, s.title, s.albumID, s.artistID, s.length, s.ratingCount ,s.averageRating,s.ratingsSum " +
                "FROM Songs s " +
                "JOIN Artists a ON s.artistID = a.artistID " +
                "WHERE a.name = ?";

        try(PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1,artistName);
            try(ResultSet rs = ps.executeQuery()){
                while(rs.next()){
                    songList.add(new Song(
                            rs.getInt("songID"),
                            rs.getString("title"),
                            rs.getInt("albumID"),
                            rs.getInt("artistID"),
                            rs.getTime("length").toLocalTime(),
                            rs.getInt("ratingCount"),
                            rs.getDouble("averageRating"),
                            rs.getInt("ratingsSum")
                    ));
                }
            }catch(SQLException e){
                System.out.println(LocalDateTime.now() + ": An SQLException  occurred while running the query" +
                        " or processing the result.");
                System.out.println("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }catch(SQLException e){
            System.out.println(LocalDateTime.now() + ": An SQLException  occurred while preparing the SQL " +
                    "statement.");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }


        return songList;
    }

    @Override
    public List<Song> findSongsFromAlbumByName(String albumName) {
        List<Song> songList = new ArrayList<>();
        Connection con = super.getConnection();
        String sql = "SELECT s.songID, s.title, s.albumID, s.artistID, s.length, s.ratingCount, s.averageRating, s.ratingsSum " +
                "FROM Songs s " +
                "JOIN Albums a ON s.albumID = a.albumID " +
                "WHERE a.title = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, albumName);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songList.add(new Song(
                            rs.getInt("songID"),
                            rs.getString("title"),
                            rs.getInt("albumID"),
                            rs.getInt("artistID"),
                            rs.getTime("length").toLocalTime(),
                            rs.getInt("ratingCount"),
                            rs.getDouble("averageRating"),
                            rs.getInt("ratingsSum")
                    ));
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": SQLException occurred while processing the result.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while preparing the SQL statement.");
            e.printStackTrace();
        }
        return songList;
    }

    @Override
    public List<Song> findSongsFromAlbumById(int albumId) {
        List<Song> songList = new ArrayList<>();
        Connection con = super.getConnection();
        String sql = "SELECT songID, title, albumID, artistID, length, ratingCount, averageRating, ratingsSum " +
                "FROM Songs WHERE albumID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, albumId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    songList.add(new Song(
                            rs.getInt("songID"),
                            rs.getString("title"),
                            rs.getInt("albumID"),
                            rs.getInt("artistID"),
                            rs.getTime("length").toLocalTime(),
                            rs.getInt("ratingCount"),
                            rs.getDouble("averageRating"),
                            rs.getInt("ratingsSum")
                    ));
                }
            } catch (SQLException e) {
                System.out.println(LocalDateTime.now() + ": SQLException occurred while processing the result.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while preparing the SQL statement.");
            e.printStackTrace();
        }
        return songList;
    }

    @Override
    public boolean addSong(Song song) {
        Connection con = super.getConnection();
        String sql = "INSERT INTO Songs (title, artistID, albumID, length, ratingCount, averageRating, ratingsSum) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, song.getTitle());
            ps.setInt(2, song.getArtistID());
            ps.setInt(3, song.getAlbumID());
            ps.setTime(4, Time.valueOf(song.getLength()));
            ps.setInt(5, song.getRatingCount());
            ps.setDouble(6, song.getAverageRating());
            ps.setInt(7, song.getRatingsSum());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // returns true if the song was added successfully
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while adding the song.");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteSong(int id) {
        Connection con = super.getConnection();
        String sql = "DELETE FROM Songs WHERE songID = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Returns true if the song was deleted successfully
        } catch (SQLException e) {
            System.out.println(LocalDateTime.now() + ": SQLException occurred while deleting the song.");
            e.printStackTrace();
        }
        return false;
    }

}
