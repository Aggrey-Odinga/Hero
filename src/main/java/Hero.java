import java.util.ArrayList;
import java.util.List;

public class Hero {

     private final String name;
     private final int age;
     private  List<String> powers;
     private  List<String> weaknesses;

    public Hero(String name, int age) {
        this.name = name;
        this.age = age;
        this.powers = new ArrayList<>();
        this.weaknesses = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getPowers() {
        return powers;
    }

    public List<String> getWeaknesses() {
        return weaknesses;
    }

    public void addPower(String power) {
        this.powers.add(power);
    }

    public void addWeakness(String weakness) {
        this.weaknesses.add(weakness);
    }
}
