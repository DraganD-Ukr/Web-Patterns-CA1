package daos;

import Business.Song;
import java.util.List;
import java.time.LocalTime;

public interface SongDAO {
    public Song findSongByTitle(String title);
    public List<Song> findSongsFromArtist(String artist);
    public List<Song> findSongsFromAlbumByName(String albumName);
    public List<Song> findSongsFromAlbumById(int albumId);
    public boolean addSong(String title, int artistId, int albumId, LocalTime Length, int ratingCount, double ratingAverage, int ratingSum);
    public boolean deleteSong(int id);

}
