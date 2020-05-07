package prt.navitruck.back.app.controller.truck;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import prt.navitruck.back.app.controller.abstr.AbstractController;
import prt.navitruck.back.app.model.entity.task.Task;
import prt.navitruck.back.app.model.entity.truck.Truck;
import prt.navitruck.back.app.model.entity.truck.TruckParams;
import prt.navitruck.back.app.repository.abstr.AbstractRepository;

@RestController
@RequestMapping("/truck")
public class TruckController extends AbstractController<Truck, Long> {

    public TruckController(AbstractRepository<Truck, Long> repository) {
        super(repository);
    }
}
