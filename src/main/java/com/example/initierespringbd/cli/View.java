package com.example.initierespringbd.cli;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserResponse;
import com.example.initierespringbd.dtos.UserUpdateRequest;
import com.example.initierespringbd.exception.EmailAlreadyUsedException;
import com.example.initierespringbd.exception.UserNotFoundException;
import com.example.initierespringbd.service.command.UserCommandService;
import com.example.initierespringbd.service.query.UserQueryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class View implements CommandLineRunner {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;
    private final ConsoleInputReader inputReader;
    private final MenuPrinter menuPrinter;

    public View(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
        this.inputReader = new ConsoleInputReader();
        this.menuPrinter = new MenuPrinter();
    }

    @Override
    public void run(String... args) {
        boolean running = true;
        System.out.println("=== Meniu Gestionare Utilizatori ===");
        while (running) {
            menuPrinter.print();
            String optionValue = inputReader.readLine("Optiune: ");
            Optional<MenuOption> option = MenuOption.fromCode(optionValue);
            if (option.isEmpty()) {
                System.out.println("Optiune invalida. Incercati din nou.");
                continue;
            }
            running = handleOption(option.get());
        }
    }

    private boolean handleOption(MenuOption option) {
        switch (option) {
            case VIEW_ALL -> viewAllUsers();
            case SEARCH_EMAIL -> searchByEmail();
            case SEARCH_QUERY -> searchByQuery();
            case ADD_USER -> addUser();
            case UPDATE_USER -> updateUser();
            case DELETE_USER -> deleteUser();
            case EXIT -> {
                System.out.println("La revedere!");
                return false;
            }
        }
        return true;
    }

    private void viewAllUsers() {
        List<UserResponse> users = userQueryService.findAllUsers();
        if (users.isEmpty()) {
            System.out.println("Nu exista utilizatori inregistrati.");
            return;
        }
        users.forEach(System.out::println);
    }

    private void searchByEmail() {
        String email = inputReader.readRequiredString("Email: ");
        Optional<UserResponse> user = userQueryService.findByEmailIgnoreCase(email);
        user.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Nu s-a gasit niciun utilizator cu emailul dat.")
        );
    }

    private void searchByQuery() {
        String query = inputReader.readRequiredString("Criteriu cautare: ");
        int page = inputReader.readNonNegativeInt("Pagina (>=0): ");
        int size = inputReader.readPositiveInt("Dimensiune pagina (>0): ");
        Page<UserResponse> result = userQueryService.search(query, PageRequest.of(page, size));
        if (result.isEmpty()) {
            System.out.println("Nu exista rezultate pentru cautarea introdusa.");
            return;
        }
        result.forEach(System.out::println);
    }

    private void addUser() {
        String firstName = inputReader.readRequiredString("Prenume: ");
        String lastName = inputReader.readRequiredString("Nume: ");
        String email = inputReader.readRequiredString("Email: ");
        int age = inputReader.readPositiveInt("Varsta (>0): ");
        LocalDate hireDate = inputReader.readDate("Data angajarii (yyyy-MM-dd): ");
        String phone = inputReader.readRequiredString("Telefon: ");
        String password = inputReader.readRequiredString("Parola: ");

        UserCreateRequest request = new UserCreateRequest(firstName, lastName, email, age, hireDate, phone, password);
        try {
            UserResponse response = userCommandService.create(request);
            System.out.println("Utilizator creat: " + response);
        } catch (EmailAlreadyUsedException e) {
            System.out.println(e.getMessage());
        }
    }

    private void updateUser() {
        long userId = inputReader.readLong("ID utilizator: ");
        Integer newAge = inputReader.readOptionalInt("Noua varsta (enter pentru a sari): ");
        String newEmail = inputReader.readOptionalString("Email nou (enter pentru a sari): ");
        String newPassword = inputReader.readOptionalString("Parola noua (enter pentru a sari): ");

        int ageValue = newAge != null ? newAge : 0;
        UserUpdateRequest request = new UserUpdateRequest(ageValue, newEmail, newPassword);
        try {
            UserResponse response = userCommandService.update(userId, request);
            System.out.println("Utilizator actualizat: " + response);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteUser() {
        long userId = inputReader.readLong("ID utilizator de sters: ");
        try {
            userCommandService.delete(userId);
            System.out.println("Utilizator sters cu succes.");
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
