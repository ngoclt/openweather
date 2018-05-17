package life.coder.openweather.api.model;

public class OWCity {
    private long id;
    private String name;
    private OWLocation coord;
    private String country;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OWLocation getCoord() {
        return coord;
    }

    public String getCountry() {
        return country;
    }
}
