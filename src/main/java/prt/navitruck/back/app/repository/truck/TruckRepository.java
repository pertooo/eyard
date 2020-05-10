package prt.navitruck.back.app.repository.truck;

import org.springframework.data.jpa.repository.Query;
import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.model.entity.truck.Truck;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

import java.util.List;

public interface TruckRepository extends AbstractRepository<Truck, Long> {
    @Query("select new prt.navitruck.back.app.model.dto.ComboDTO(t.id, t.make.objValue) from Truck t")
    List<ComboDTO> getCombo();
}
