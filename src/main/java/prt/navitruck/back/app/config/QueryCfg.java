package prt.navitruck.back.app.config;


import prt.navitruck.back.app.utils.Constants;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class QueryCfg {
    private String field;
    private Constants.QueryOperation operation;
    private Constants.QueryOperation nextOperation;
}
