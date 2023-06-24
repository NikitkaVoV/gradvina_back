package ru.nikitavov.analitics.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nikitavov.analitics.database.model.Point;
import ru.nikitavov.analitics.database.model.dto.*;
import ru.nikitavov.analitics.database.model.Field;
import ru.nikitavov.analitics.database.model.Region;
import ru.nikitavov.analitics.database.repository.FieldRepository;
import ru.nikitavov.analitics.database.repository.RegionRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("fields")
public class FieldController {

    private final FieldRepository fieldRepository;
    private final RegionRepository regionRepository;

    @GetMapping
    public ResponseEntity<List<Field>> all() {
        return ResponseEntity.ok(fieldRepository.findAll());
    }

    @GetMapping("list")
    public ResponseEntity<List<FieldDto>> allFields() {
        List<FieldDto> groupDtos = new ArrayList<>();
        for (Field field : fieldRepository.findAll()) {
            List<RegionDto> regionDtos = new ArrayList<>();
            for (Region region : field.getRegions()) {
                String color = region.getVariety().getColor();
                RegionDto layerDto = new RegionDto(region.getId(), getPoints(region.getPoints()), color);
                regionDtos.add(layerDto);
            }
            FieldDto groupDto = new FieldDto(field.getId(), getPoints(field.getPoints()), regionDtos);
            groupDtos.add(groupDto);
        }

        return ResponseEntity.ok(groupDtos);
    }

    public List<PointDto> getPoints(List<Point> points) {
        List<PointDto> pointDtos = new ArrayList<>();
        for (Point point : points) {
            PointDto pointDto = new PointDto(point.getId(), point.getLon(), point.getLat());
            pointDtos.add(pointDto);
        }

        return pointDtos;
    }

    @PostMapping("add")
    public ResponseEntity<String> addField(@RequestBody List<List<BigDecimal>> pointsRow) {
        List<Point> points = new ArrayList<>();
        for (List<BigDecimal> values : pointsRow) {
            Point point = Point.builder().lon(values.get(0)).lat(values.get(1)).build();
            points.add(point);
        }
        Field field = Field.builder().points(points).build();
        fieldRepository.save(field);
        return ResponseEntity.ok("Поле добавлено");
    }

    @PostMapping("regin/add/{id}")
    public ResponseEntity<String> addRegion(@RequestBody List<List<BigDecimal>> pointsRow, @PathVariable int id) {
        List<Point> points = new ArrayList<>();
        for (List<BigDecimal> values : pointsRow) {
            Point point = Point.builder().lon(values.get(0)).lat(values.get(1)).build();
            points.add(point);
        }
        Region region = Region.builder().points(points).field(fieldRepository.findById(id).get()).build();
        regionRepository.save(region);
        return ResponseEntity.ok("Регион добавлен");
    }
}
