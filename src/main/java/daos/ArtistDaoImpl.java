package daos;

import Business.Artist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Dmytro Drahan
 */
public class ArtistDaoImpl extends Dao implements ArtistDAO {



    /**
     * Get the connection from the superclass by passing the database name
     * @param dbName - the name of the database
     */
    public ArtistDaoImpl(String dbName) {
        super(dbName);
    }


    @Override
    public Artist getArtistById(int id) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Artist artist = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM Artists WHERE artistID = ?";
            ps = con.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
//                Using builder for easier object creation
                artist = Artist.builder()
                        // Get the properties of an artist from the resultset
                        .artistId(rs.getInt("artistId"))
                        .name(rs.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }
        return artist;

    }

    @Override
    public Artist getArtistByName(String name) {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Artist artist = null;

        try {
            con = getConnection();

            String query = "SELECT * FROM Artists WHERE name = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, name);
            rs = ps.executeQuery();

            if (rs.next()) {
//                Using builder for easier object creation
                artist = Artist.builder()
                        // Get the properties of an artist from the resultset
                        .artistId(rs.getInt("artistId"))
                        .name(rs.getString("name"))
                        .build();
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }
        return artist;

    }

    @Override
    public List<Artist> getAllArtistsWhereNameLike(String artistName) {


        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Artist artist;
        List<Artist> result = new ArrayList<>();


        try {
            con = getConnection();

            String query = "SELECT * FROM Artists WHERE name LIKE ? ORDER BY artistID";
            ps = con.prepareStatement(query);
            ps.setString(1, "%"+artistName+"%");
            rs = ps.executeQuery();

            while (rs.next()) {
//                Using builder for easier object creation
                artist = Artist.builder()
                        // Get the properties of an artist from the resultset
                        .artistId(rs.getInt("artistId"))
                        .name(rs.getString("name"))
                        .build();
//                Add the artist to the result list
                result.add(artist);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }

        return result;

    }


    @Override
    public List<Artist> getAllArtists() {

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Artist artist;
        List<Artist> result = new ArrayList<>();


        try {
            con = getConnection();

            String query = "SELECT * FROM Artists ORDER BY artistID";
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
//                Using builder for easier object creation
                artist = Artist.builder()
                        // Get the properties of an artist from the resultset
                        .artistId(rs.getInt("artistId"))
                        .name(rs.getString("name"))
                        .build();
//                Add the artist to the result list
                result.add(artist);
            }
        } catch (SQLException e) {
            System.out.println("Exception occurred in the getArtistById() method: " + e.getMessage());
        } finally {
            closeResources(rs, ps, con);
        }

        return result;
    }
}
