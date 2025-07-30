package hu.george.english.words;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "word")
@Getter
@Setter

public class Word {

    @Id
    @GeneratedValue
    private int id;

    private String englishWord;

    private String hungarianWord;

}