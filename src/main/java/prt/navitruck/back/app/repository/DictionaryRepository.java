package prt.navitruck.back.app.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.model.entity.Dictionary;
import prt.navitruck.back.app.model.entity.User;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

import java.util.List;

public interface DictionaryRepository extends AbstractRepository<Dictionary, Long> {

    @Query("select new prt.navitruck.back.app.model.dto.ComboDTO(d.id, d.objValue) from Dictionary d" +
            " where d.objKey = :key")
    List<ComboDTO> getComboByKey(@Param("key") String key);

    @Query("select new prt.navitruck.back.app.model.dto.ComboDTO(d.id, d.objValue) from Dictionary d" +
            " where d.objParentID = :parentId and d.objKey = :key")
    List<ComboDTO> getComboByKeyAndParentId(@Param("key") String key,
                                            @Param("parentId") long parentId);
}
