package Business;

import lombok.*;

/**
 * @Author: Jo Art Mahilaga
 */

@Getter
@Setter
@AllArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class User {

    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    private String userName;
    @NonNull
    private String password; //hashed password
    private int userID;




}
