package prt.navitruck.back.app.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import prt.navitruck.back.app.model.dto.ComboDTO;
import prt.navitruck.back.app.repository.DictionaryRepository;
import prt.navitruck.back.app.repository.truck.TruckParamsRepository;
import prt.navitruck.back.app.service.ComboService;
import prt.navitruck.back.app.utils.Constants;

import java.util.ArrayList;
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

    @Override
    public List<ComboDTO> getComboByKeyAndParentId(String key, long parentId) {
        return dictionaryRepository.getComboByKeyAndParentId(key, parentId);
    }

    @Override
    public List<ComboDTO> getComboTruckType() {
        List<ComboDTO> list = new ArrayList<>();
        for (Constants.TruckType str : Constants.TruckType.values()) {
            list.add(new ComboDTO(str.name()));
        }
        return list;
    }

    @Override
    public List<ComboDTO> getComboBodyType() {
        List<ComboDTO> list = new ArrayList<>();
        for (Constants.BodyType str : Constants.BodyType.values()) {
            list.add(new ComboDTO(str.name()));
        }
        return list;
    }
}
