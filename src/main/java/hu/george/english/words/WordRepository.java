package hu.george.english.words;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordRepository extends JpaRepository<Word, Integer> {

    @Query(value = "SELECT * FROM word WHERE used_word = false ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Optional<Word> findRandomWord();

    @Modifying
    @Transactional
    @Query("UPDATE Word w SET w.used = false")
    void resetAllUsedFlags();

    Optional<Word> findById(Integer id);

    List<Word> findByUsedFalse();

}
