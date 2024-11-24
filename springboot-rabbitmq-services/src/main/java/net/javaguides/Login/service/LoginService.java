package net.javaguides.Login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.javaguides.Login.dto.LoginDTO;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

@Service
public class LoginService {

    private static final String LOGIN_FILE = "login.json";
    private final ObjectMapper objectMapper = new ObjectMapper();

    public void saveLogin(LoginDTO loginDTO) {
        try {
            objectMapper.writeValue(new File(LOGIN_FILE), loginDTO);
        } catch (IOException e) {
            throw new RuntimeException("Error al guardar el login en JSON", e);
        }
    }

    public Optional<LoginDTO> getLogin() {
        try {
            File file = new File(LOGIN_FILE);
            if (file.exists()) {
                return Optional.of(objectMapper.readValue(file, LoginDTO.class));
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el login desde JSON", e);
        }
    }
}
