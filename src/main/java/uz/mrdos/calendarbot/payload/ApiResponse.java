package uz.mrdos.calendarbot.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {
    private String message;
    private boolean success;

    @JsonProperty("data")
    private Object object;

    public ApiResponse(String message, boolean success){
        this.message = message;
        this.success = success;
    }
}
