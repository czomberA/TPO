package pl.edu.pja.dziabor.tpo_09.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PersonDtoBmi {
    private double weight;
    private double height;


    public PersonDtoBmi() {
    }

    public PersonDtoBmi(double weight, double height) {
        this.weight = weight;
        this.height = height;

    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @JsonProperty("BMI")
    public int getBmi() {
        return (int) ((int) weight/Math.pow(height/100, 2));
    }

    @JsonProperty("type")
    public String getType() {
        if (getBmi() < 18) {
            return "Underweight";
        } else if (getBmi() < 25) {
            return "Normal";
        } else {
            return "Obese";
        }
    }

    @Override
    public String toString() {
        return getBmi()+"";
    }
}
