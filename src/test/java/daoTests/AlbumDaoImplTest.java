package daoTests;

import Business.Album;
import daos.AlbumDaoImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class AlbumDaoImplTest {

    private static List<Album> allAlbums;

    @BeforeAll
    public static void init() {

//      Artists are in order by id for easier comparison
        allAlbums = new ArrayList<>();

        allAlbums.add(
                Album.builder()
                        .albumId(1)
                        .title("Abbey Road")
                        .artistId(1)
                        .releaseDate(Date.valueOf("1969-09-26"))
                        .build()
        );
        allAlbums.add(
                Album.builder()
                        .albumId(2)
                        .title("Led Zeppelin IV")
                        .artistId(2)
                        .releaseDate(Date.valueOf("1971-11-08"))
                        .build()
        );
        allAlbums.add(
                Album.builder()
                        .albumId(3)
                        .title("1989")
                        .artistId(3)
                        .releaseDate(Date.valueOf("2014-10-27"))
                        .build()
        );
        allAlbums.add(
                Album.builder()
                        .albumId(4)
                        .title("Lemonade")
                        .artistId(4)
                        .releaseDate(Date.valueOf("2016-04-23"))
                        .build()
        );
        allAlbums.add(
                Album.builder()
                        .albumId(5)
                        .title("The Eminem Show")
                        .artistId(5)
                        .releaseDate(Date.valueOf("2002-05-26"))
                        .build()
        );
    }


    @Test
    public void getAllAlbumsByArtistId(){
        AlbumDaoImpl albumDao = new AlbumDaoImpl("CA1_test");

        List<Album> result1 = albumDao.getAllAlbumsByArtistId(1);
        List<Album> result2 = albumDao.getAllAlbumsByArtistId(4);

        assertEquals(result1.size(), 1);
        assertEquals(result2.size(), 1);

        assertEquals(result1.get(0), allAlbums.get(0));
        assertEquals(result2.get(0), allAlbums.get(3));
    }



    @Test
    public void getAllAlbumsByArtistId_NotFound(){
        AlbumDaoImpl albumDao = new AlbumDaoImpl("CA1_test");

        List<Album> result1 = albumDao.getAllAlbumsByArtistId(7);

        assertEquals(result1.size(), 0);

    }

    @Test
    public void getAllAlbumsWhereArtistNameLike(){
        AlbumDaoImpl albumDao = new AlbumDaoImpl("CA1_test");

        List<Album> result1 = albumDao.getAllAlbumsWhereArtistNameLike("The Beatle");
        List<Album> result2 = albumDao.getAllAlbumsWhereArtistNameLike("Beyon");

        assertEquals(result1.size(), 1);
        assertEquals(result2.size(), 1);

        assertEquals(result1.get(0), allAlbums.get(0));
        assertEquals(result2.get(0), allAlbums.get(3));
    }

    @Test
    public void getAllAlbumsWhereArtistNameLike_NotFound(){
        AlbumDaoImpl albumDao = new AlbumDaoImpl("CA1_test");

        List<Album> result = albumDao.getAllAlbumsWhereArtistNameLike("Drak");

        assertEquals(result.size(), 0);

    }

    @Test
    public void getAllAlbumsByArtistName(){
        AlbumDaoImpl albumDao = new AlbumDaoImpl("CA1_test");

        List<Album> result1 = albumDao.getAllAlbumsByArtistName("Taylor Swift");
        List<Album> result2 = albumDao.getAllAlbumsByArtistName("Eminem");

        assertEquals(result1.size(), 1);
        assertEquals(result2.size(), 1);

        assertEquals(result1.get(0), allAlbums.get(2));
        assertEquals(result2.get(0), allAlbums.get(4));
    }

    @Test
    public void getAllAlbumsByArtistName_NotFound(){
        AlbumDaoImpl albumDao = new AlbumDaoImpl("CA1_test");

        List<Album> result1 = albumDao.getAllAlbumsByArtistName("Invalid artist name");

        assertEquals(result1.size(), 0);

    }


}
