package Utils;

import Business.Album;
import Business.Artist;
import Business.Song;
import daos.AlbumDaoImpl;
import daos.ArtistDaoImpl;
import lombok.NonNull;

import java.time.LocalTime;
import java.util.List;

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
                    String.format("| %-8d | %-30s |", a.getArtistId(), artistName)
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
                    String.format("| %-8d | %-30s | %-30s | %-30s |", a.getAlbumId(), albumTitle, artistName, a.getReleaseDate())
            );
        }
        System.out.println("+----------+------------------------------+------------------------------+------------------------------+");

    }
//    private int songID;
//    @NonNull
//    private String title;
//    @NonNull
//    private int albumID; we use this and get album name from album table
//    @NonNull
//    private int artistID; same with artist name
//    @NonNull
//    private LocalTime length;
//    private int ratingCount;
//    private double averageRating
//    it should show the table columns like this song id 8,song title 30, length 10, rating average 4, ratting count 10, artist name 30, album name 30
/**
 * Display the songs details in a table format with the song id, song title, album name, artist name, length, rating count and average rating
 * @param songs The list of songs to display
 */

public static void DisplaySongsInAlbum(List<Song> songs, ArtistDaoImpl artistDao, AlbumDaoImpl albumDao) {
    System.out.println(
            "+---------+------------------------------+----------+----------------+------------+------------------------------+------------------------------+\n" +
            "| Song ID | Song Title                   | Length   | Avg Rating     | Rating Count | Artist Name                  | Album Name                   |\n" +
            "+---------+------------------------------+----------+----------------+------------+------------------------------+------------------------------+"
    );
    for (Song s : songs) {
        String songTitle = Truncate(s.getTitle(), 30);
        String artistName = Truncate(artistDao.getArtistById(s.getArtistID()).getName(), 30);
        String albumName = "";//Truncate(albumDao.getAlbumByAlbumId(s.getAlbumID()).getTitle(), 30);
        String length = s.getLength().toString();
        String avgRating = String.format("%.2f", s.getAverageRating());
        int ratingCount = s.getRatingCount();

        System.out.println(
                String.format("| %-6d | %-30s | %-8s | %-14s | %-10d | %-30s | %-30s |",
                        s.getSongID(), songTitle, length, avgRating, ratingCount, artistName, albumName)
        );
    }
    System.out.println("+--------+------------------------------+----------+----------------+------------+------------------------------+------------------------------+");
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
