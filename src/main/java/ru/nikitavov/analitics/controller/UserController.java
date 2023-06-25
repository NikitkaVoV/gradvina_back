package ru.nikitavov.analitics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikitavov.analitics.database.model.User;
import ru.nikitavov.analitics.database.repository.UserRepository;

import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("users")
public class UserController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> all() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("me/{id}")
    public ResponseEntity<User> me(@PathVariable int id) {
        return ResponseEntity.ok(userRepository.findById(id).get());
    }
}
