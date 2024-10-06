-- Insert data into Artists table
INSERT INTO Artists (name)
VALUES
    ('The Beatles'), ('Led Zeppelin'), ('Taylor Swift'), ('Beyoncé'), ('Eminem');



-- Insert data into Albums table
INSERT INTO Albums (title, artistID, releaseDate)
VALUES
    ('Abbey Road', 1, '1969-09-26'), ('Led Zeppelin IV', 2, '1971-11-08'),
    ('1989', 3, '2014-10-27'), ('Lemonade', 4, '2016-04-23'), ('The Eminem Show', 5, '2002-05-26');




-- Insert data into Users table
INSERT INTO Users (firstName, lastName, username, password)
VALUES
    ('John', 'Doe', 'johndoe', 'password123'),
    ('Jane', 'Smith', 'janesmith', 'password456');




-- Insert data into Songs table
INSERT INTO Songs (title, artistID, albumID, length)
VALUES
    ('Come Together', 1, 1, '00:04:20'), --  (The Beatles)
    ('Let It Be', 1, 1, '00:03:50'),
    ('Stairway to Heaven', 2, 2, '00:08:02'), -- (Led Zeppelin)
    ('Whole Lotta Love', 2, 2, '00:05:34'),
    ('Blank Space', 3, 3, '00:03:51'), -- (Taylor Swift)
    ('Formation', 4, 4, '00:03:26'), --  (Beyoncé)
    ('Halo', 4, 4, '00:03:44'),
    ('Lose Yourself', 5, 5, '00:05:26'), --  (Eminem)
    ('Without Me', 5, 5, '00:04:00');





-- Insert data into ArtistsSongs (Junction) table
INSERT INTO ArtistsSongs (artistID, songID)
VALUES
    (1, 1), -- The Beatles - Come Together
    (1, 2), -- The Beatles - Let It Be
    (2, 3), -- Led Zeppelin - Stairway to Heaven
    (2, 4), -- Led Zeppelin - Whole Lotta Love
    (3, 5), -- Taylor Swift - Blank Space
    (4, 6), -- Beyoncé - Formation
    (4, 7), -- Beyoncé - Halo
    (5, 8), -- Eminem - Lose Yourself
    (5, 9); -- Eminem - Without Me


-- Insert data into Playlists table
INSERT INTO Playlists (name, userID, isPublic)
VALUES
    ('Top Hits', 1, true), -- Playlist created by User 1
    ('Chill Out', 2, true);

-- Insert data into PlaylistSongs(Junction Table) table
INSERT INTO PlaylistSongs (playlistID, songID) VALUES
       (1, 1), -- Top Hits - Come Together
       (1, 2), -- Top Hits - Stairway to Heaven
       (1, 3), -- Top Hits - Blank Space
       (2, 4), -- Chill Out - Formation
       (2, 5); -- Chill Out - Lose Yourself



-- Insert data into Ratings table
INSERT INTO Ratings (userID, songID, ratingValue) VALUES
      (1, 1, 4),  -- User 1 rates Stairway to Heaven
      (1, 2, 3),  -- User 2 rates Blank Space
      (1, 3, 4),  -- User 3 rates Formation
      (1, 4, 5),  -- User 4 rates Lose Yourself
      (1, 5, 3), -- User 1 rates God's Plan
      (2, 1, 2), -- User 1 rates Diamonds
      (2, 2, 4),  -- User 2 rates Without Me
      (2, 3, 5),  -- User 3 rates Lose Yourself
      (2, 4, 3), -- User 4 rates Hotline Bling
      (2, 5, 4); -- User 1 rates thank u, next












