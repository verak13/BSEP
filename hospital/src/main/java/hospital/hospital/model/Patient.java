package hospital.hospital.model;


import java.util.Date;

import javax.persistence.*;

import hospital.hospital.helper.AttributeEncryptor;
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

	@Convert(converter = AttributeEncryptor.class)
	@Column(unique = true, name="jmbg")
	private String jmbg;


	@Convert(converter = AttributeEncryptor.class)
	@Column(name = "first_name")
	private String firstName;


	@Convert(converter = AttributeEncryptor.class)
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "birth_date")
	private Date birthDate;
	
	@Column(name = "height")
	private double height;
	
	@Column(name = "weight")
	private double weight;
	
	@Column(name = "gender")
	@Enumerated(EnumType.STRING)
	private Gender gender;
	
	@Column(name = "blood_type")
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
