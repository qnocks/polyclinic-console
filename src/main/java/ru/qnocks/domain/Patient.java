package ru.qnocks.domain;

import ru.qnocks.enums.District;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    @NotBlank(message = "Patient's full name cannot be blank")
    private String fullName;

    @Column(name = "birth_date")
    @NotNull(message = "This field cannot be null")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$",
            message = "Patient's birthdate should match pattern dd.mm.yyyy")
    private String birthDate;

    @Column(name = "district")
    @Enumerated(EnumType.STRING)
    private District district;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private List<Observation> observations;

    public Patient() {
    }

    public Patient(String fullName, String birthDate, District district) {
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.district = district;
    }

    public Patient(Long id, String fullName, String birthDate, District district) {
        this.id = id;
        this.fullName = fullName;
        this.birthDate = birthDate;
        this.district = district;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", district='" + district.name() + '\'' +
                '}';
    }
}