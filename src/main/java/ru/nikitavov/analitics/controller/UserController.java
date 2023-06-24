package ru.nikitavov.analitics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("user")
public class UserController {

    @PostMapping("login")
    public boolean login(@RequestBody String login, @RequestBody String password) {
        return true;
    }

    @PostMapping("reg")
    public boolean reg(@RequestBody String login, @RequestBody String password, @RequestBody String name) {
        return true;
    }

    @GetMapping("me/{id}")
    public String me(@PathVariable String id) {
        return "";
    }
}
