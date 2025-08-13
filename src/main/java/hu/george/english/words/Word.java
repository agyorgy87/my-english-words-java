package hu.george.english.words;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
;
import lombok.*;

@Entity
@Table(name = "word")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Word {

    @Id
    @GeneratedValue
    @JsonProperty("id")
    private int id;

    @JsonProperty("englishWord")
    @Column(name = "english_word")
    private String englishWord;
    @JsonProperty("hungarianWord")
    @Column(name = "hungarian_word")
    private String hungarianWord;
    @Column(name = "used_word")
    private boolean used;

    public void setUsed(boolean used) {
        this.used = used;
    }

}