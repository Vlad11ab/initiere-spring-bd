package com.example.initierespringbd.cli;

import java.util.Arrays;
import java.util.Optional;

public enum MenuOption {
    VIEW_ALL("1", "Afiseaza toti utilizatorii"),
    SEARCH_EMAIL("2", "Cauta dupa email"),
    SEARCH_QUERY("3", "Cautare generala (nume/email/telefon)"),
    ADD_USER("4", "Adauga utilizator"),
    UPDATE_USER("5", "Actualizeaza utilizator"),
    DELETE_USER("6", "Sterge utilizator"),
    EXIT("0", "Iesire");

    private final String code;
    private final String description;

    MenuOption(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String code() {
        return code;
    }

    public String description() {
        return description;
    }

    public static Optional<MenuOption> fromCode(String code) {
        return Arrays.stream(values())
                .filter(option -> option.code.equals(code))
                .findFirst();
    }
}
