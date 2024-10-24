package daoTests;

import Business.Playlist;
import daos.PlaylistDAO;
import daos.PlaylistDAOimpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaylistDAOimplTest {
    private PlaylistDAO testPlaylistDao;
    private Playlist createPlaylistTestEntity, deletePlaylistTestEntity, addSongToPlaylistEntity, playlistTorename;

    @BeforeEach
    void setUp() {
        testPlaylistDao = new PlaylistDAOimpl("CA1_test");

        createPlaylistTestEntity = Playlist.builder()
                .playlistId(1)
                .userId(1)
                .name("Test Playlist")
                .isPublic(true)
                .build();

        deletePlaylistTestEntity = Playlist.builder()
                .playlistId(2)
                .userId(2)
                .name("Test Playlist 2")
                .isPublic(false)
                .build();

        addSongToPlaylistEntity = Playlist.builder()
                .playlistId(3)
                .userId(3)
                .name("Test Playlist 3")
                .isPublic(true)
                .build();

        playlistTorename = Playlist.builder()
                .playlistId(4)
                .userId(4)
                .name("Test Playlist 4")
                .isPublic(false)
                .build();

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createPlaylist() {
    }

    @Test
    void deletePlaylistByID() {
    }

    @Test
    void addSongToPlaylist() {
    }

    @Test
    void removeSongFromPlaylist() {
    }

    @Test
    void renamePlaylist() {
    }

    @Test
    void getPlaylists() {
    }

    @Test
    void getSongsInPlaylistByID() {
    }
}