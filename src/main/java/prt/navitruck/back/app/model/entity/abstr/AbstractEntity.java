package prt.navitruck.back.app.model.entity.abstr;

import com.fasterxml.jackson.annotation.JsonIgnore;
import prt.navitruck.back.app.model.entity.embeddable.DateCreatedUpdate;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    protected Long id;

    @Embedded
    @JsonIgnore
    private DateCreatedUpdate timestamp;

    @PrePersist
    void onCreate() {
        this.setTimestamp(DateCreatedUpdate.builder().created(LocalDateTime.now()).build());
    }

    @PreUpdate
    public void onPersist() {
        if (timestamp != null) {
            this.getTimestamp().setUpdated(LocalDateTime.now());
        }
    }
}
