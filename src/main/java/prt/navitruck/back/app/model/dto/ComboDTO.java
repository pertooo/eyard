package prt.navitruck.back.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@AllArgsConstructor
@Getter
public class ComboDTO {

    private long key;
    private String name;


}
