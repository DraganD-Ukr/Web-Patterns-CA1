package Application;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import Utils.Encryption;
import Utils.GraphicsDisplay;
import daos.*;
import Business.*;

import javax.xml.crypto.Data;
/**
 * The main class of the application.
 * @version 1.0
 * @author Aloysius Wilfred Pacheco
 * @author Dmytro Drahan
 * @author JoArt Mahilaga
 */
public class MusicPlaylistApplication {
    //Database selection toggle
    private static final String DATABASE_NAME = "CA1_test";
    //private static final String DATABASE_NAME = "CA1";
    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAOimpl userDAO = new UserDAOimpl(DATABASE_NAME);
    private static final ArtistDaoImpl artistDAO = new ArtistDaoImpl(DATABASE_NAME);
    private static final AlbumDaoImpl albumDAO = new AlbumDaoImpl(DATABASE_NAME);
    private static final SongDAOImpl songDAO = new SongDAOImpl(DATABASE_NAME);
    private static final PlaylistDAOimpl playlistDAO = new PlaylistDAOimpl(DATABASE_NAME);
    private static final RatingDaoimpl ratingDAO = new RatingDaoimpl(DATABASE_NAME);
    private static final Encryption encryption = new Encryption();
    private static User currentUser = null;

    public static void main(String[] args) {

//    ----------------LOGIN/REGISTER FUNCTIONALITY -----------------------

        boolean exit = false;

        while (!exit) {

            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    login();
                    break;
                case 3:
                    if (currentUser != null) {
                        generalAccess();
                    } else {
                        System.out.println("You need to log in first.");
                    }
                    break;
                case 4:
                    if (currentUser != null) {
                        managePlaylists();
                    } else {
                        System.out.println("You need to log in first.");
                    }
                    break;
                case 5:
                    if (currentUser != null) {
                        manageRatings();
                    } else {
                        System.out.println("You need to log in first.");
                    }
                    break;
                case 6:
                    logout();
                    break;
                case 7:
                    exit = true;
                    System.out.println("Cya later Dude!");
                    break;
                default:
                    System.out.println("Wata heck are you doing dude?");
            }

        }

    }

    private static void displayMenu() {
        System.out.println("\n (っ◔◡◔)っ Music App Menu (っ◔◡◔)っ");
        System.out.println("1 Register");
        System.out.println("2 Login");
        System.out.println("3 General Access");
        System.out.println("4 Manage Playlists");
        System.out.println("5 Manage Ratings");
        System.out.println("6 Logout");
        System.out.println("7 Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getMenuChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input please enter a number.");
            scanner.next();
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        return choice;
    }

    public static void register() {
        String firstName = validateInput("Enter first name: ", "^[A-Za-z]{2,30}$", "Invalid first name. Use 2-30 alphabetic characters.");
        String lastName = validateInput("Enter last name: ", "^[A-Za-z]{2,30}$", "Invalid last name. Use 2-30 alphabetic characters.");
        String username = validateUsername();
        String password = validatePassword(username);
        validateReenteredPassword(password);
        String creditCardNumber = validateInput("Enter credit card number: ", "^\\d{16}$", "Invalid credit card number enter 16 digits.");

        // Hash the password before storing
        String hashedPassword = encryption.hashPassword(password);

        // Create and store the new user
        User newUser = new User(firstName, lastName, username, hashedPassword, 0);
        if (userDAO.addUser(newUser)) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed try again.");
        }
    }

    public static void login() {
        String username = validateInput("Enter username: ", "^[A-Za-z0-9_]{3,20}$", "Invalid username. Use 3-20 alphanumeric characters or underscores.");
        String password = validateInput("Enter password: ", ".*", "");

        if (authenticateUser(username, password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
            currentUser = userDAO.getUserByName(username);
        } else {
            System.out.println("Login failed invalid username or password.");
        }
    }

    private static boolean authenticateUser(String username, String password) {
        User user = userDAO.getUserByName(username);
        if (user != null) {
            return encryption.checkPasswordWithUsername(password, username);
        }
        return false;
    }

    private static String validateInput(String prompt, String regex, String errorMessage) {
        String input;
        do {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!Pattern.matches(regex, input)) {
                System.out.println(errorMessage);
            }
        } while (!Pattern.matches(regex, input));
        return input;
    }

    private static String validateUsername() {
        String username;
        do {
            username = validateInput("Enter username: ", "^[A-Za-z0-9_]{3,20}$", "Invalid username use 3-20 alphanumeric characters or underscores.");
            if (userDAO.existsbyUserName(username)) {
                System.out.println("Username already exists choose another one.");
            }
        } while (userDAO.existsbyUserName(username));
        return username;
    }

    private static String validatePassword(String username) {
        String password;
        do {
            password = validateInput("Enter password: ", "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",
                    "Invalid password. It must contain at least 8 characters, including uppercase, lowercase, number, and special character.");
            if (password.toLowerCase().contains(username.toLowerCase())) {
                System.out.println("Password cannot contain the username.");
            }
        } while (password.toLowerCase().contains(username.toLowerCase()));
        return password;
    }

    private static void validateReenteredPassword(String password) {
        String reenteredPassword;
        do {
            reenteredPassword = validateInput("Re-enter password: ", ".*", "");
            if (!reenteredPassword.equals(password)) {
                System.out.println("Passwords do not match try again.");
            }
        } while (!reenteredPassword.equals(password));
    }


//    ------------------------------------MAIN FUNCTIONALITY--------------------------------------



    private static void generalAccess() {

        System.out.println("\nGeneral Access Menu:");
        System.out.println("1 View all artists");
        System.out.println("2 View all albums for an artist");
        System.out.println("3 View all songs in an album");
        System.out.println("4 Search for songs");
        System.out.println("5 Exit");

        boolean exit = false;
        while(!exit){
            System.out.print("Enter your choice: ");
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    viewAllArtists();
                    break;
                case 2:
                    viewAlbumsForArtist();
                    break;
                case 3:
                    viewSongsInAlbum();
                    break;
                case 4:
                    searchSongByTitleArtistAlbums();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exited");
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

    }

    private static void viewAllArtists() {

        List<Artist> artists = artistDAO.getAllArtists();

        GraphicsDisplay.DisplayArtists(artists);

    }

    private static void viewAlbumsForArtist() {

        String artistName = validateInput("Enter artist name: ", ".*", "");

        List<Album> albums = albumDAO.getAllAlbumsWhereArtistNameLike(artistName);

        GraphicsDisplay.DisplayAlbums(albums, artistDAO);

    }

    private static void viewSongsInAlbum() {

        String albumTitle = validateInput("Enter album title: ", ".*", "");

        List<Song> songs = songDAO.findSongsFromAlbumByName(albumTitle);

        GraphicsDisplay.DisplaySongs(songs, artistDAO, albumDAO);
    }

    private static void searchSongByTitleArtistAlbums() {

        System.out.println("\nSearch Menu:");
        System.out.println("1 Search by song title");
        System.out.println("2 Search by artist name");
        System.out.println("3 Search by album title");
        System.out.println("4 Exit");


        boolean exit = false;
        while (!exit) {

            System.out.print("Enter your choice: ");
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    searchSongByTitle();
                    break;
                case 2:
                    searchSongByArtist();
                    break;
                case 3:
                    searchSongByAlbum();
                    break;
                case 4:
                    System.out.println("Exited");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

    }

    private static void searchSongByAlbum() {
        List<Song> songsInAlbum = songDAO.findSongsFromAlbumByName(validateInput("Enter album title: ", ".*", ""));
        GraphicsDisplay.DisplaySongs(songsInAlbum, artistDAO, albumDAO);

    }

    private static void searchSongByArtist() {
        List<Song> songsByArtist = songDAO.findSongsFromArtist(validateInput("Enter artist name: ", ".*", ""));
        GraphicsDisplay.DisplaySongs(songsByArtist, artistDAO, albumDAO);
    }

    private static void searchSongByTitle() {
        List<Song> songsByTitle = songDAO.getAllSongsByTitle(validateInput("Enter song title: ", ".*", ""));
        GraphicsDisplay.DisplaySongs(songsByTitle, artistDAO, albumDAO);
    }

    private static void managePlaylists() {

        System.out.println("\nManage Playlists Menu:");
        System.out.println("1 Create a playlist");
        System.out.println("2 Edit a playlist");
        System.out.println("3 View playlists");
        System.out.println("4 View songs in a playlist");
        System.out.println("5 Exit");


        boolean exit = false;

        while (!exit) {
            System.out.print("Enter your choice: ");
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    createPlaylist();
                    break;
                case 2:
                    editPlaylist();
                    break;
                case 3:
                    viewPlaylists();
                    break;
                case 4:
                    viewSongsInPlaylist();
                    break;
                case 5:
                    System.out.println("Exited");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

    }

    private static void viewSongsInPlaylist() {
        String playlistName = validateInput("Enter playlist name: ", ".*", "");
        Playlist playlist = playlistDAO.getPlaylistByName(playlistName);

        if (playlist == null || !authorize(currentUser.getUserID(), playlist.getUserId())) {
            System.out.println("Playlist not found or you do not have permission to view it.");
            return;
        }

        List<Song> songs = songDAO.getSongsInPlaylistByPlaylistName(playlistName);

        GraphicsDisplay.DisplaySongs(songs, artistDAO, albumDAO);
    }

    private static void createPlaylist() {

        String name = validateInput("Enter playlist name: ", ".*", "");
        boolean isPublic = validateInput("Is the playlist public? (yes/no): ", "^(yes|no)$", "Invalid input. Enter 'yes' or 'no'.").equalsIgnoreCase("yes");

        Playlist playlist = new Playlist(currentUser.getUserID(), name, isPublic);

        if (playlistDAO.createPlaylist(playlist) >= 0) {
            System.out.println("Playlist created successfully!");
        } else {
            System.out.println("Failed to create playlist.");
        }
    }

    private static void editPlaylist() {

        String playlistName = validateInput("Enter playlist name to edit: ", ".*", "");
        Playlist playlist = playlistDAO.getPlaylistByName(playlistName);

        if (playlist == null || !authorize(currentUser.getUserID(), playlist.getUserId())) {
            System.out.println("Playlist not found or you do not have permission to edit it.");
            return;
        }



        System.out.println("\nEdit Playlist Menu:");
        System.out.println("1 Add a song");
        System.out.println("2 Remove a song");
        System.out.println("3 Rename playlist");
        System.out.println("4 Exit");

        boolean exit = false;
        while (!exit) {
            System.out.print("Enter your choice: ");
            int choice = getMenuChoice();
            switch (choice) {
                case 1:
                    addSongToPlaylist(playlist);
                    break;
                case 2:
                    removeSongFromPlaylist(playlist);
                    break;
                case 3:
                    renamePlaylist(playlist);
                    break;
                case 4:
                    System.out.println("Exited");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }

    }

    private static void addSongToPlaylist(Playlist playlist) {

        String songTitle = validateInput("Enter song title to add: ", ".*", "");
        Song song = songDAO.findSongByTitle(songTitle);

        if (song != null) {
            if (playlistDAO.addSongToPlaylist(playlist.getPlaylistId(), song.getSongID())) {
                System.out.println("Song added to playlist.");
            } else {
                System.out.println("Failed to add song to playlist.");
            }
        } else {
            System.out.println("Song not found.");
        }
    }

    private static void removeSongFromPlaylist(Playlist playlist) {

        String songTitle = validateInput("Enter song title to remove: ", ".*", "");
        Song song = songDAO.findSongByTitle(songTitle);

        if (song != null) {
            if (playlistDAO.removeSongFromPlaylist(playlist.getPlaylistId(), song.getSongID())) {
                System.out.println("Song removed from playlist.");
            } else {
                System.out.println("Failed to remove song from playlist.");
            }
        } else {
            System.out.println("Song not found.");
        }
    }

    private static void renamePlaylist(Playlist playlist) {

        String newName = validateInput("Enter new playlist name: ", ".*", "");
        playlist.setName(newName);

        if (playlistDAO.renamePlaylist(playlist.getPlaylistId(), newName)) {
            System.out.println("Playlist renamed successfully.");
        } else {
            System.out.println("Failed to rename playlist.");
        }

    }

    private static void viewPlaylists() {

        List<Playlist> playlists = playlistDAO.getPlaylists(currentUser.getUserID());

        GraphicsDisplay.DisplayPlaylists(playlists, userDAO);

    }

    private static void manageRatings() {

        System.out.println("\nManage Ratings Menu:");
        System.out.println("1 Rate a song");
        System.out.println("2 View rated songs");
        System.out.println("3 Get top-rated song");
        System.out.println("4 Get most popular song");


        int choice = getMenuChoice();
        boolean exit = false;
        while (!exit) {
            System.out.println("5 Exit");
            System.out.print("Enter your choice: ");
            switch (choice) {
                case 1:
                    rateSong();
                    break;
                case 2:
                    viewRatedSongs();
                    break;
                case 3:
                    getTopRatedSong();
                    break;
                case 4:
                    getMostPopularSong();
                    break;
                case 5:
                    System.out.println("Exited");
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void rateSong() {

        String songTitle = validateInput("Enter song title to rate: ", ".*", "");
        int rating = Integer.parseInt(validateInput("Enter rating (1-5): ", "^[1-5]$", "Invalid rating. Enter a number between 1 and 5."));

        Song song = songDAO.findSongByTitle(songTitle);

        if (song != null) {
            Rating ratingObj = new Rating(currentUser.getUserID(), song.getSongID(), rating);
            if (ratingDAO.addRating(ratingObj)) {
                System.out.println("Song rated successfully.");
            } else {
                System.out.println("Failed to rate song.");
            }
        } else {
            System.out.println("Song not found.");
        }

    }

    private static void viewRatedSongs() {

        List<Rating> ratings = ratingDAO.getRatingsByUserID(currentUser.getUserID());

        GraphicsDisplay.DisplayRatings(ratings, songDAO);

    }


    private static void getTopRatedSong() {

        Song topRatedSong = songDAO.getTopRatedSong();

        if (topRatedSong != null) {
            System.out.println("Top-rated song");
            GraphicsDisplay.DisplaySong(topRatedSong, artistDAO, albumDAO);
        } else {
            System.out.println("No ratings found.");
        }

    }


    private static void getMostPopularSong() {

        Song mostPopularSong = songDAO.getMostPopularSong();

        if (mostPopularSong != null) {
            System.out.println("Most popular song");
            GraphicsDisplay.DisplaySong(mostPopularSong, artistDAO, albumDAO);
        } else {
            System.out.println("No playlists found.");
        }

    }






    private static void logout() {
        currentUser = null;
        System.out.println("Logged out successfully.");
    }



    private static boolean authorize(int currentUserId, int sourceUserID) {
        if (currentUser == null || currentUserId != sourceUserID) {
            return false;
        }else{
            return true;
        }

    }
}