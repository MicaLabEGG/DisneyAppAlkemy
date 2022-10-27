package com.example.DisneyAppAlkemy.services;

import com.example.DisneyAppAlkemy.entity.Characters;
import com.example.DisneyAppAlkemy.entity.Movies;
import com.example.DisneyAppAlkemy.exception.ResourceNotFoundException;
import com.example.DisneyAppAlkemy.repository.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CharacterServices {

    @Autowired
    private CharacterRepository characterRepository;

    public Characters save(Characters character) {
        return characterRepository.save(character);
    }

    public Characters updateCharacter(Characters characters, Integer characterId, Movies movies) throws Exception {
        Optional<Characters> characters1 = characterRepository.findById(characterId);
        if (characters1.isPresent()) {
            Characters existingCharacters = characters1.get();
            existingCharacters.setImage(characters.getImage());
            existingCharacters.setName(characters.getName());
            existingCharacters.setAge(characters.getAge());
            existingCharacters.setWeight(characters.getWeight());
            existingCharacters.setHistory(characters.getHistory());
            existingCharacters.getMoviesId().add(movies);
            // guardar existingEmployee en DB
            characterRepository.save(existingCharacters);
            return existingCharacters;
        } else {
            throw new Exception();
        }
    }
    public List<Characters> getAllCharacters(){

        return characterRepository.findAll();
    }
    public Optional<Characters> findById(Integer characterId) {
        return characterRepository.findById(characterId);
    }
    public List<Characters> findByName(String name) {
        return characterRepository.findByName(name);
    }
    public List<Characters> findByAge(Integer age) {
        return characterRepository.findByAge(age);
    }
    public void delete(Integer characterId){
        Optional<Characters> characters = characterRepository.findById(characterId);
        if (characters.isPresent()) {
            characterRepository.deleteById(characterId);
        }else {
            throw new ResourceNotFoundException("Character", "characterId", characterId);
        }
    }
}
