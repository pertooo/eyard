package prt.navitruck.back.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.RequestParam;
import prt.navitruck.back.app.utils.Constants;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TruckDTO {

    private String serialNum;
    private int make;
    private int model;
    private Constants.BodyType bodyType;
    private Constants.TruckType truckType;
    private int year;
    private int truckParam;
    private String paramName;
    private boolean newParam;
    private int width;
    private int height;
    private int length;
    private int maxWeight;

}
