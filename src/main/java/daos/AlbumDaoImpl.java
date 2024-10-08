package daos;

import Business.Album;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlbumDaoImpl extends Dao implements AlbumDAO{

        public AlbumDaoImpl(String dbName) {
            super(dbName);
        }



    @Override
    public List<Album> getAllAlbumsByArtistId(int artistId) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Album album;
        List<Album> result = new ArrayList<>();


        try {
            con = getConnection();

            String query = "SELECT * FROM Albums WHERE artistID = ? ORDER BY albumID";
            ps = con.prepareStatement(query);
            ps.setInt(1, artistId);
            rs = ps.executeQuery();

            while (rs.next()) {
//                Using builder for easier object creation
                album = Album.builder()
                        // Get the properties of an artist from the resultset
                        .albumId(rs.getInt("artistId"))
                        .title(rs.getString("title"))
                        .artistId(rs.getInt("artistId"))
                        .releaseDate(rs.getDate("releaseDate"))
                        .build();
//                Add the artist to the result list
                result.add(album);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }

        return result;
    }

    @Override
    public List<Album> getAllAlbumsWhereArtistNameLike(String artistName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Album album;
        List<Album> result = new ArrayList<>();


        try {
            con = getConnection();

            String query = """
                    SELECT Albums.*\s
                    FROM Albums
                    JOIN Artists ON Albums.artistID = Artists.artistID
                    WHERE Artists.name LIKE ?""";
            ps = con.prepareStatement(query);
            ps.setString(1, "%" + artistName + "%");
            rs = ps.executeQuery();

            while (rs.next()) {
//                Using builder for easier object creation
                album = Album.builder()
                        // Get the properties of an artist from the resultset
                        .albumId(rs.getInt("artistId"))
                        .title(rs.getString("title"))
                        .artistId(rs.getInt("artistId"))
                        .releaseDate(rs.getDate("releaseDate"))
                        .build();
//                Add the artist to the result list
                result.add(album);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }

        return result;
    }

    @Override
    public List<Album> getAllAlbumsByArtistName(String artistName) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Album album;
        List<Album> result = new ArrayList<>();


        try {
            con = getConnection();

            String query = """
                    SELECT Albums.*\s
                    FROM Albums
                    JOIN Artists ON Albums.artistID = Artists.artistID
                    WHERE Artists.name = ?""";
            ps = con.prepareStatement(query);
            ps.setString(1, artistName);
            rs = ps.executeQuery();

            while (rs.next()) {
//                Using builder for easier object creation
                album = Album.builder()
                        // Get the properties of an artist from the resultset
                        .albumId(rs.getInt("artistId"))
                        .title(rs.getString("title"))
                        .artistId(rs.getInt("artistId"))
                        .releaseDate(rs.getDate("releaseDate"))
                        .build();
//                Add the artist to the result list
                result.add(album);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }

        return result;
    }

}
