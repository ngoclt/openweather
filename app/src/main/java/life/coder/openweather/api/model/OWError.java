package life.coder.openweather.api.model;

import com.google.gson.annotations.SerializedName;

public class OWError {
    @SerializedName("cod")
    private int cod;
    @SerializedName("message")
    private String message;

    public int getCod() {
        return cod;
    }

    public String getMessage() {
        return message;
    }
}
