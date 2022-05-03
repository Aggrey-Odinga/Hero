import exceptions.HeroExistsInSquadException;
import exceptions.SquadExistsException;
import exceptions.SquadFullException;
import models.Hero;
import models.Squad;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SquadServiceTest {

    @Test
    public void add_squad_should_be_successful() throws Exception {
        SquadService service = new SquadService();

        Squad squad = new Squad("Avengers", "Stopping Thanos", 2);
        List<Squad> squads = service.addSquad(new ArrayList<>(), squad);

        Assert.assertEquals(squad, squads.get(0));
    }

    @Test
    public void add_existing_squad_should_throw_squad_exists_exception() {

        List<Squad> squads = new ArrayList<>();
        Squad squad = new Squad("Avengers", "Stopping Thanos", 2);
        squads.add(squad);
        Exception ex = null;
        SquadService service = new SquadService();
        try {
            service.addSquad(squads, squad);
        } catch (SquadExistsException e) {
            ex = e;
        }
        Assert.assertNotNull(ex);
        Assert.assertEquals("A squad with the name Avengers already exists", ex.getMessage());

    }

    @Test
    public void add_hero_to_a_full_squad_should_throw_squad_full_exception() {
        Squad squad = new Squad("Avengers", "Stoping Thanos", 2);
        squad.addHero(new Hero("Thor", 30));
        squad.addHero(new Hero("Iron Man", 39));

        Exception ex = null;
        SquadService service = new SquadService();
        try {
            service.addHero(new ArrayList<>(), squad, new Hero("SpiderMan", 20));
        } catch (Exception e) {
            ex = e;
        }

        Assert.assertNotNull(ex);
        Assert.assertEquals("The squad has reached a max size of 2", ex.getMessage());

    }


}
