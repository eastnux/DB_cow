package io.mobile.cow;

public class Cow {
    private String cow_id;
    private int age;
    private String health_status;
    private String gender;

    public Cow(String cow_id, int age, String health_status, String gender) {
        this.cow_id = cow_id;
        this.age = age;
        this.health_status = health_status;
        this.gender = gender;
    }

    public String getCow_id() {
        return cow_id;
    }

    public int getAge() {
        return age;
    }

    public String getHealth_status() {
        return health_status;
    }

    public String getGender() {
        return gender;
    }

    @Override
    public String toString() {
        return "Cow{" +
                "cow_id='" + cow_id + '\'' +
                ", age=" + age +
                ", health_status='" + health_status + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}

