package pl.edu.pja.dziabor.tpo_09.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDtoBmr {
    private String gender;
    private double weight;
    private double height;
    private int age;

    public PersonDtoBmr() {}

    public PersonDtoBmr(String gender, double weight, double height, int age) {
        this.gender = gender.toLowerCase();
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @JsonProperty("BMR")
    public int getBmr() {
        switch (gender.toLowerCase()) {
            case "man": return (int) (13.397*weight+4.799*height-5.677*age+88.362);
            case "woman": return (int) (9.247*weight+3.098*height-4.33*age + 447.593);
            default: return 0;
        }
    }

    @Override
    public String toString() {
        return getBmr()+"kcal";
    }
}
