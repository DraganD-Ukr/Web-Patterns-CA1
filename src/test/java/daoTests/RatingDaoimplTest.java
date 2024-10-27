package daoTests;

import Business.Rating;
import daos.RatingDaoimpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author: Jo Art Mahilaga
 */

public class RatingDaoimplTest {
    private static List<Rating> ratingsToTestFromUser, ratingsBySongID;
    private static Rating ratingToBeDeleted, ratingToBeAdded, ratingToUpdated, ratingToBeDuplicated;

    @BeforeAll
    public static void init() {
        ratingsToTestFromUser = new ArrayList<>();

        ratingsToTestFromUser.add(
                Rating.builder()
                        .ratingID(1)
                        .userID(1)
                        .songID(1)
                        .ratingValue(4)
                        .build());
        ratingsToTestFromUser.add(
                Rating.builder()
                        .ratingID(2)
                        .userID(1)
                        .songID(2)
                        .ratingValue(3)
                        .build());
        ratingsToTestFromUser.add(
                Rating.builder()
                        .ratingID(3)
                        .userID(1)
                        .songID(3)
                        .ratingValue(4)
                        .build());
        ratingsToTestFromUser.add(
                Rating.builder()
                        .ratingID(4)
                        .userID(1)
                        .songID(4)
                        .ratingValue(5)
                        .build());
        ratingsToTestFromUser.add(
                Rating.builder()
                        .ratingID(5)
                        .userID(1)
                        .songID(5)
                        .ratingValue(3)
                        .build());

        ratingsBySongID = new ArrayList<>();

        ratingsBySongID.add(
                Rating.builder()
                        .ratingID(2)
                        .userID(1)
                        .songID(2)
                        .ratingValue(3)
                        .build());

        ratingsBySongID.add(
                Rating.builder()
                        .ratingID(7)
                        .userID(2)
                        .songID(2)
                        .ratingValue(4)
                        .build());

        //--------------------------------------------------------------------------------------------------------------


        ratingToUpdated = Rating.builder()
                .ratingID(42)
                .userID(2)
                .songID(2)
                .ratingValue(3)
                .build();

        ratingToBeAdded = Rating.builder()
                .ratingID(24)
                .userID(2)
                .songID(4)
                .ratingValue(5)
                .build();

        ratingToBeDeleted = Rating.builder()
                .ratingID(23)
                .userID(2)
                .songID(4)
                .ratingValue(3)
                .build();

        ratingToBeDuplicated = Rating.builder()
                .ratingID(27)
                .userID(2)
                .songID(4)
                .ratingValue(3)
                .build();
    }


    @Test
    public void addRating_Success() {

        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        boolean result = ratingDao.addRating(ratingToBeAdded);

        assertTrue(result);

        ratingDao.deleteRating(ratingToBeAdded);
    }


    @Test
    public void addRating_Failure_NullRating() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        boolean result = ratingDao.addRating(null);

        assertEquals(false, result);
    }

    @Test
    public void updateRating_Success() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        assertTrue(ratingDao.addRating(ratingToUpdated));

        ratingToUpdated.setRatingValue(3);
        assertTrue(ratingDao.updateRating(ratingToUpdated));

        ratingDao.deleteRating(ratingToUpdated);
    }

    @Test
    public void deleteRating_Success() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        ratingDao.addRating(ratingToBeDeleted);

        boolean result = ratingDao.deleteRating(ratingToBeDeleted);

        assertEquals(true, result);
    }

    @Test
    public void getRatingByUserID_NotFound() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        List<Rating> ratings = ratingDao.getRatingsByUserID(42069);

        assertTrue(ratings.isEmpty());
    }

    @Test
    public void getRatingBySongID_NotFound() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        List<Rating> ratings = ratingDao.getRatingsBySongID(999);

        assertTrue(ratings.isEmpty());
    }

    @Test
    public void getRatingByUserID_IsFound() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        List<Rating> ratings = ratingDao.getRatingsByUserID(1);

        assertEquals(ratingsToTestFromUser, ratings);
    }

    @Test
    public void getRatingBySongID_IsFound() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        List<Rating> ratings = ratingDao.getRatingsBySongID(2);

        assertEquals(ratingsBySongID, ratings);
    }

    @Test
    public void getRatingBySongID_UserExists() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        List<Rating> ratings = ratingDao.getRatingsBySongID(1);

        assertFalse(ratings.isEmpty());
        assertTrue(ratings.stream().anyMatch(r -> r.getSongID() == 1));

    }

    @Test
    public void addDuplicateRating_Failure() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        ratingDao.addRating(ratingToBeDuplicated);

        assertFalse(ratingDao.addRating(ratingToBeDuplicated));

        ratingDao.deleteRating(ratingToBeDuplicated);
    }


    @Test
    public void getRatingByUserIDandSongID_SongFound() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        Rating existingRating = ratingDao.getRatingByUserIDandSongID(1, 1);
        assertNotNull(existingRating);
    }

    @Test
    public void getRatingByUserIDandSongID_UserDoesNotExist() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        Rating nonExistentUserRating = ratingDao.getRatingByUserIDandSongID(1, 9999);
        assertNull(nonExistentUserRating);
    }

    @Test
    public void getRatingByUserIDandSongID_SongDoesNotExist() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        Rating nonExistentSongRating = ratingDao.getRatingByUserIDandSongID(9999, 1);
        assertNull(nonExistentSongRating);
    }

    @Test
    public void getRatingByUserIDandSongID_RatingExists() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        Rating existingRating = ratingDao.getRatingByUserIDandSongID(2, 2);
        assertNotNull(existingRating);
        assertEquals(7, existingRating.getRatingID());
        assertEquals(2, existingRating.getUserID());
        assertEquals(2, existingRating.getSongID());
        assertEquals(4, existingRating.getRatingValue());
    }

    @Test
    public void getRatingByUserIDandSongID_NoRatingExists() {
        RatingDaoimpl ratingDao = new RatingDaoimpl("CA1_test");

        Rating nonExistentRating = ratingDao.getRatingByUserIDandSongID(69, 69);
        assertNull(nonExistentRating);
    }

}