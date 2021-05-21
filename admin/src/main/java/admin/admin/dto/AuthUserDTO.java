package admin.admin.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AuthUserDTO {

    @NotBlank(message="Username must not be empty")
    @Size(min=2, max=20)
    @Email(message="Email must be valid")
    private String username;

    @NotBlank(message="Password must not be empty")
    //@Size(min=2, max=20)
    //@Pattern(regexp="^$|[a-zA-Z ]+$", message="Username must not include special characters.")
    private String password;

    public AuthUserDTO(){}

    public AuthUserDTO(String user, String pw){
        this.username = user;
        this.password = pw;
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
