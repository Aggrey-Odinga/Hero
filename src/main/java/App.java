import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

import exceptions.HeroExistsException;
import exceptions.SquadExistsException;
import models.Hero;
import models.Squad;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


import static spark.Spark.*;

public class App {
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
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


            String message = "hero created successfully";

            try {
                HeroService heroService = new HeroService();
                herosDB = heroService.addHero(hero, herosDB);
            } catch (HeroExistsException e) {
                message = e.getMessage();
            }

            request.session().attribute("heros", herosDB);
            model.put("heros", herosDB);
            model.put("message", message);
            return new ModelAndView(model, "heroes.hbs");
        }, new HandlebarsTemplateEngine());



        get("/heroes", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Hero> herosDB = request.session().attribute("heros");
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


            String message = "squad created successfully";

            try {
                SquadService squadService = new SquadService();
                squadsDB = squadService.addSquad(squadsDB, squad);
            } catch (SquadExistsException e) {
                message = e.getMessage();
            }

            request.session().attribute("squads", squadsDB);
            model.put("squads", squadsDB);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());


        get("/squads", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            List<Squad> squadsDB = request.session().attribute("squads");
            model.put("squads", squadsDB);
            return new ModelAndView(model, "squads.hbs");
        }, new HandlebarsTemplateEngine());




    }
}

