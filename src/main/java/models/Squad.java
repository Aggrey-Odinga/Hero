package models;

import java.util.ArrayList;
import java.util.List;

public class Squad {

    private final String name;
    private final String cause;
    private final int maxSize;
    private List<Hero> heros;

    public Squad(String name, String cause, int maxSize) {
        this.name = name;
        this.cause = cause;
        this.maxSize = maxSize;
        this.heros = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCause() {
        return cause;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public List<Hero> getHeros() {
        return heros;
    }

    public void addHero(Hero hero) {
        // TODO check if hero exists
        this.heros.add(hero);
    }


}
