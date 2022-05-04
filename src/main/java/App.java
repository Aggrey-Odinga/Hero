import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
        staticFileLocation("/public");

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "homepage.hbs");
        }, new HandlebarsTemplateEngine());


        get("/about", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "about.hbs");
        }, new HandlebarsTemplateEngine());

        get("/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "hero.hbs");
        }, new HandlebarsTemplateEngine());

        post("/hero", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("heroName");
            String age = request.queryParams("heroAge");
            String power = request.queryParams("heroPower");
            String weakness = request.queryParams("heroWeakness");
            // TODO Add validations of the params
            List<Hero> herosDB = request.session().attribute("heros");

            Hero hero = new Hero(name, Integer.parseInt(age));
            hero.addPower(power);
            hero.addWeakness(weakness);

            HeroService heroService = new HeroService();
            herosDB = heroService.addHero(hero, herosDB);

            request.session().attribute("heros", herosDB);
            model.put("heros", herosDB);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());


        get("/squad", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            return new ModelAndView(model, "squad.hbs");
        }, new HandlebarsTemplateEngine());


        post("/squad", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("squadName");
            String size = request.queryParams("squadSize");
            String cause = request.queryParams("squadCause");
            List<Squad> squadsDB = request.session().attribute("squads");

            Squad squad = new Squad(name, cause, Integer.parseInt(size));
            SquadService squadService = new SquadService();
            squadsDB = squadService.addSquad(squadsDB, squad);

            request.session().attribute("squads", squadsDB);
            model.put("squads", squadsDB);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());




    }
}

