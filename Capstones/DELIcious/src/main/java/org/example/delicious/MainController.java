package org.example.delicious;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.delicious.enums.*;

import java.io.IOException;
import java.util.Arrays;

public class MainController {
    public static Meal.MealBuilder mealBuilder;

    static {
        mealBuilder = new Meal.MealBuilder();
    }

    public void sandwichBuilder(ActionEvent event) throws IOException {
        Scene s = prepareScene(event, "build-sandwich.fxml");
        ChoiceBox<String> breadSizes = (ChoiceBox<String>) s.lookup("#Size");
        for (SandwichSize size : SandwichSize.values())
            breadSizes.getItems().add(size.toString());
        breadSizes.setOnAction(e -> {
            mealBuilder.sandwichSize(SandwichSize.valueOf(breadSizes.getValue()));
        });
        ChoiceBox<String> bread = (ChoiceBox<String>) s.lookup("#Bread");
        bread.setOnAction(e -> mealBuilder.bread(Bread.valueOf(bread.getValue())));
        CheckBox sideSauce = (CheckBox) s.lookup("#sidesauce");
        CheckBox auJus = (CheckBox) s.lookup("#aujus");
        sideSauce.setOnAction(e -> mealBuilder.sauceOnSide(sideSauce.isSelected()));
        auJus.setOnAction(e -> mealBuilder.auJus(auJus.isSelected()));
    }

    public void useSigSandwich(ActionEvent event) throws IOException {
        Scene s = ((Node)event.getSource()).getScene();
        var choice = ((ChoiceBox<String>)s.lookup("#sigSandwiches")).getValue();
        if (choice == null) return;
        mealBuilder = Meal.SIGNATURE_SANDWICHES
                .get(choice).clone();
        sandwichBuilder(event);
    }

    public void newOrder(ActionEvent event) throws IOException {
        Scene scene = prepareScene(event, "new-order.fxml");
        ChoiceBox<String> sigSandwiches = (ChoiceBox<String>) scene.lookup("#sigSandwiches");
        Meal.SIGNATURE_SANDWICHES.keySet()
                .forEach(k -> sigSandwiches.getItems().add(k));
    }

    public void addOns(ActionEvent event) throws IOException {
        Scene scene = prepareScene(event, "add-ons.fxml");
        VBox vbox = (VBox) scene.lookup("#addonvbox");
        ChoiceBox<String> cb = new ChoiceBox<>();
        CheckBox chips = new CheckBox("Add Chips");
        chips.setSelected(mealBuilder.hasChips());
        CheckBox toasted = new CheckBox("Toast Sandwich");
        toasted.setSelected(mealBuilder.isToasted());
        Arrays.stream(DrinkSize.values())
                .map(Enum::toString)
                .forEach(s -> cb.getItems().add(s));
        cb.setValue("NONE");
        cb.setOnAction(e -> mealBuilder.drinkSize(DrinkSize.valueOf(cb.getValue())));
        chips.setOnAction(e -> mealBuilder.chips(chips.isSelected()));
        toasted.setOnAction(e -> mealBuilder.toasted(toasted.isSelected()));
        vbox.getChildren().addAll(cb, chips, toasted);
    }

    public void addMeat(ActionEvent event) throws IOException {
        Scene scene = prepareScene(event, "add-meat.fxml");
        VBox vbox = (VBox) scene.lookup("#meatv");
        for (Meat m : Meat.values()) {
            Button b = new Button();
            b.setText(m.name());
            b.setOnAction(e -> {
                mealBuilder.addMeat(Meat.valueOf(b.getText()));
                try {
                    sandwichBuilder(e);
                } catch (IOException ex) {}
            });
            vbox.getChildren().add(b);
        }
    }

    public void addCheese(ActionEvent event) throws IOException {
        Scene scene = prepareScene(event, "add-cheese.fxml");

        VBox vbox = (VBox) scene.lookup("#cheesev");
        for (Cheese m : Cheese.values()) {
            Button b = new Button();
            b.setText(m.name());
            b.setOnAction(e -> {
                mealBuilder.addCheese(Cheese.valueOf(b.getText()));
                try {
                    sandwichBuilder(e);
                } catch (IOException ignored) {}
            });
            vbox.getChildren().add(b);
        }
    }

//    Button IDs were named after their corresponding enums
//    This was done in order to make possible creating one general scene that creates the buttons depending on the enum
//    We convert the button ID (String) into a Class type, then assume it's an enum and take its values
//    If it's not an enum, the assertion will print a String saying so.
//    When looping through enum values, we cast it as an Enum type to use the name() method as just one more assurance
//    that it's an enum value, rather than simply using toString().
    public void addSomething(ActionEvent event) throws IOException, ClassNotFoundException {
        Scene scene = prepareScene(event, "add-something.fxml");
        var enVals = Class.forName("org.example.delicious.enums." + ((Button) event.getSource()).getId())
                .getEnumConstants();
        assert enVals != null: "ID did not represent an enum!";
        VBox vbox = (VBox) scene.lookup("#vbadd");
        for (Object o : enVals) {
            Button b = new Button();
            b.setText(((Enum<?>)o).name());
            b.setOnAction(e -> {
                switch (o) {
                    case Meat m -> mealBuilder.addMeat(m);
                    case Cheese c -> mealBuilder.addCheese(c);
                    case RegTopping r -> mealBuilder.addTopping(r);
                    case Sauce s -> mealBuilder.addSauce(s);
                    default -> throw new RuntimeException("Black magic has occurred.");
                }
                try {
                    sandwichBuilder(e);
                } catch (IOException ignored) {}
            });
            vbox.getChildren().add(b);
        }
    }

    public void cancelOrder(ActionEvent event) throws IOException {
        mealBuilder = new Meal.MealBuilder();
        prepareScene(event, "main-menu.fxml");
    }

    public void onExit(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }

    private Scene prepareScene(ActionEvent event, String fxmlFile) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(fxmlFile));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root, 640, 480);
        stage.setScene(scene);
        stage.show();
        return scene;
    }
}
