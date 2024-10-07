DROP DATABASE IF EXISTS CA1_test;
CREATE DATABASE IF NOT EXISTS CA1_test;

USE CA1_test;


-- Create Artists table
CREATE TABLE Artists (
     artistID INT AUTO_INCREMENT PRIMARY KEY,
     name VARCHAR(255) NOT NULL UNIQUE
);

-- Create Albums table
CREATE TABLE Albums (
    albumID INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    artistID INT NOT NULL,
    releaseDate DATE NOT NULL,
    FOREIGN KEY (artistID) REFERENCES Artists(artistID)
);

-- Create Users table
CREATE TABLE Users (
   userID INT AUTO_INCREMENT PRIMARY KEY,
   firstName VARCHAR(100) NOT NULL,
   lastName VARCHAR(100) NOT NULL,
   username VARCHAR(100) NOT NULL UNIQUE,
   password VARCHAR(255) NOT NULL,
   registrationDate DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create Songs table
CREATE TABLE Songs (
   songID INT AUTO_INCREMENT PRIMARY KEY,
   title VARCHAR(255) NOT NULL,
   albumID INT NOT NULL,
   artistID INT NOT NULL,
   length TIME NOT NULL,
   ratingCount INT NOT NULL DEFAULT 0,
   averageRating DECIMAL(3, 2) CHECK (averageRating >= 1 AND averageRating <= 5) DEFAULT NULL,
   ratingsSum INT DEFAULT 0,
   FOREIGN KEY (albumID) REFERENCES Albums(albumID),
   FOREIGN KEY (artistID) REFERENCES Artists(artistID)
);

-- Create Ratings table
CREATE TABLE Ratings (
     ratingID INT AUTO_INCREMENT PRIMARY KEY,
     userID INT NOT NULL,
     songID INT NOT NULL,
     ratingValue TINYINT NOT NULL CHECK (ratingValue >= 1 AND ratingValue <= 5),
     FOREIGN KEY (userID) REFERENCES Users(userID),
     FOREIGN KEY (songID) REFERENCES Songs(songID),
     createdAt DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Create Playlists table
CREATE TABLE Playlists (
   playlistID INT AUTO_INCREMENT PRIMARY KEY,
   userID INT NOT NULL,
   name VARCHAR(255) NOT NULL,
   isPublic BOOLEAN DEFAULT FALSE,
   FOREIGN KEY (userID) REFERENCES Users(userID)
);

-- Create PlaylistSongs table (Junction table for Playlists and Songs)
CREATE TABLE PlaylistSongs (
   playlistID INT NOT NULL,
   songID INT NOT NULL,
   PRIMARY KEY (playlistID, songID),
   FOREIGN KEY (playlistID) REFERENCES Playlists(playlistID),
   FOREIGN KEY (songID) REFERENCES Songs(songID)
);

-- Create ArtistsSongs table (Junction table for Artists and Songs)
CREATE TABLE ArtistsSongs (
  artistID INT NOT NULL,
  songID INT NOT NULL,
  PRIMARY KEY (artistID, songID),
  FOREIGN KEY (artistID) REFERENCES Artists(artistID),
  FOREIGN KEY (songID) REFERENCES Songs(songID)
);



-- Using trigger to simplify the process of updating the rating count and average rating for a song

DELIMITER //

CREATE TRIGGER after_rating_insert
    AFTER INSERT ON Ratings
    FOR EACH ROW
BEGIN
    -- Update the rating count and average rating for the song
    UPDATE Songs
    SET
        -- Increment the rating count
        ratingCount = ratingCount + 1,

        -- Update the total sum of ratings
        ratingsSum = ratingsSum + NEW.ratingValue,

        -- Calculate the new average rating
        averageRating = ratingsSum / ratingCount
    WHERE
        -- The song that was rated
        songID = NEW.songID;
END; //

DELIMITER ;
