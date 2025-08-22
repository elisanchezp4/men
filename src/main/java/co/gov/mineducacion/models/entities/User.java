package co.gov.mineducacion.models.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "identification_number", length = 50, unique = true)
    private String identificationNumber;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "second_name", length = 50)
    private String secondName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "second_last_name", length = 50)
    private String secondLastName;

    @Column(name = "email", unique = true, length = 50)
    private String email;

    @Column(name = "password_hash", length = 2000)
    private String passwordHash;

    @Column(name = "status", length = 10)
    private String status;

    @Column(name = "is_migrated")
    private Boolean isMigrated;

    @Column(name = "password_changed_at")
    private LocalDateTime passwordChangedAt;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_user")
    private String updatedUser;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Audit> audits;
    @PrePersist
    private void beforePersist(){
        this.createdDate = LocalDateTime.now();
    }
    @PreUpdate
    private void beforeUpdate(){
        this.updatedDate = LocalDateTime.now();
        this.passwordChangedAt = LocalDateTime.now();
    }
}
