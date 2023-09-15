package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class Friend {

private String name;
private String gender;
private int age;
private boolean nitPicker;
private String[] hobbies;
private Map<String, String> relatives;

    @JsonProperty("Имя")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("Пол")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @JsonProperty("Возраст")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JsonProperty("Душнила")
    public boolean isNitPicker() {
        return nitPicker;
    }

    public void setNitPicker(boolean nitPicker) {
        this.nitPicker = nitPicker;
    }

    @JsonProperty("Хобби")
    public String[] getHobbies() {
        return hobbies;
    }

    public void setHobbies(String[] hobbies) {
        this.hobbies = hobbies;
    }

    @JsonProperty("Родственники")
    public Map<String, String> getRelatives() {
        return relatives;
    }

    public void setRelatives(Map<String, String> relatives) {
        this.relatives = relatives;
    }
}