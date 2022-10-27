package com.example.DisneyAppAlkemy.controller;

import com.example.DisneyAppAlkemy.entity.Characters;
import com.example.DisneyAppAlkemy.services.CharacterServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RequestMapping("/characters")
@RestController
public class CharacterController {

    @Autowired
    private CharacterServices characterServices;

    //Anda Postman
    @PostMapping("save")
    public ResponseEntity<Characters> save(@RequestParam("file") MultipartFile image,
                                           @Validated Characters characters){
        if(!image.isEmpty()){
            Path imagesPath = Paths.get("src//main//resources//static//images");
            String absolutPath = imagesPath.toFile().getAbsolutePath();
            try {
                byte[] bytes = image.getBytes();
                Path route = Paths.get(absolutPath + image.getOriginalFilename());
                Files.write(route, bytes);
                characters.setImage(image.getOriginalFilename());

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<Characters>(characterServices.save(characters), HttpStatus.CREATED);
    }

    //Postman anda
    @PutMapping("/{characterId}")
    public ResponseEntity<Characters> updateCharacter(@PathVariable("characterId") Integer characterId,@Validated Characters characters) throws Exception {
        if(characters.getCharacterId() == null){
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<Characters>(characterServices.updateCharacter(characters, characterId, characters.getMoviesId().get(0)), HttpStatus.OK);
    }

    //Postman anda
    @GetMapping()
    public List<Characters> getAllCharacters() {
        return characterServices.getAllCharacters();
    }

    //Postman anda
    @GetMapping("/{characterId}")
    public ResponseEntity<Characters> findById(@PathVariable("characterId") Integer characterId) {
        Optional<Characters> charopt = characterServices.findById(characterId);
        if(charopt.isPresent()){
            return ResponseEntity.ok(charopt.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Postman anda
    @GetMapping(params="name")
    public List<Characters> findByName(@RequestParam("name") String name){
        return characterServices.findByName(name);
    }

    //Postman anda
    @GetMapping(params="age")
    public List<Characters> findByAge(@RequestParam("age") Integer age){
        return characterServices.findByAge(age);
    }

    //Postman anda
    @DeleteMapping("delete/{characterId}")
    public ResponseEntity<String> delete(@PathVariable("characterId") Integer characterId){
        try {
            characterServices.delete(characterId);
            return new ResponseEntity<String>("Character deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}