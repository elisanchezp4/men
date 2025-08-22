package co.gov.mineducacion.models.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name",length = 50)
    private String name;

    @Column(name = "code",unique = true, length = 50)
    private String code;

    @Column(name = "revocation_reason",length = 500)
    private String revocationReason;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_date", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_user")
    private String updatedUser;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;
    @PrePersist
    private void beforePersist(){
        this.createdDate = LocalDateTime.now();
    }
    @PreUpdate
    private void beforeUpdate(){
        this.updatedDate = LocalDateTime.now();
    }
}
