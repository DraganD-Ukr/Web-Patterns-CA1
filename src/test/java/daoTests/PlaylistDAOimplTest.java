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

        createPlaylistTestEntity = Playlist.builder()
                .userId(1)
                .name("Test Playlist")
                .isPublic(true)
                .build();

        deletePlaylistTestEntity = Playlist.builder()
                .userId(2)
                .name("Test Playlist 2")
                .isPublic(false)
                .build();
    }


    @Test
    void createPlaylist() {
        int id = testPlaylistDao.createPlaylist(createPlaylistTestEntity);
        assertTrue(id >= 0);
        testPlaylistDao.deletePlaylistByID(id);
    }

    @Test
    void deletePlaylist_success(){
        System.out.println("Playlist ID: " + deletePlaylistTestEntity.getPlaylistId());
        //created to be destroyed (Playlists were harmed in the making of this test XD XD)

        deletePlaylistTestEntity.setPlaylistId(testPlaylistDao.createPlaylist(deletePlaylistTestEntity));

        assertTrue(testPlaylistDao.deletePlaylistByID(deletePlaylistTestEntity.getPlaylistId()));
    }






}