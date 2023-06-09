import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
 
public class Calculator extends Application {
    private TextField textField = new TextField();
    private long num1 = 0;
    private long num2 = 0;
    private String finalform = "";
    private boolean on = true;
     private boolean undifined = false;
    
   
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        textField.setFont(Font.font(20));
        textField.setPrefHeight(50);
        textField.setAlignment(Pos.CENTER_RIGHT);
        textField.setEditable(false);

        StackPane stackpane = new StackPane();
        stackpane.setPadding(new Insets(10));
        stackpane.getChildren().add(textField);
       


        TilePane tile = new TilePane();
        tile.setHgap(10);
        tile.setVgap(10);
        tile.setAlignment(Pos.TOP_CENTER);
        tile.getChildren().addAll(
            createButtonForNumber("7"),
            createButtonForNumber("8"),
            createButtonForNumber("9"),
            createButtonForOperators("/"),

            createButtonForNumber("4"),
            createButtonForNumber("5"),
            createButtonForNumber("6"),
            createButtonForOperators("x"),

            createButtonForNumber("3"),
            createButtonForNumber("2"),
            createButtonForNumber("1"),
            createButtonForOperators("-"),

            createButtonForNumber("0"),
            createButtonForClear("C"),
            createButtonForOperators("="),
            createButtonForOperators("+")
);
       


        BorderPane root = new BorderPane();
        root.setTop(stackpane);
        root.setCenter(tile);
        root.setBackground(new Background(new BackgroundFill(Color.SLATEGRAY,CornerRadii.EMPTY,Insets.EMPTY)));
   
        Scene scene = new Scene(root,250,300);
       

        
        primaryStage.setScene(scene);
        primaryStage.setTitle("Aryabhatta Calculator");
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    private Button createButtonForNumber(String ch){
        Button button = new Button(ch);
        button.setPrefSize(50, 50);
        button.setFont(Font.font(18));
        button.setStyle("fx-background-color: #0000FF");
        button.setOnAction(this::processNumbers);
        return button;

    }
    private Button createButtonForOperators(String ch){
        Button button = new Button(ch);
        button.setPrefSize(50, 50);
        button.setFont(Font.font(18));
        button.setOnAction(this::processOperators);
        return button;

    }
    private Button createButtonForClear(String ch){
        Button button = new Button(ch);
        button.setPrefSize(50, 50);
        button.setFont(Font.font(18));
        button.setOnAction(e->{
            textField.setText("");
            finalform = "";
            on = true;
});
        return button;

    }
    private void processNumbers(ActionEvent e){

        if(on){
            textField.setText("");
            on = false;
        }
        String value = ((Button)e.getSource()).getText();
        textField.setText(textField.getText()+value);

    }

    private void processOperators(ActionEvent e){
        String value = ((Button)e.getSource()).getText();
        if(!value.equals("=")){
            if(!finalform.isEmpty())
                return ;
            num1 = Long.parseLong(textField.getText());
            finalform = value;
            textField.setText("");


        }
        else{
           
            if(finalform.isEmpty()) return;
            num2 = Long.parseLong(textField.getText());
           float result =  calculate(num1,num2,finalform,undifined);
           boolean res = arya(num1, num2, finalform, undifined);
           if(res) textField.setText("UNDEFINED!!");
          
           else  textField.setText(String.valueOf(result));
           on = true;
           finalform = "";


        }

    }
    private float calculate(long num1,long num2,String operatior,boolean undifined){
       switch(operatior){
        case "+": return num1+num2;
        case "-": return num1-num2;
        case "x": return num1*num2;
        case "/":
            if(num2==0) {
                undifined = true;
                return 0;
               
            }
            else return num1/num2;
        default : return 0;
       } 
    }
    private boolean arya (long num1,long num2,String operatior,boolean undifined){
        if(operatior=="/"&&num2==0)
            return true;
        else return false;
    }
    public static void main(String[] args) {
        launch(args);
        
    }
}
