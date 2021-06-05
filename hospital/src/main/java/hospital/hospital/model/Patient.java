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

import hospital.hospital.enums.BloodType;
import hospital.hospital.enums.Gender;

@Entity
@Table(name = "patients")
public class Patient {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;
	
	@Column(unique = true)
	private String jmbg;
	@Column
	private String firstName;
	@Column
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