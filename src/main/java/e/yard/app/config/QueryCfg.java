package e.yard.app.config;


import e.yard.app.utils.Constants;
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
