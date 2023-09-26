package md.listings.domain;

import java.util.List;
import java.util.Map;

public final class Cars {

    private Cars() {

    }

    public static final Map<String, List<String>> MODELS = Map.of(
            "BMW", List.of("M2", "M3", "M4", "M6", "M8", "X1", "X2", "X3", "X4", "X5", "X6", "X7"),
            "Audi", List.of("A1", "A2", "A3", "A4", "A5", "A6", "A7", "A8", "Q2", "Q3", "Q5", "Q7", "Q8"),
            "Peugeot", List.of("1007", "2008", "3008", "4008", "5008"),
            "Renault", List.of("Clio", "Kadjar", "Laguna", "Megane", "Scenic", "Twingo"),
            "Opel", List.of("Astra", "Corsa", "Insignia", "Zafira"),
            "Alfa Romeo", List.of("147", "159", "166", "Giulia", "Giulietta", "GT", "Stelvio"),
            "Fiat", List.of("Panda", "500", "500L", "Punto", "Grande Punto", "Multipla"),
            "Ferrari", List.of("458 Italia", "599", "Roma", "California"),
            "Ford", List.of("Focus", "Escort", "Fiesta", "F-150", "Mondeo"),
            "Mercedes", List.of("SLR", "SLS", "A class", "B class", "CLA class", "E class")
    );

    public static final List<String> GEARBOX = List.of("Manual", "Automatic");

    public static final List<String> FUEL_TYPE = List.of("Gasoline", "Diesel", "Electric");

    public static final List<String> BODY_TYPE = List.of("Sedan", "Pick-up", "SUV", "Station wagon", "Hatchback");

}
