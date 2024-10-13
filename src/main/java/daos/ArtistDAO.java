package daos;

import Business.Artist;

import java.util.List;

public interface ArtistDAO {

    /**
     * Retrieve an artist by id.
     * @param id the id of the artist to retrieve.
     * @return the artist with the given id, null if not found.
     */
    Artist getArtistById(int id);

    /**
     * Retrieve an only artist by name.
     * @param name the name of the artist to retrieve.
     * @return the artist with the given name, null if not found.
     */
    Artist getArtistByName(String name);

    /**
     * Retrieve all artists containing provided String.
     * @return a list of all matching artists.
     */
    List<Artist> getAllArtistsWhereNameLike(String artistName);

    /**
     * Retrieve all artists.
     * @return a list of all artists in the library.
     */
    List<Artist> getAllArtists();

}
