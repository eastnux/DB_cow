package io.mobile.farm;

public class Farm {
    private String farm_id;
    private String farm_name;
    private String farm_location;

    public Farm(String farm_id, String farm_name, String farm_location) {
        this.farm_id = farm_id;
        this.farm_name = farm_name;
        this.farm_location = farm_location;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getFarm_name() {
        return farm_name;
    }

    public String getFarm_location() {
        return farm_location;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "farm_id='" + farm_id + '\'' +
                ", farm_name='" + farm_name + '\'' +
                ", farm_location='" + farm_location + '\'' +
                '}';
    }
}
