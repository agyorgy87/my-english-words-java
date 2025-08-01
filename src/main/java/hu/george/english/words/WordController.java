package hu.george.english.words;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class WordController {

    @Autowired
    private WordRepository wordRepository;

    @PostMapping(
            path = "/create-word"
    )
    public boolean createWord(@RequestBody Word word) {
        wordRepository.save(word);
        return true;
    }

    @GetMapping(
            path = "/random-english-word"
    )
    public Word getRandomEnglishWord() {
        return wordRepository.findRandomWord().orElse(null);
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
