package ru.qnocks.domain;

import lombok.*;
import ru.qnocks.domain.enums.District;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "full_name")
    @NotBlank(message = "Patient's full name cannot be blank")
    @NonNull
    private String fullName;

    @Column(name = "birth_date")
    @NotNull(message = "This field cannot be null")
    @Pattern(regexp = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d$",
            message = "Patient's birthdate should match pattern dd.mm.yyyy")
    @NonNull
    private String birthDate;

    @Column(name = "district")
    @Enumerated(EnumType.STRING)
    @NonNull
    private District district;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private List<Observation> observations;

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