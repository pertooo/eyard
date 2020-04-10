package prt.navitruck.back.app.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import prt.navitruck.back.app.model.entity.abstr.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@AllArgsConstructor
@Entity
@Table(name = "dictionary")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dictionary extends AbstractEntity {

    @Column(name = "OPERATION_KEY", nullable = false)
    private String opKey;

    @Column(name = "OPERATION_VALUE_ENG")
    private String opValueEng;

    @Column(name = "OPERATION_VALUE_GEO")
    private String opValueGeo;

    @Column(name = "ROW_NUM")
    private int rowNum;

    public Dictionary() {
    }
}
