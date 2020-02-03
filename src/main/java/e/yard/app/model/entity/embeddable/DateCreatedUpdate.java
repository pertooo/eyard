package e.yard.app.model.entity.embeddable;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class DateCreatedUpdate {

    @Column(name = "date_created")
    private LocalDateTime created;

    @Column(name = "date_updated")
    private LocalDateTime updated;
}
