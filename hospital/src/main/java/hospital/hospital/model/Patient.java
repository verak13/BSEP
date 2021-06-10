package hospital.hospital.model;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

import hospital.hospital.enums.BloodType;
import hospital.hospital.enums.Gender;

@Entity
@Table(name = "patients")
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@ColumnTransformer(forColumn = "jmbg", 
			read = "pgp_sym_decrypt(jmbg, 'secret')", 
			write = "pgp_sym_encrypt(?, 'secret'")
			/*read="AES_DECRYPT(UNHEX(jmbg), UNHEX(SHA2('secret', 512)))",
			write="HEX(AES_ENCRYPT(?, UNHEX(SHA2('secret', 512))))")*/
	@Column(unique = true/*, columnDefinition = "bytea"*/)
	private String jmbg;
	
	@Column
	@ColumnTransformer(forColumn = "first_name", 
			read = "pgp_sym_decrypt(first_name, 'secret')", 
			write = "pgp_sym_encrypt(?, 'secret'")
	private String firstName;
	
	@Column
	@ColumnTransformer(forColumn = "last_name", 
			read = "pgp_sym_decrypt(last_name, 'secret')", 
			write = "pgp_sym_encrypt(?, 'secret'")
	private String lastName;
	
	@Column
	private Date birthDate;
	
	@Column
	private double height;
	
	@Column
	private double weight;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column	
	@Enumerated(EnumType.STRING)
	private BloodType bloodType;
	
	public Patient() {}

	public Patient(Long id, String jmbg, String firstName, String lastName, Date birthDate, double height,
			double weight, Gender gender, BloodType bloodType) {
		super();
		this.id = id;
		this.jmbg = jmbg;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
		this.height = height;
		this.weight = weight;
		this.gender = gender;
		this.bloodType = bloodType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getJmbg() {
		return jmbg;
	}
	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public BloodType getBloodType() {
		return bloodType;
	}
	public void setBloodType(BloodType bloodType) {
		this.bloodType = bloodType;
	}

}
