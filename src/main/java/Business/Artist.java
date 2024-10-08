package Business;

import lombok.*;

/**
 * @Author: Dmytro Drahan
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Artist {

    private Integer artistId;
    private String name;

    @Override
    public String toString() {
        return
                "Id=" + artistId +
                ", Name=" + name + '\'';
    }

}
