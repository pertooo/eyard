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
@NoArgsConstructor
@Entity
@Table(name = "dictionary")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Dictionary extends AbstractEntity {

    @Column(name = "OBJ_KEY", nullable = false)
    private String objKey;

    @Column(name = "OBJ_PARENT_ID")
    private long objParentID;

    @Column(name = "OBJ_PARENT_KEY")
    private String objParentKey;

    @Column(name = "OBJ_VALUE")
    private String objValue;

    @Column(name = "ROW_NUM")
    private int rowNum;


}
