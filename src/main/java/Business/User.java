package Business;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class User {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    private String userName;
    private String password; //hashed password
    private int userID;

}
