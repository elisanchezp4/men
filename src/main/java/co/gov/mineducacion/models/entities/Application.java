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
@Entity(name = "applications")
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "code_type", unique = true, length = 10)
    private String codeType;

    @Column(name = "application_name", length = 50)
    private String applicationName;

    @Column(name = "description", length = 1000)
    private String description;

    @Column(name = "url_callback", length = 500)
    private String urlCallback;

    @Column(name = "mfa_enable")
    private Boolean mfaEnable;

    @Column(name = "created_user")
    private String createdUser;

    @Column(name = "created_date")
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
