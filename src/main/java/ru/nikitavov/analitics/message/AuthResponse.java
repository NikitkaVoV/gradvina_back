package ru.nikitavov.analitics.message;

public record AuthResponse(String token, boolean ok, int code) {
}
