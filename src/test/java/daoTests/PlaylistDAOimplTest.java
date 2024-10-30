package daoTests;

import Business.Playlist;
import daos.PlaylistDAOimpl;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Test class for the PlaylistDAOimpl class
 * @Author: Aloysius Wilfred Pacheco D00253302
 **/

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PlaylistDAOimplTest {
    private static PlaylistDAOimpl testPlaylistDao;
    private static Playlist testPlaylist;
    private static List<Playlist> testPlaylists,playlistPresentInDatabase,testGetPlaylistsList;

    //store the Test id of the created playlist

    @BeforeAll
    public static void setUp() {

        testPlaylistDao = new PlaylistDAOimpl("CA1_test");

        playlistPresentInDatabase = List.of(
                Playlist.builder()
                        .playlistId(1)
                        .userId(1)
                        .name("Top Hits")
                        .isPublic(true)
                        .build(),
                Playlist.builder()
                        .playlistId(2)
                        .userId(2)
                        .name("Chill Out")
                        .isPublic(true)
                        .build()
        );

        testPlaylist = Playlist.builder()
                .userId(1)
                .name("Test Playlist")
                .isPublic(false)
                .build();

        testPlaylists = List.of(
                Playlist.builder()
                        .userId(2)
                        .name("Test Playlist 1")
                        .isPublic(true)
                        .build(),
                Playlist.builder()
                        .userId(2)
                        .name("Test Playlist 2")
                        .isPublic(true)
                        .build(),
                Playlist.builder()
                        .userId(2)
                        .name("Test Playlist 3")
                        .isPublic(true)
                        .build()
        );

        testGetPlaylistsList = new ArrayList<>();


    }





    @Test
    @Order(1)
    void createPlaylist_success() {
        //creates playlist
        testPlaylist.setPlaylistId(testPlaylistDao.createPlaylist(testPlaylist));

        //make sure the playlist is created
        assertTrue(testPlaylist.getPlaylistId() > 0);
        assertEquals(testPlaylist, testPlaylistDao.getPlaylistByID(testPlaylist.getPlaylistId()));
    }

    @Test
    @Order(2)
    void AddSongToPlaylistEntity_success(){
        //add a song to the playlist tes
        assertTrue(testPlaylistDao.addSongToPlaylist(testPlaylist.getPlaylistId(), 1));


    }
    @Test
    @Order(3)
    void removeSongFromPlaylist_success(){
        //remove the song from the playlist
        assertTrue(testPlaylistDao.removeSongFromPlaylist(testPlaylist.getPlaylistId(), 1));
    }

    @Test
    @Order(5)
    void renamePlaylist_success() {
        //rename the playlist
        String newName = "New Playlist Name";
        assertTrue(testPlaylistDao.renamePlaylist(testPlaylist.getPlaylistId(), newName));

        //check if the playlist was renamed
        assertEquals(newName, testPlaylistDao.getPlaylistByID(testPlaylist.getPlaylistId()).getName());
    }

    @Test
    @Order(6)
    void getPlaylists_success() {
        //put playlists in the database
        for (Playlist playlist : testPlaylists) {
            playlist.setPlaylistId(testPlaylistDao.createPlaylist(playlist));
        }

        //get the playlists
        List<Playlist> playlists = testPlaylistDao.getPlaylists(2);

        //create a list of all the playlists
        testGetPlaylistsList.addAll(playlistPresentInDatabase);
        testGetPlaylistsList.addAll(testPlaylists);

        //check if the playlists are the same
        assertEquals(testGetPlaylistsList, playlists);

        //delete the playlists
        for (Playlist playlist : testPlaylists) {
            testPlaylistDao.deletePlaylistByID(playlist.getPlaylistId());
        }

    }

    @Test
    @Order(7)
    void deletePlaylist_success(){
        assertTrue(testPlaylistDao.deletePlaylistByID(testPlaylist.getPlaylistId()));

        //check if the playlist was deleted really
        if (testPlaylistDao.getPlaylistByID(testPlaylist.getPlaylistId()) == null){
            assertNull(testPlaylistDao.getPlaylistByID(testPlaylist.getPlaylistId()));
        }
    }

    @Test
    @Order(8)
    void getPlaylistByID_success(){
        //get the playlist by id
        Playlist playlist = testPlaylistDao.getPlaylistByID(1);

        //check if the playlist is the same
        assertEquals(playlistPresentInDatabase.get(0), playlist);
    }

    @Test
    @Order(9)
    void getPlaylistByName_success(){
        //get the playlist by name
        Playlist playlist = testPlaylistDao.getPlaylistByName("Top Hits");

        //check if the playlist is the same
        assertEquals(playlistPresentInDatabase.get(0), playlist);
    }






}