import exceptions.HeroExistsException;
import models.Hero;

import java.util.List;

public class HeroService {

    public List<Hero> addHero(Hero hero, List<Hero> heroes) throws HeroExistsException {
        for (Hero h : heroes) {
            if(h.getName().equalsIgnoreCase(hero.getName())) {
               throw new HeroExistsException(String.format("A hero with the name %s already exists", hero.getName()));
            }
        }
        heroes.add(hero);
        return heroes;
    }
}
