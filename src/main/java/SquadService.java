import exceptions.HeroExistsInSquadException;
import exceptions.SquadExistsException;
import exceptions.SquadFullException;
import models.Hero;
import models.Squad;

import java.util.ArrayList;
import java.util.List;

public class SquadService {

    public List<Squad> addSquad(List<Squad> squads, Squad squad) throws SquadExistsException {
        if (squads == null) {
            squads = new ArrayList<>();
        }
        for (Squad sq : squads) {
            if (sq.getName().equalsIgnoreCase(squad.getName())) {
                throw new SquadExistsException(String.format("A squad with the name %s already exists", sq.getName()));
            }
        }
        squads.add(squad);
        return squads;
    }


    public Squad addHero(List<Squad> squads, Squad squad, Hero hero) throws HeroExistsInSquadException, SquadFullException {
        for (Squad sq : squads) {
            for (Hero h : sq.getHeros()) {
                if (h.getName().equalsIgnoreCase(hero.getName())) {
                    throw new HeroExistsInSquadException(String.format("Hero %s already exists in squad %s", hero.getName(), sq.getName()));
                }
            }
        }

        if (squad.getHeros().size() + 1 > squad.getMaxSize()) {
            throw new SquadFullException(String.format("The squad has reached a max size of %d", squad.getMaxSize()));
        }

        squad.addHero(hero);
        return squad;
    }

}
