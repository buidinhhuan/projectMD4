package project.dto;

import org.springframework.validation.Errors;

import java.util.Objects;

public class FormLoginDto {
    private String username;
    private String password;

    public FormLoginDto() {
    }

    public FormLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
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
    public  void  checkValidate(Errors errors){
        // kiểm tra trương user nam co để trống hay không
        if (this.username.trim().equals("")){
            errors.rejectValue("username","username.empty");
        }
    }
}
