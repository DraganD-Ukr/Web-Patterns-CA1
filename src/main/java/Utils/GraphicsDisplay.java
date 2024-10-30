package Utils;

import Business.*;
import daos.AlbumDaoImpl;
import daos.ArtistDaoImpl;
import daos.SongDAOImpl;
import daos.UserDAOimpl;

import java.time.LocalTime;
import java.util.List;

/**
 * The GraphicsDisplay class is used to display the data in a neat table format (╯°□°)╯︵ ┻━┻
 * @author  Aloysius Wilfred Pacheco D00253302
 */

public class GraphicsDisplay {
    /**
     * Display the artist details in a table format with the artist id and artist name
     * @param artist The list of artists to display
     */
    public static void DisplayArtists(List<Artist> artist){
        System.out.println(
                "+----------+------------------------------+\n"+
                "| Artist ID| Artist Name                  |\n"+
                "+----------+------------------------------+"
        );
        for (Artist a : artist) {
            //truncate the artist name to 30 characters if exceeds 30 charactersq
            String artistName = Truncate(a.getName(), 30);
            System.out.println(
                    //format the output to be displayed in a table format
                    String.format("| %-8d | %-28s |", a.getArtistId(), artistName)
            );
        }
        System.out.println("+----------+------------------------------+");

    }

    /**
     * Display the albums details in a table format with the album id, album title, artist name and release date
     * @param album The list of albums to display
     * @param artistDao The artistDao object to get the artist name from the artist id
     */
    public static void DisplayAlbums(List<Album> album, ArtistDaoImpl artistDao){
        System.out.println(
                "+----------+------------------------------+------------------------------+------------------------------+\n"+
                "| Album ID | Album Title                  | Artist Name                  | Release Date                 |\n"+
                "+----------+------------------------------+------------------------------+------------------------------+"
        );
        for (Album a : album) {
            //truncate the album title to 30 characters if exceeds 30 characters
            String albumTitle = Truncate(a.getTitle(), 30);


            Artist at = artistDao.getArtistById(a.getArtistId());
            String artistName = Truncate(at.getName(), 30);


            System.out.println(
                    //format the output to be displayed in a table format
                    String.format("| %-8d | %-28s | %-28s | %-28s |", a.getAlbumId(), albumTitle, artistName, a.getReleaseDate())
            );
        }
        System.out.println("+----------+------------------------------+------------------------------+------------------------------+");

    }

/**
 * Display the songs details in a table format with the song id, song title, album name, artist name, length, rating count and average rating
 * @param songs The list of songs to display in the table
 */

public static void DisplaySongs(List<Song> songs, ArtistDaoImpl artistDao, AlbumDaoImpl albumDao) {
    System.out.println(
            "+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+\n" +
            "| Song ID | Song Title                   | Length   | Avg Rating     | Rating Count | Artist Name                  | Album Name                   |\n" +
            "+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+"
    );
    for (Song s : songs) {
        String songTitle = Truncate(s.getTitle(), 30);
        String artistName = Truncate(artistDao.getArtistById(s.getArtistID()).getName(), 30);
        String albumName = Truncate(albumDao.getAlbumById(s.getAlbumID()).getTitle(), 30);
        String length = s.getLength().toString();
        String avgRating = String.format("%.2f", s.getAverageRating());
        int ratingCount = s.getRatingCount();

        System.out.println(
                String.format("| %-7d | %-28s | %-8s | %-14s | %-12d | %-28s | %-28s |",
                        s.getSongID(), songTitle, length, avgRating, ratingCount, artistName, albumName)
        );
    }
    System.out.println("+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+");
}
public static void DisplaySongs(Song song, ArtistDaoImpl artistDao, AlbumDaoImpl albumDao) {
    System.out.println(
            "+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+\n" +
            "| Song ID | Song Title                   | Length   | Avg Rating     | Rating Count | Artist Name                  | Album Name                   |\n" +
            "+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+"
    );
    String songTitle = Truncate(song.getTitle(), 30);
    String artistName = Truncate(artistDao.getArtistById(song.getArtistID()).getName(), 30);
    String albumName = Truncate(albumDao.getAlbumById(song.getAlbumID()).getTitle(), 30);
    String length = song.getLength().toString();
    String avgRating = String.format("%.2f", song.getAverageRating());
    int ratingCount = song.getRatingCount();

    System.out.println(
            String.format("| %-7d | %-28s | %-8s | %-14s | %-12d | %-28s | %-28s |",
                    song.getSongID(), songTitle, length, avgRating, ratingCount, artistName, albumName)
    );
    System.out.println("+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+");
}

    /**
     * Display the song details in a table format with the song id, song title, album name, artist name, length, rating count and average rating
     * @param song The song to display in the table
     */
    public static void DisplaySong(Song song,ArtistDaoImpl artistDao, AlbumDaoImpl albumDao) {
        System.out.println(
                "+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+\n" +
                "| Song ID | Song Title                   | Length   | Avg Rating     | Rating Count | Artist Name                  | Album Name                   |\n" +
                "+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+"
        );
        String songTitle = Truncate(song.getTitle(), 30);
        String artistName = Truncate(artistDao.getArtistById(song.getArtistID()).getName(), 30);
        String albumName = Truncate(albumDao.getAlbumById(song.getAlbumID()).getTitle(), 30);
        String length = song.getLength().toString();
        String avgRating = String.format("%.2f", song.getAverageRating());
        int ratingCount = song.getRatingCount();

        System.out.println(
                String.format("| %-7d | %-28s | %-8s | %-14s | %-12d | %-28s | %-28s |",
                        song.getSongID(), songTitle, length, avgRating, ratingCount, artistName, albumName)
        );
        System.out.println("+---------+------------------------------+----------+----------------+--------------+------------------------------+------------------------------+");
    }

    /**
     * Display the user details in a table format with the user id, username, email, and role
     * @param playlists The list of users to display
     *
     * @param userDao   The userDAO object to get the user details
     */
    public static void DisplayPlaylists(List<Playlist> playlists, UserDAOimpl userDao){
        System.out.println(
                "+--------------+------------------------------+------------------+\n"+
                "| Playlist ID  | Playlist Name                | By User          |\n"+
                "+--------------+------------------------------+------------------+"
        );
        for (Playlist p : playlists) {
            //truncate the playlist name to 30 characters if exceeds 30 characters
            String playlistName = Truncate(p.getName(), 30);
            String userName = Truncate(userDao.getUserById(p.getUserId()).getUserName(), 30);
            System.out.println(
                    //format the output to be displayed in a table format
                    String.format("| %-12d | %-28s | %-16s |", p.getPlaylistId(), playlistName, userName)
            );
        }
        System.out.println("+--------------+------------------------------+------------------+");
    }

    /**
     * Display the ratings details in a table format with the rating id, song name, rating value and average rating,from the song id
     * @param ratings The list of ratings to display
     * @param songDao The songDao object to get the song name from the song id
     */
    public static void DisplayRatings(List<Rating> ratings, SongDAOImpl songDao) {
        System.out.println(
                "+--------------+------------------------------+------------------+------------------+\n" +
                "| Rating ID    | Song Name                    | Rating Value     | Avg Rating       |\n" +
                "+--------------+------------------------------+------------------+------------------+"
        );
        for (Rating r : ratings) {
            //truncate the song name to 30 characters if exceeds 30 characters
            String songName = Truncate(songDao.findSongById(r.getSongID()).getTitle(), 30);
            System.out.println(
                    //format the output to be displayed in a table format
                    String.format("| %-12d | %-28s | %-16d | %-16.2f |", r.getRatingID(), songName, r.getRatingValue(), songDao.findSongById(r.getSongID()).getAverageRating())
            );
        }
        System.out.println("+--------------+------------------------------+------------------+------------------+");
    }


    /**
     * Truncate the string to the specified length if the string length exceeds the specified length
     * @param str The string to truncate
     * @param length The length to truncate the string to
     * @return The truncated string
     */
    public static String Truncate(String str, int length){
        String result;
        if (str.length() > length){
            result = str.substring(0, length);
        } else {
            result = str;
        }
        return result;
    }
}
