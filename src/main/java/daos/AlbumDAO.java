package daos;

import Business.Album;

import java.util.List;

public interface AlbumDAO {

    /**
     * Retrieve an album by artist's id.
     * @param artistId - the id of the artist to retrieve his albums.
     * @return - a list of all artist's albums.
     */
    List<Album> getAllAlbumsByArtistId(int artistId);

    /**
     * Retrieve all albums by artist's name.
     * @param artistName - the name of the artist to retrieve his albums.
     * @return - a list of all artist's albums.
     */
    List<Album> getAllAlbumsWhereArtistNameLike(String artistName);

    /**
     * Retrieve all albums by artist's name.
     * @param artistName - the name of the artist to retrieve his albums.
     * @return - a list of all artist's albums.
     */
    List<Album> getAllAlbumsByArtistName(String artistName);

    /**
     * Retrieve album by id.
     * @param albumId - the id of the album to retrieve.
     * @return - the album with the given id.
     */
    Album getAlbumById(int albumId);

}
