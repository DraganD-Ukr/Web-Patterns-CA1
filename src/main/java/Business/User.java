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
    private String userName;
    private String password; //hashed password
    private int userID;




}
