package prt.navitruck.back.app.service;

import prt.navitruck.back.app.model.dto.ComboDTO;

import java.util.List;

public interface ComboService {
    List<ComboDTO> getComboByKey(String key);
    List<ComboDTO> getComboByKeyAndParentId(String key, long parentId);
    List<ComboDTO> getComboTruckType();
    List<ComboDTO> getComboBodyType();

}
