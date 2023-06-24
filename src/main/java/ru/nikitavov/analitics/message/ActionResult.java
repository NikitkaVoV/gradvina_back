package ru.nikitavov.analitics.message;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ActionResult {
    private Long id;
    private String code;
    private String field;
}
