package ru.nikitavov.analitics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikitavov.analitics.database.model.Region;
import ru.nikitavov.analitics.database.repository.RegionRepository;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("regions")
public class RegionController {

    private final RegionRepository regionRepository;


    @GetMapping("info/{id}")
    public ResponseEntity<Region> getRegionInfo(@PathVariable int id) {
        return ResponseEntity.ok(regionRepository.findById(id).get());
    }
}
