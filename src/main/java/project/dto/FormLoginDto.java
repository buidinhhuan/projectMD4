package project.dto;

import org.springframework.validation.Errors;

import java.util.Objects;

public class FormLoginDto {
    private Long id;
    private String username;
    private String password;

    public FormLoginDto() {
    }

    public FormLoginDto(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
