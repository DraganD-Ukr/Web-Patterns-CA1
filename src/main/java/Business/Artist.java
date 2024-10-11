package Business;

import lombok.*;

/**
 * @Author: Dmytro Drahan
 */

@Getter
@Setter
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class Artist {

    private Integer artistId;
    @EqualsAndHashCode.Exclude
    private String name;

    @Override
    public String toString() {
        return
                "Id=" + artistId +
                ", Name=" + name + '\'';
    }



}
