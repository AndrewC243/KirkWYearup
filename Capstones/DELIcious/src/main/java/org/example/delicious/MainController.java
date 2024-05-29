package org.example.delicious;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.example.delicious.enums.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.example.delicious.JavaFxUtils.centeredVbox;
import static org.example.delicious.JavaFxUtils.prepareScene;

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
        if (mealBuilder.build().getSandwichSize() != null)
            breadSizes.setValue(mealBuilder.build().getSandwichSize().toString());
        breadSizes.setOnAction(e -> {
            mealBuilder.sandwichSize(SandwichSize.valueOf(breadSizes.getValue()));
        });
        ChoiceBox<String> breadTypes = (ChoiceBox<String>) s.lookup("#Bread");
        for (Bread bread : Bread.values())
            breadTypes.getItems().add(bread.toString());
        if (mealBuilder.build().getBread() != null)
            breadTypes.setValue(mealBuilder.build().getBread().toString());
        breadTypes.setOnAction(e -> mealBuilder.bread(Bread.valueOf(breadTypes.getValue())));
        CheckBox sideSauce = (CheckBox) s.lookup("#sidesauce");
        sideSauce.setSelected(mealBuilder.build().sauceOnSide());
        CheckBox auJus = (CheckBox) s.lookup("#aujus");
        auJus.setSelected(mealBuilder.build().auJus());
        sideSauce.setOnAction(e -> mealBuilder.sauceOnSide(sideSauce.isSelected()));
        auJus.setOnAction(e -> mealBuilder.auJus(auJus.isSelected()));
    }

    public void checkout(ActionEvent event) throws IOException {
        Scene s = prepareScene(event, "checkout.fxml");
        Meal completedMeal = mealBuilder.build();
        HBox hb = (HBox) s.lookup("#hbox");
        hb.setAlignment(Pos.CENTER);
        List<VBox> vboxes = new ArrayList<>();

        vboxes.add(centeredVbox());
        vboxes.getFirst().getChildren().add(new Text("Sandwich size: " + completedMeal.getSandwichSize()));
        vboxes.getFirst().getChildren().add(new Text("Bread: " + completedMeal.getBread()));
        vboxes.getFirst().getChildren().add(new Text("Drink size: " + (completedMeal.getDrinkSize())));
        vboxes.getFirst().getChildren().add(new Text("Toasted: " + (completedMeal.isToasted() ? "Yes" : "No")));
        vboxes.getFirst().getChildren().add(new Text("Sauce on side: " + (completedMeal.sauceOnSide() ? "Yes" : "No")));
        vboxes.getFirst().getChildren().add(new Text("Au Jus: " + (completedMeal.auJus() ? "Yes" : "No")));
        vboxes.getFirst().getChildren().add(new Text("Chips: " + (completedMeal.getChips() ? "Yes" : "No")));

        vboxes.add(centeredVbox());
        vboxes.get(1).getChildren().add(new Text("Meats:"));
        completedMeal.getMeats().stream()
                .map(m -> new Text(m.toString()))
                .forEach(vboxes.get(1).getChildren()::add);

        vboxes.add(centeredVbox());
        vboxes.get(2).getChildren().add(new Text("Cheeses:"));
        completedMeal.getCheeses().stream()
                .map(c -> new Text(c.toString()))
                .forEach(vboxes.get(2).getChildren()::add);

        vboxes.add(centeredVbox());
        vboxes.get(3).getChildren().add(new Text("Toppings:"));
        completedMeal.getToppings().stream()
                .map(t -> new Text(t.toString()))
                .forEach(vboxes.get(3).getChildren()::add);

        vboxes.add(centeredVbox());
        vboxes.get(4).getChildren().add(new Text("Sauces:"));
        completedMeal.getSauces().stream()
                .map(sa -> new Text(sa.toString()))
                .forEach(vboxes.get(4).getChildren()::add);

        hb.getChildren().addAll(vboxes);

        VBox topVb = centeredVbox();
        Text priceTotal = new Text("Total price: " + new MealOrder(completedMeal).getPrice().toString());
        priceTotal.setFont(Font.font(priceTotal.getFont().getName(), FontWeight.BOLD, 24));
        topVb.getChildren().add(priceTotal);
        ((BorderPane)s.lookup("#bpane")).setTop(topVb);
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
        Meal builtMeal = mealBuilder.build();
        if (builtMeal.getSandwichSize() == null) return;
        if (builtMeal.getBread() == null) return;

        Scene scene = prepareScene(event, "add-ons.fxml");
        VBox vbox = (VBox) scene.lookup("#addonvbox");
        ChoiceBox<String> cb = new ChoiceBox<>();
        CheckBox chips = new CheckBox("Add Chips");
        chips.setSelected(mealBuilder.build().getChips());
        CheckBox toasted = new CheckBox("Toast Sandwich");
        toasted.setSelected(mealBuilder.build().isToasted());
        Arrays.stream(DrinkSize.values())
                .map(Enum::toString)
                .forEach(s -> cb.getItems().add(s));
        cb.setValue("NONE");
        cb.setOnAction(e -> mealBuilder.drinkSize(DrinkSize.valueOf(cb.getValue())));
        chips.setOnAction(e -> mealBuilder.chips(chips.isSelected()));
        toasted.setOnAction(e -> mealBuilder.toasted(toasted.isSelected()));
        vbox.getChildren().addAll(cb, chips, toasted);
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
            b.setText(o.toString());
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

    public void saveOrder(ActionEvent event) throws IOException {
        MealOrder mo = new MealOrder(mealBuilder.build());
        mo.saveOrder();
        mealBuilder = new Meal.MealBuilder();
        prepareScene(event, "main-menu.fxml");
    }

    public void cancelOrder(ActionEvent event) throws IOException {
        mealBuilder = new Meal.MealBuilder();
        prepareScene(event, "main-menu.fxml");
    }

    public void onExit(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.close();
    }
}
