package prt.navitruck.back.app.model.dto;

import prt.navitruck.back.app.utils.Constants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO<T> {
    private boolean success;
    private T content;
    private long count;
    private Constants.ErrorObj errorObj;
    private List<String> errors;

    public void setContent(T content) {
        this.success = true;
        this.content = content;
    }


    public static class ResponseDTOBuilder<T> {

        public ResponseDTOBuilder content(T content) {
            this.success = true;
            this.content = content;
            return this;
        }

        public ResponseDTOBuilder errorObj(Constants.ErrorObj errorObj) {
            this.success = false;
            this.errorObj = errorObj;
            return this;
        }

        public ResponseDTOBuilder errors(List<String> errors) {
            this.success = false;
            this.errors = errors;
            return this;
        }

    }

}
