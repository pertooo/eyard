package prt.navitruck.back.app.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.repository.DictionaryRepository;
import prt.navitruck.back.app.repository.truck.TruckParamsRepository;
import prt.navitruck.back.app.service.ComboService;

import java.util.List;

@Service
public class ComboServiceImpl implements ComboService {

    @Autowired
    TruckParamsRepository truckParamsRepository;

    @Autowired
    DictionaryRepository dictionaryRepository;

    @Override
    public List<ComboDTO>  getComboByKey(String key) {
        return dictionaryRepository.getComboByKey(key);
    }
}
