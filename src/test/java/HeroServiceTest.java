import exceptions.HeroExistsException;
import models.Hero;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class HeroServiceTest {

    @Test
    public void add_new_hero_should_be_successful() throws HeroExistsException {
        HeroService service = new HeroService();

        Hero hero = new Hero("Superman", 24);
        List<Hero> heros = service.addHero(hero, new ArrayList<>());

        Assert.assertEquals(hero, heros.get(0));
    }

    @Test
    public void add_existing_hero_throws_hero_exists_exception(){
        HeroService heroService  = new HeroService();

        List<Hero> heroes = new ArrayList<>();
        Hero superman = new Hero("Superman", 24);
        heroes.add(superman);

        try {
            heroService.addHero(superman, heroes);
        } catch (HeroExistsException e) {
            Assert.assertEquals("A hero with the name Superman already exists", e.getMessage());
        }
    }
}
