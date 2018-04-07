package life.coder.openweather.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by ngocle on 11/11/17.
 */

public class OWForecast {


    /**
     * cod : 200
     * message : 0.0023
     * cnt : 40
     * list : [{"dt":1521806400,"main":{"temp":-1.7,"temp_min":-1.7,"temp_max":-1.27,"pressure":1018.46,"sea_level":1018.85,"grnd_level":1018.46,"humidity":100,"temp_kf":-0.44},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":12},"wind":{"speed":4.03,"deg":332.502},"snow":{"3h":0.0015},"sys":{"pod":"d"},"dt_txt":"2018-03-23 12:00:00"},{"dt":1521817200,"main":{"temp":-0.94,"temp_min":-0.94,"temp_max":-0.61,"pressure":1018.92,"sea_level":1019.36,"grnd_level":1018.92,"humidity":98,"temp_kf":-0.33},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.36,"deg":348.001},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-23 15:00:00"},{"dt":1521828000,"main":{"temp":-2.19,"temp_min":-2.19,"temp_max":-1.98,"pressure":1020,"sea_level":1020.38,"grnd_level":1020,"humidity":96,"temp_kf":-0.22},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":0.46,"deg":271.002},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-23 18:00:00"},{"dt":1521838800,"main":{"temp":-3.32,"temp_min":-3.32,"temp_max":-3.21,"pressure":1020.16,"sea_level":1020.67,"grnd_level":1020.16,"humidity":95,"temp_kf":-0.11},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"02n"}],"clouds":{"all":8},"wind":{"speed":1.86,"deg":248},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-23 21:00:00"},{"dt":1521849600,"main":{"temp":-3.62,"temp_min":-3.62,"temp_max":-3.62,"pressure":1020.01,"sea_level":1020.5,"grnd_level":1020.01,"humidity":98,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02n"}],"clouds":{"all":20},"wind":{"speed":3.25,"deg":231},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-24 00:00:00"},{"dt":1521860400,"main":{"temp":-2.95,"temp_min":-2.95,"temp_max":-2.95,"pressure":1019.59,"sea_level":1020.01,"grnd_level":1019.59,"humidity":96,"temp_kf":0},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04n"}],"clouds":{"all":64},"wind":{"speed":5.56,"deg":218.5},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-24 03:00:00"},{"dt":1521871200,"main":{"temp":-2.61,"temp_min":-2.61,"temp_max":-2.61,"pressure":1018.83,"sea_level":1019.2,"grnd_level":1018.83,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":48},"wind":{"speed":8.01,"deg":211.001},"snow":{"3h":0.001},"sys":{"pod":"d"},"dt_txt":"2018-03-24 06:00:00"},{"dt":1521882000,"main":{"temp":-1.31,"temp_min":-1.31,"temp_max":-1.31,"pressure":1017.73,"sea_level":1018.07,"grnd_level":1017.73,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":68},"wind":{"speed":9.96,"deg":215.502},"snow":{"3h":5.0E-4},"sys":{"pod":"d"},"dt_txt":"2018-03-24 09:00:00"},{"dt":1521892800,"main":{"temp":0.21,"temp_min":0.21,"temp_max":0.21,"pressure":1016.07,"sea_level":1016.43,"grnd_level":1016.07,"humidity":97,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}],"clouds":{"all":36},"wind":{"speed":11.37,"deg":222.006},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-24 12:00:00"},{"dt":1521903600,"main":{"temp":0.51,"temp_min":0.51,"temp_max":0.51,"pressure":1014.89,"sea_level":1015.21,"grnd_level":1014.89,"humidity":95,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":88},"wind":{"speed":12.12,"deg":230.501},"snow":{"3h":0.01625},"sys":{"pod":"d"},"dt_txt":"2018-03-24 15:00:00"},{"dt":1521914400,"main":{"temp":0.42,"temp_min":0.42,"temp_max":0.42,"pressure":1013.65,"sea_level":1013.95,"grnd_level":1013.65,"humidity":95,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13n"}],"clouds":{"all":92},"wind":{"speed":12.12,"deg":235.02},"snow":{"3h":0.34125},"sys":{"pod":"n"},"dt_txt":"2018-03-24 18:00:00"},{"dt":1521925200,"main":{"temp":0.53,"temp_min":0.53,"temp_max":0.53,"pressure":1012.94,"sea_level":1013.28,"grnd_level":1012.94,"humidity":94,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13n"}],"clouds":{"all":88},"wind":{"speed":10.16,"deg":248.001},"snow":{"3h":0.29},"sys":{"pod":"n"},"dt_txt":"2018-03-24 21:00:00"},{"dt":1521936000,"main":{"temp":0.65,"temp_min":0.65,"temp_max":0.65,"pressure":1012.21,"sea_level":1012.6,"grnd_level":1012.21,"humidity":93,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13n"}],"clouds":{"all":88},"wind":{"speed":8.86,"deg":247.506},"snow":{"3h":0.095},"sys":{"pod":"n"},"dt_txt":"2018-03-25 00:00:00"},{"dt":1521946800,"main":{"temp":0.79,"temp_min":0.79,"temp_max":0.79,"pressure":1011.41,"sea_level":1011.81,"grnd_level":1011.41,"humidity":92,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":80},"wind":{"speed":8.76,"deg":245.004},"snow":{"3h":0.0175},"sys":{"pod":"n"},"dt_txt":"2018-03-25 03:00:00"},{"dt":1521957600,"main":{"temp":0.78,"temp_min":0.78,"temp_max":0.78,"pressure":1011.1,"sea_level":1011.47,"grnd_level":1011.1,"humidity":95,"temp_kf":0},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"clouds":{"all":64},"wind":{"speed":8.67,"deg":245.5},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-25 06:00:00"},{"dt":1521968400,"main":{"temp":0.95,"temp_min":0.95,"temp_max":0.95,"pressure":1011.13,"sea_level":1011.53,"grnd_level":1011.13,"humidity":94,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":88},"wind":{"speed":9.03,"deg":241.004},"snow":{"3h":0.005},"sys":{"pod":"d"},"dt_txt":"2018-03-25 09:00:00"},{"dt":1521979200,"main":{"temp":0.88,"temp_min":0.88,"temp_max":0.88,"pressure":1011.11,"sea_level":1011.43,"grnd_level":1011.11,"humidity":94,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":{"all":92},"wind":{"speed":9.38,"deg":240.5},"rain":{"3h":0.03},"snow":{"3h":0.035},"sys":{"pod":"d"},"dt_txt":"2018-03-25 12:00:00"},{"dt":1521990000,"main":{"temp":0.74,"temp_min":0.74,"temp_max":0.74,"pressure":1010.51,"sea_level":1010.92,"grnd_level":1010.51,"humidity":95,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":{"all":92},"wind":{"speed":9.25,"deg":241.001},"rain":{"3h":0.025},"snow":{"3h":0.1775},"sys":{"pod":"d"},"dt_txt":"2018-03-25 15:00:00"},{"dt":1522000800,"main":{"temp":0.63,"temp_min":0.63,"temp_max":0.63,"pressure":1010.09,"sea_level":1010.49,"grnd_level":1010.09,"humidity":96,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13n"}],"clouds":{"all":92},"wind":{"speed":8.36,"deg":245.001},"rain":{},"snow":{"3h":0.31},"sys":{"pod":"n"},"dt_txt":"2018-03-25 18:00:00"},{"dt":1522011600,"main":{"temp":0.64,"temp_min":0.64,"temp_max":0.64,"pressure":1010.04,"sea_level":1010.34,"grnd_level":1010.04,"humidity":96,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13n"}],"clouds":{"all":92},"wind":{"speed":6.96,"deg":252.004},"rain":{},"snow":{"3h":0.3775},"sys":{"pod":"n"},"dt_txt":"2018-03-25 21:00:00"},{"dt":1522022400,"main":{"temp":0.64,"temp_min":0.64,"temp_max":0.64,"pressure":1010.36,"sea_level":1010.77,"grnd_level":1010.36,"humidity":93,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10n"}],"clouds":{"all":88},"wind":{"speed":4.75,"deg":255.505},"rain":{"3h":0.005},"snow":{"3h":0.0825},"sys":{"pod":"n"},"dt_txt":"2018-03-26 00:00:00"},{"dt":1522033200,"main":{"temp":0.18,"temp_min":0.18,"temp_max":0.18,"pressure":1011.07,"sea_level":1011.43,"grnd_level":1011.07,"humidity":97,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10n"}],"clouds":{"all":76},"wind":{"speed":3.66,"deg":254.5},"rain":{"3h":0.02},"snow":{"3h":0.0975},"sys":{"pod":"n"},"dt_txt":"2018-03-26 03:00:00"},{"dt":1522044000,"main":{"temp":-0.3,"temp_min":-0.3,"temp_max":-0.3,"pressure":1012.81,"sea_level":1013.34,"grnd_level":1012.81,"humidity":100,"temp_kf":0},"weather":[{"id":500,"main":"Rain","description":"light rain","icon":"10d"}],"clouds":{"all":88},"wind":{"speed":6.28,"deg":12.0034},"rain":{"3h":0.01},"snow":{"3h":0.5575},"sys":{"pod":"d"},"dt_txt":"2018-03-26 06:00:00"},{"dt":1522054800,"main":{"temp":-1.87,"temp_min":-1.87,"temp_max":-1.87,"pressure":1016.01,"sea_level":1016.38,"grnd_level":1016.01,"humidity":100,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13d"}],"clouds":{"all":92},"wind":{"speed":8.72,"deg":28.501},"rain":{},"snow":{"3h":1.325},"sys":{"pod":"d"},"dt_txt":"2018-03-26 09:00:00"},{"dt":1522065600,"main":{"temp":-1.74,"temp_min":-1.74,"temp_max":-1.74,"pressure":1018.41,"sea_level":1018.74,"grnd_level":1018.41,"humidity":100,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13d"}],"clouds":{"all":80},"wind":{"speed":8.41,"deg":32.0005},"rain":{},"snow":{"3h":0.885},"sys":{"pod":"d"},"dt_txt":"2018-03-26 12:00:00"},{"dt":1522076400,"main":{"temp":-1.18,"temp_min":-1.18,"temp_max":-1.18,"pressure":1019.76,"sea_level":1020.2,"grnd_level":1019.76,"humidity":99,"temp_kf":0},"weather":[{"id":600,"main":"Snow","description":"light snow","icon":"13d"}],"clouds":{"all":68},"wind":{"speed":7.37,"deg":30.0001},"rain":{},"snow":{"3h":0.105},"sys":{"pod":"d"},"dt_txt":"2018-03-26 15:00:00"},{"dt":1522087200,"main":{"temp":-2.48,"temp_min":-2.48,"temp_max":-2.48,"pressure":1021.52,"sea_level":1021.88,"grnd_level":1021.52,"humidity":96,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":7.02,"deg":26.004},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-26 18:00:00"},{"dt":1522098000,"main":{"temp":-3.95,"temp_min":-3.95,"temp_max":-3.95,"pressure":1022.6,"sea_level":1023.02,"grnd_level":1022.6,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":5.61,"deg":25.003},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-26 21:00:00"},{"dt":1522108800,"main":{"temp":-4.88,"temp_min":-4.88,"temp_max":-4.88,"pressure":1023.39,"sea_level":1023.78,"grnd_level":1023.39,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":4.07,"deg":8.50043},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-27 00:00:00"},{"dt":1522119600,"main":{"temp":-4.95,"temp_min":-4.95,"temp_max":-4.95,"pressure":1023.37,"sea_level":1023.79,"grnd_level":1023.37,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":3.36,"deg":355.007},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-27 03:00:00"},{"dt":1522130400,"main":{"temp":-4.88,"temp_min":-4.88,"temp_max":-4.88,"pressure":1023.4,"sea_level":1023.8,"grnd_level":1023.4,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":3.23,"deg":344.501},"rain":{},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-27 06:00:00"},{"dt":1522141200,"main":{"temp":-3.15,"temp_min":-3.15,"temp_max":-3.15,"pressure":1023.38,"sea_level":1023.62,"grnd_level":1023.38,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.56,"deg":332.001},"rain":{},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-27 09:00:00"},{"dt":1522152000,"main":{"temp":-2.05,"temp_min":-2.05,"temp_max":-2.05,"pressure":1022.29,"sea_level":1022.68,"grnd_level":1022.29,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}],"clouds":{"all":0},"wind":{"speed":2.31,"deg":267.001},"rain":{},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-27 12:00:00"},{"dt":1522162800,"main":{"temp":-1.4,"temp_min":-1.4,"temp_max":-1.4,"pressure":1020.9,"sea_level":1021.25,"grnd_level":1020.9,"humidity":100,"temp_kf":0},"weather":[{"id":801,"main":"Clouds","description":"few clouds","icon":"02d"}],"clouds":{"all":12},"wind":{"speed":4.01,"deg":249.003},"rain":{},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-27 15:00:00"},{"dt":1522173600,"main":{"temp":-2.22,"temp_min":-2.22,"temp_max":-2.22,"pressure":1019.85,"sea_level":1020.31,"grnd_level":1019.85,"humidity":97,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":3.65,"deg":269.002},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-27 18:00:00"},{"dt":1522184400,"main":{"temp":-3.48,"temp_min":-3.48,"temp_max":-3.48,"pressure":1018.96,"sea_level":1019.37,"grnd_level":1018.96,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":3.16,"deg":301.001},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-27 21:00:00"},{"dt":1522195200,"main":{"temp":-3.95,"temp_min":-3.95,"temp_max":-3.95,"pressure":1017.99,"sea_level":1018.39,"grnd_level":1017.99,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":2.76,"deg":312.5},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-28 00:00:00"},{"dt":1522206000,"main":{"temp":-4.52,"temp_min":-4.52,"temp_max":-4.52,"pressure":1016.94,"sea_level":1017.4,"grnd_level":1016.94,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"01n"}],"clouds":{"all":0},"wind":{"speed":2.98,"deg":323.504},"rain":{},"snow":{},"sys":{"pod":"n"},"dt_txt":"2018-03-28 03:00:00"},{"dt":1522216800,"main":{"temp":-4.62,"temp_min":-4.62,"temp_max":-4.62,"pressure":1016.52,"sea_level":1016.96,"grnd_level":1016.52,"humidity":100,"temp_kf":0},"weather":[{"id":800,"main":"Clear","description":"clear sky","icon":"02d"}],"clouds":{"all":8},"wind":{"speed":3.06,"deg":344.501},"rain":{},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-28 06:00:00"},{"dt":1522227600,"main":{"temp":-4.13,"temp_min":-4.13,"temp_max":-4.13,"pressure":1016.75,"sea_level":1017.17,"grnd_level":1016.75,"humidity":100,"temp_kf":0},"weather":[{"id":802,"main":"Clouds","description":"scattered clouds","icon":"03d"}],"clouds":{"all":44},"wind":{"speed":3.91,"deg":14.0002},"rain":{},"snow":{},"sys":{"pod":"d"},"dt_txt":"2018-03-28 09:00:00"}]
     * city : {"id":658225,"name":"Helsinki","coord":{"lat":60.1695,"lon":24.9355},"country":"FI","population":558457}
     */

    private String cod;
    private double message;
    private int cnt;
    private CityBean city;
    private List<ListBean> list;

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public CityBean getCity() {
        return city;
    }

    public void setCity(CityBean city) {
        this.city = city;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class CityBean {
        /**
         * id : 658225
         * name : Helsinki
         * coord : {"lat":60.1695,"lon":24.9355}
         * country : FI
         * population : 558457
         */

        private int id;
        private String name;
        private CoordBean coord;
        private String country;
        private int population;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public CoordBean getCoord() {
            return coord;
        }

        public void setCoord(CoordBean coord) {
            this.coord = coord;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public static class CoordBean {
            /**
             * lat : 60.1695
             * lon : 24.9355
             */

            private double lat;
            private double lon;

            public double getLat() {
                return lat;
            }

            public void setLat(double lat) {
                this.lat = lat;
            }

            public double getLon() {
                return lon;
            }

            public void setLon(double lon) {
                this.lon = lon;
            }
        }
    }

    public static class ListBean {
        /**
         * dt : 1521806400
         * main : {"temp":-1.7,"temp_min":-1.7,"temp_max":-1.27,"pressure":1018.46,"sea_level":1018.85,"grnd_level":1018.46,"humidity":100,"temp_kf":-0.44}
         * weather : [{"id":800,"main":"Clear","description":"clear sky","icon":"01d"}]
         * clouds : {"all":12}
         * wind : {"speed":4.03,"deg":332.502}
         * snow : {"3h":0.0015}
         * sys : {"pod":"d"}
         * dt_txt : 2018-03-23 12:00:00
         * rain : {"3h":0.03}
         */

        private int dt;
        private MainBean main;
        private CloudsBean clouds;
        private WindBean wind;
        private SnowBean snow;
        private SysBean sys;
        private String dt_txt;
        private RainBean rain;
        private List<WeatherBean> weather;

        public int getDt() {
            return dt;
        }

        public void setDt(int dt) {
            this.dt = dt;
        }

        public MainBean getMain() {
            return main;
        }

        public void setMain(MainBean main) {
            this.main = main;
        }

        public CloudsBean getClouds() {
            return clouds;
        }

        public void setClouds(CloudsBean clouds) {
            this.clouds = clouds;
        }

        public WindBean getWind() {
            return wind;
        }

        public void setWind(WindBean wind) {
            this.wind = wind;
        }

        public SnowBean getSnow() {
            return snow;
        }

        public void setSnow(SnowBean snow) {
            this.snow = snow;
        }

        public SysBean getSys() {
            return sys;
        }

        public void setSys(SysBean sys) {
            this.sys = sys;
        }

        public String getDt_txt() {
            return dt_txt;
        }

        public void setDt_txt(String dt_txt) {
            this.dt_txt = dt_txt;
        }

        public RainBean getRain() {
            return rain;
        }

        public void setRain(RainBean rain) {
            this.rain = rain;
        }

        public List<WeatherBean> getWeather() {
            return weather;
        }

        public void setWeather(List<WeatherBean> weather) {
            this.weather = weather;
        }

        public static class MainBean {
            /**
             * temp : -1.7
             * temp_min : -1.7
             * temp_max : -1.27
             * pressure : 1018.46
             * sea_level : 1018.85
             * grnd_level : 1018.46
             * humidity : 100
             * temp_kf : -0.44
             */

            private double temp;
            private double temp_min;
            private double temp_max;
            private double pressure;
            private double sea_level;
            private double grnd_level;
            private int humidity;
            private double temp_kf;

            public double getTemp() {
                return temp;
            }

            public void setTemp(double temp) {
                this.temp = temp;
            }

            public double getTemp_min() {
                return temp_min;
            }

            public void setTemp_min(double temp_min) {
                this.temp_min = temp_min;
            }

            public double getTemp_max() {
                return temp_max;
            }

            public void setTemp_max(double temp_max) {
                this.temp_max = temp_max;
            }

            public double getPressure() {
                return pressure;
            }

            public void setPressure(double pressure) {
                this.pressure = pressure;
            }

            public double getSea_level() {
                return sea_level;
            }

            public void setSea_level(double sea_level) {
                this.sea_level = sea_level;
            }

            public double getGrnd_level() {
                return grnd_level;
            }

            public void setGrnd_level(double grnd_level) {
                this.grnd_level = grnd_level;
            }

            public int getHumidity() {
                return humidity;
            }

            public void setHumidity(int humidity) {
                this.humidity = humidity;
            }

            public double getTemp_kf() {
                return temp_kf;
            }

            public void setTemp_kf(double temp_kf) {
                this.temp_kf = temp_kf;
            }
        }

        public static class CloudsBean {
            /**
             * all : 12
             */

            private int all;

            public int getAll() {
                return all;
            }

            public void setAll(int all) {
                this.all = all;
            }
        }

        public static class WindBean {
            /**
             * speed : 4.03
             * deg : 332.502
             */

            private double speed;
            private double deg;

            public double getSpeed() {
                return speed;
            }

            public void setSpeed(double speed) {
                this.speed = speed;
            }

            public double getDeg() {
                return deg;
            }

            public void setDeg(double deg) {
                this.deg = deg;
            }
        }

        public static class SnowBean {
            /**
             * 3h : 0.0015
             */

            @SerializedName("3h")
            private double _$3h;

            public double get_$3h() {
                return _$3h;
            }

            public void set_$3h(double _$3h) {
                this._$3h = _$3h;
            }
        }

        public static class SysBean {
            /**
             * pod : d
             */

            private String pod;

            public String getPod() {
                return pod;
            }

            public void setPod(String pod) {
                this.pod = pod;
            }
        }

        public static class RainBean {
            /**
             * 3h : 0.03
             */

            @SerializedName("3h")
            private double _$3h;

            public double get_$3h() {
                return _$3h;
            }

            public void set_$3h(double _$3h) {
                this._$3h = _$3h;
            }
        }

        public static class WeatherBean {
            /**
             * id : 800
             * main : Clear
             * description : clear sky
             * icon : 01d
             */

            private int id;
            private String main;
            private String description;
            private String icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
