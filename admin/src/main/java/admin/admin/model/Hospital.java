package admin.admin.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "hospitals")
public class Hospital {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
    private Long id;
	
	@NotBlank(message="Field must not be empty")
    @Size(min=2, max=20)
    @Pattern(regexp="^$|[a-zA-Z ]+$", message="Field must not include special characters.")
	@Column
    private String name;
	

}
