package hu.george.english.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WordController {

    @Autowired
    private final WordRepository wordRepository;

    public WordController(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    @PostMapping(
            path = "/create-word"
    )
    public boolean createWord(@RequestBody Word word) {
        wordRepository.save(word);
        return true;
    }
/* working!!!
    @GetMapping(
            path = "/random-english-word"
    )
    public Word getRandomEnglishWord() {
        return wordRepository.findRandomWord().orElse(null);
    }
*/
/* working!!!
    @GetMapping(
            path = "/random-english-word"
    )
    public ResponseEntity<Word> getRandomEnglishWord() {
        Optional<Word> optionalRandomWord = wordRepository.findRandomWord();

        if(optionalRandomWord.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        Word randomWord = optionalRandomWord.get();

        randomWord.setUsed(true);
        wordRepository.save(randomWord);

        return ResponseEntity.ok(randomWord);
    }
*/

    @GetMapping(
            path = "/random-english-word"
    )
    public Word getRandomEnglishWord() {
        Optional<Word> optionalRandomWord = wordRepository.findRandomWord();

        if(optionalRandomWord.isEmpty()) {
            wordRepository.resetAllUsedFlags();
            optionalRandomWord = wordRepository.findRandomWord();
        }

        if(optionalRandomWord.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No words found.");
        }

        Word randomWord = optionalRandomWord.get();
        randomWord.setUsed(true);
        wordRepository.save(randomWord);

        return randomWord;
    }

    @GetMapping(
            path ="/get-hungarian-word/{id}"
    )
    public Word getHungarianWordById(@PathVariable int id) {
        return wordRepository.findById(id).orElse(null);
    }

    @GetMapping(
            path = "/all-words"
    )
    public List<Word> findAll(){return wordRepository.findAll();}

    @DeleteMapping(
            path = "/deleteword/{id}"
    )
    public boolean deleteWord(@PathVariable int id) {
        Optional<Word> word = wordRepository.findById(id);
        if(word.isEmpty()) {
            return false;
        }else{
            wordRepository.delete(word.get());
            return true;
        }
    }


}
