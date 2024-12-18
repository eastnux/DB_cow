package io.mobile.farm;

public class Farm {
    private String farm_id;
    private String name;
    private String location;

    public Farm(String farm_id, String location, String name) {
        this.farm_id = farm_id;
        this.location = location;
        this.name = name;
    }

    public String getFarm_id() {
        return farm_id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "Farm{" +
                "farm_id='" + farm_id + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
