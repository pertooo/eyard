package prt.navitruck.back.app.repository.truck;

import org.springframework.data.jpa.repository.Query;
import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.model.entity.truck.TruckParams;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

import java.util.List;

public interface TruckParamsRepository extends AbstractRepository<TruckParams, Long> {

    @Query("select new prt.navitruck.back.app.model.dto.ComboDTO(t.id, t.paramName) from TruckParams t")
    List<ComboDTO> combo();


}
