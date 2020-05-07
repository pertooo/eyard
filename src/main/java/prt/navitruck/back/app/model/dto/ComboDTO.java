package prt.navitruck.back.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import prt.navitruck.back.app.service.ComboService;
import prt.navitruck.back.app.utils.Constants;

@AllArgsConstructor
@Getter
public class ComboDTO {

    private long key;
    private String name;

    public ComboDTO(String name){
        this.name = name;
    }
}
