package ru.nikitavov.analitics.message;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public record SignUpRequest(String name, String login, String password, String phone) {
}
