package daoTests;

import Business.Playlist;
import daos.PlaylistDAO;
import daos.PlaylistDAOimpl;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistDAOimplTest {
    private static PlaylistDAOimpl testPlaylistDao;

    //the last playlist id is used as a starting point from where test objects can be initialised and tested
    private static int lastPlaylistID;
    private static Playlist createPlaylistTestEntity, deletePlaylistTestEntity, addSongToPlaylistEntity, playlistTorename;

    @BeforeAll
    public static void setUp() {

        testPlaylistDao = new PlaylistDAOimpl("CA1_test");

        testPlaylistDao.startTransaction();
        lastPlaylistID = testPlaylistDao.getNextPlaylistID();

        createPlaylistTestEntity = Playlist.builder()
                .playlistId(lastPlaylistID+1)
                .userId(1)
                .name("Test Playlist")
                .isPublic(true)
                .build();

        deletePlaylistTestEntity = Playlist.builder()
                .playlistId(lastPlaylistID+2)
                .userId(2)
                .name("Test Playlist 2")
                .isPublic(false)
                .build();
    }

    @AfterEach
    public void resetAutoIncrement() {
    }
    @AfterAll
    public static void sweepaDaFloor() {
        testPlaylistDao.rollbackTransaction();
    }

    @Test
    void createPlaylist() {
        assertTrue(testPlaylistDao.createPlaylist(createPlaylistTestEntity));
        testPlaylistDao.deletePlaylistByID(createPlaylistTestEntity.getPlaylistId());
    }

    @Test
    void deletePlaylist_success(){
        System.out.println("Playlist ID: " + deletePlaylistTestEntity.getPlaylistId());
        //created to be destroyed (Playlists were harmed in the making of this test XD XD)
        testPlaylistDao.createPlaylist(deletePlaylistTestEntity);
        assertTrue(testPlaylistDao.deletePlaylistByID(deletePlaylistTestEntity.getPlaylistId()));
    }


    @Test
    void resetPlaylistAutoIncrementID_success() {
        //get the current AUTO_INCREMENT value
        int originalAutoIncrementID = testPlaylistDao.getNextPlaylistID();
        int testAutoIncrementID = 11;

        //test AUTO_INCREMENT to 11
        assertTrue(testPlaylistDao.resetPlatylistAutoIncrementID(testAutoIncrementID));
//        assertEquals(testAutoIncrementID, testPlaylistDao.getNextPlaylistID(),
//                " resetting AUTO_INCREMENT to  " + testAutoIncrementID);

        //reset AUTO_INCREMENT to original value
        //testPlaylistDao.resetPlatylistAutoIncrementID(originalAutoIncrementID);

    }



}