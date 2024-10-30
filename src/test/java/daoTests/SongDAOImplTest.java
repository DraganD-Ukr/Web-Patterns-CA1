package daoTests;

import Business.Song;
import daos.SongDAO;
import daos.SongDAOImpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Song DAO implementation test class used to test the methods in the SongDAOImpl class
 *
 * @author Aloysius Wilfred Pacheco D00253302
 */

class SongDAOImplTest {

    private static SongDAOImpl testSongDao;
    private static Song testSongToaddToDatabase, testSongToDeleteFromDatabase;
    private static List<Song> testSongListInAlbums , testSongListByArtists, testSongListInPlaylist;
    private static Song testSong;

    @BeforeAll
    public static void setup() {
        testSongDao = new SongDAOImpl("CA1_test");

        //test song that is in the database
        testSong = Song.builder()
                .songID(1)
                .title("Come Together")
                .albumID(1)
                .artistID(1)
                .length(LocalTime.of(0, 4, 20))
                .ratingCount(2)
                .averageRating(3.0)
                .ratingsSum(6)
                .build();

        // song to add to the database can be used for song manipulation tests
        testSongToaddToDatabase = Song.builder()
                .title("All Existence Void")
                .albumID(2)
                .artistID(2)
                .length(LocalTime.of(0, 3, 20))
                .ratingCount(0)
                .averageRating(0)
                .ratingsSum(0)
                .build();

        // delete test song
        testSongToDeleteFromDatabase = Song.builder()
                .title("All The WOrld")
                .albumID(3)
                .artistID(4)
                .length(LocalTime.of(0, 3, 10))
                .ratingCount(0)
                .averageRating(0)
                .ratingsSum(0)
                .build();

        // Add the song to the database to use for the delete test
        testSongDao.addSong(testSongToDeleteFromDatabase);

        testSongListInAlbums = List.of(
                Song.builder()
                        .songID(1)
                        .title("Come Together")
                        .albumID(1)
                        .artistID(1)
                        .length(LocalTime.of(0, 4, 20))
                        .ratingCount(2)
                        .averageRating(3.0)
                        .ratingsSum(6)
                        .build(),

                Song.builder()
                        .songID(2)
                        .title("Let It Be")
                        .albumID(1)
                        .artistID(1)
                        .length(LocalTime.of(0, 3, 50))
                        .ratingCount(2)
                        .averageRating(3.5)
                        .ratingsSum(7)
                        .build()
        );


        // test songs in the database related to artists(Looks redundant but it will be used in case
        // there may be a need to have multiple artists having one song in an album later on)
        testSongListByArtists = List.of(
                Song.builder()
                        .songID(1)
                        .title("Come Together")
                        .albumID(1)
                        .artistID(1)
                        .length(LocalTime.of(0, 4, 20))
                        .ratingCount(2)
                        .averageRating(3.0)
                        .ratingsSum(6)
                        .build(),
                Song.builder()
                        .songID(2)
                        .title("Let It Be")
                        .albumID(1)
                        .artistID(1)
                        .length(LocalTime.of(0, 3, 50))
                        .ratingCount(2)
                        .averageRating(3.5)
                        .ratingsSum(7)
                        .build()
        );

        testSongListInPlaylist = List.of(
                Song.builder()
                        .songID(1)
                        .title("Come Together")
                        .albumID(1)
                        .artistID(1)
                        .length(LocalTime.of(0, 4, 20))
                        .ratingCount(2)
                        .averageRating(3.0)
                        .ratingsSum(6)
                        .build(),

                Song.builder()
                        .songID(2)
                        .title("Let It Be")
                        .albumID(1)
                        .artistID(1)
                        .length(LocalTime.of(0, 3, 50))
                        .ratingCount(2)
                        .averageRating(3.5)
                        .ratingsSum(7)
                        .build(),

                Song.builder()
                        .songID(3)
                        .title("Stairway to Heaven")
                        .albumID(2)
                        .artistID(2)
                        .length(LocalTime.of(0, 8, 2))
                        .ratingCount(2)
                        .averageRating(4.5)
                        .ratingsSum(9)
                        .build(),

                Song.builder()
                        .songID(5)
                        .title("Blank Space")
                        .albumID(3)
                        .artistID(3)
                        .length(LocalTime.of(0, 3, 51))
                        .ratingCount(1)
                        .averageRating(4)
                        .ratingsSum(4)
                        .build()
        );
    }

    // Clean up the database after the tests
    @AfterAll
    public static void sweepDaFloor() {
        if(testSongDao.findSongByTitle(testSongToaddToDatabase.getTitle()) != null) {
            testSongDao.deleteSong(testSongDao.findSongByTitle(testSongToaddToDatabase.getTitle()).getSongID());
        }
    }

    // Test cases

    // Test if the song is found in the database by the title
    @Test
    void findSongByTitle_songFoundInDatabase() {
        String songToTest = "Come Together";

        Song foundSong = testSongDao.findSongByTitle(songToTest);

        assertEquals(testSong, foundSong);
    }

    @Test
    void findSongById_songFoundInDatabase() {
        int songId = 1;

        Song foundSong = testSongDao.findSongById(songId);

        assertEquals(testSong, foundSong);
    }

    @Test
    void findSongById_songNotFoundInDatabase() {
        int songId = -1;

        Song foundSong = testSongDao.findSongById(songId);

        assertNull(foundSong);
    }

    @Test
    void getAllSongsByTitle_songFoundInDatabase() {
        String songToTest = "Come Together";

        List<Song> foundSongs = testSongDao.getAllSongsByTitle(songToTest);

        assertEquals(List.of(testSong), foundSongs);
    }

    // Test if the song is --not-- found in the database by the title and return null
    @Test
    void findSongByTitle_songNotFoundInDatabase() {
        String songToTest = "I am Your Father";

        Song foundSong = testSongDao.findSongByTitle(songToTest);

        assertNull(foundSong);
    }

    // Test if the songs are found in the database from that album by id
    @Test
    void findSongsFromAlbumById_albumFoundInDatabase() {
        int albumId = 1;

        List<Song> foundSongs = testSongDao.findSongsFromAlbumById(albumId);

        assertEquals(testSongListInAlbums, foundSongs);
    }

    // Test if the songs are --not-- found in the database from that album by id and return an empty list
    @Test
    void findSongsFromAlbumById_albumNotFoundInDatabase() {
        int albumId = 100;

        List<Song> foundSongs = testSongDao.findSongsFromAlbumById(albumId);

        assertTrue(foundSongs.isEmpty());
    }

    @Test
    void findSongsInPlaylistByPlaylistName_playlistFoundInDatabase() {
        String playlistName = "Top Hits";

        List<Song> foundSongs = testSongDao.getSongsInPlaylistByPlaylistName(playlistName);

        assertEquals(testSongListInPlaylist, foundSongs);


    }

    @Test
    void findSongsInPlaylistByPlaylistName_playlistNotFoundInDatabase() {
        String playlistName = "Invalid Name";

        List<Song> foundSongs = testSongDao.getSongsInPlaylistByPlaylistName(playlistName);

        assertTrue(foundSongs.isEmpty());


    }

    // Test if the songs are found in the database from that album by name
    @Test
    void findSongsFromAlbumByName_albumFoundInDatabase() {
        String albumName = "Abbey Road";

        List<Song> foundSongs = testSongDao.findSongsFromAlbumByName(albumName);

        assertEquals(testSongListInAlbums, foundSongs);
    }

    // Test if the songs are --not-- found in the database from that album by name and return an empty list
    @Test
    void findSongsFromAlbumByName_albumNotFoundInDatabase() {
        String albumName = "The White Album";

        List<Song> foundSongs = testSongDao.findSongsFromAlbumByName(albumName);

        assertTrue(foundSongs.isEmpty());
    }

    // Test if the songs can be added to the database successfully
     @Test
    void findSongsFromArtist_artistFoundInDatabase() {
        String artist = "The Beatles";

        List<Song> foundSongs = testSongDao.findSongsFromArtist(artist);

        assertEquals(testSongListByArtists, foundSongs);
    }

    //test if the songs are not found in the database from that artist and return an empty list
    @Test
    void findSongsFromArtist_artistNotFoundInDatabase() {
        String artist = "The Rolling Stones";

        List<Song> foundSongs = testSongDao.findSongsFromArtist(artist);

        assertTrue(foundSongs.isEmpty());
    }
    @Test
    void addSong_songAddedToDatabaseSuccess() {
        boolean songAdded = testSongDao.addSong(testSongToaddToDatabase);

        assertTrue(songAdded);
        if(songAdded) {

            Song foundSong = testSongDao.findSongByTitle(testSongToaddToDatabase.getTitle());

            //because the songID is auto-incremented, we need to set the songID of the song to be added to the database,
            //but it is only this that needs to be changed
            testSongToaddToDatabase.setSongID(foundSong.getSongID());

            assertEquals(testSongToaddToDatabase, foundSong);
        }
    }

    // Find songs from artist test



    // Test if the songs can be deleted from the database successfully
    @Test
    void deleteSong_songDeletedFromDatabaseSuccess() {

        int songId = testSongDao.findSongByTitle(testSongToDeleteFromDatabase.getTitle()).getSongID();


        boolean songDeleted = testSongDao.deleteSong(songId);

        assertTrue(songDeleted);

        if(songDeleted) {
            Song foundSong = testSongDao.findSongByTitle(testSongToDeleteFromDatabase.getTitle());
            assertNull(foundSong);
        }
    }



    // Test if the method returns false when the song is not found in the database
    @Test
    void deleteSong_songDeletedFromDatabaseFailure() {
        int songId = -1;

        boolean songDeleted = testSongDao.deleteSong(songId);

        assertFalse(songDeleted);
    }


    @Test
    void findTopRatedSongSuccess(){
        Song topRatedSong = testSongDao.getTopRatedSong();

        assertEquals(Song.builder()
                .songID(3)
                .title("Stairway to Heaven")
                .albumID(2)
                .artistID(2)
                .length(LocalTime.of(0, 8, 2))
                .ratingCount(2)
                .averageRating(4.5)
                .ratingsSum(9)
                .build(), topRatedSong);
    }

    @Test
    void getMostPopularSong(){
        Song mostPopularSong = testSongDao.getMostPopularSong();

        System.out.println(mostPopularSong);

        assertEquals(Song.builder()
                .songID(5)
                .title("Blank Space")
                .albumID(3)
                .artistID(3)
                .length(LocalTime.of(0, 3, 51))
                .ratingCount(1)
                .averageRating(4)
                .ratingsSum(4)
                .build(), mostPopularSong);
    }


}