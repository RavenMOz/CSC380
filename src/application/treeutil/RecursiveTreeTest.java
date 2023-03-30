package treelinecalculationsalgorithm;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.shape.DrawMode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Group;

public class RecursiveTreeTest extends Application
{
    Stage window;
    Scene scene;

    static Pane pane = new Pane();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Family myFamily = new Family();

        Member member1 = new Member("John Young", "Lives in NYC");
        Member member2 = new Member("Elise Young", "Works at a School");
        Member member3 = new Member("Mark Young", "Loves to Cook");
        Member member4 = new Member("Zachary Young", "");
        Member member5 = new Member("Jennifer White", "");
        Member member6 = new Member("Stacy Young", "");
        Member member7 = new Member("Anthony White", "");
        Member member8 = new Member("Emily Young", "");
        Member member9 = new Member("Harold Young", "");
        Member member10 = new Member("Rebecca White", "");

        /****************************************************************************************************************************/

        //myFamily.addMember(member1);
        myFamily.addMember(member2);
        myFamily.addMember(member3);
        //myFamily.addMember(member4);
        myFamily.addMember(member5);
        //myFamily.addMember(member6);
        myFamily.addMember(member7);
        //myFamily.addMember(member8);
        //myFamily.addMember(member9);
        myFamily.addMember(member10);

        myFamily.getMember(member2).addSpouse(member3);
        myFamily.getMember(member3).addSpouse(member2);

        //myFamily.getMember(member2).addChild(member4);
        //myFamily.getMember(member2).addChild(member1);
        myFamily.getMember(member2).addChild(member5);

        //myFamily.getMember(member3).addChild(member4);
        //myFamily.getMember(member3).addChild(member1);
        myFamily.getMember(member3).addChild(member5);

        //myFamily.getMember(member4).addMother(member2);
        //myFamily.getMember(member4).addFather(member3);

        //myFamily.getMember(member1).addMother(member2);
        //myFamily.getMember(member1).addFather(member3);

        myFamily.getMember(member5).addMother(member2);
        myFamily.getMember(member5).addFather(member3);

        //myFamily.getMember(member4).addSpouse(member6);
        //myFamily.getMember(member6).addSpouse(member4);

        //myFamily.getMember(member4).addChild(member8);
        //myFamily.getMember(member4).addChild(member9);

        //myFamily.getMember(member6).addChild(member8);
        //myFamily.getMember(member6).addChild(member9);

        //myFamily.getMember(member8).addFather(member4);
        //myFamily.getMember(member8).addMother(member6);

        //myFamily.getMember(member9).addFather(member4);
        //myFamily.getMember(member9).addMother(member6);

        myFamily.getMember(member5).addSpouse(member7);
        myFamily.getMember(member7).addSpouse(member5);

        myFamily.getMember(member5).addChild(member10);

        myFamily.getMember(member7).addChild(member10);

        myFamily.getMember(member10).addMother(member5);
        myFamily.getMember(member10).addFather(member7);

        /****************************************************************************************************************************/

        //getMembers(myFamily, myFamily.getRootMember());

        /****************************************************************************************************************************/

        window = primaryStage;
        window.setTitle("Tree Line Algorithm Test");

        Line dividingLine = new Line(700, 0, 700, 1000);

        Button addButton = new Button("Add Member");
        addButton.setLayoutX(800);
        addButton.setLayoutY(250);
        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
               drawMembers(myFamily, myFamily.getRootMember());
            }
        });

        pane.getChildren().add(dividingLine);
        pane.getChildren().add(addButton);

        scene = new Scene(pane, 1000, 700);
        window.setScene(scene);
        window.show();
    }

    public static void getMembers(Family familyTree, Member root)
    {
        if(root.hasMother() && !(root.getMother().getDrawnFlag()))
        {
            System.out.println(root.getMother().getName());
            root.getMother().hasBeenDrawn(true);

            getMembers(familyTree, root.getMother());
        }

        if(root.hasFather() && !(root.getFather().getDrawnFlag()))
        {
            System.out.println(root.getFather().getName());
            root.getFather().hasBeenDrawn(true);

            getMembers(familyTree, root.getFather());
        }

        if(root.hasSpouse() && !(root.getSpouse().getDrawnFlag()))
        {
            System.out.println(root.getSpouse().getName());
            root.getSpouse().hasBeenDrawn(true);

            getMembers(familyTree, root.getSpouse());
        }

        if(root.hasChildren())
        {
            for(int count = 0; count < root.getChildCount(); count++)
            {
                if(!root.getChild(count).getDrawnFlag())
                {
                    System.out.println(root.getChild(count).getName());
                    root.getChild(count).hasBeenDrawn(true);
                }

                getMembers(familyTree, root.getChild(count));
            }
        }
    }

    public static void drawMembers(Family familyTree, Member root)
    {
        Platform.runLater(() -> {
            if(!(root.getDrawnFlag()))
            {
                Button memButton = new Button(root.getName());
                memButton.setLayoutX(215);
                memButton.setLayoutY(200);

                root.setButton(memButton);
                pane.getChildren().add(root.getButton());

                root.hasBeenDrawn(true);
            }

            if(root.hasMother() && !(root.getMother().getDrawnFlag()))
            {
                Button memButton = new Button(root.getMother().getName());
                memButton.setLayoutX(root.getButton().getLayoutX() - 200);
                memButton.setLayoutY(root.getButton().getLayoutY() - 200);

                root.getMother().setButton(memButton);
                pane.getChildren().add(root.getMother().getButton());

                root.getMother().hasBeenDrawn(true);

                drawMembers(familyTree, root.getMother());
            }

            if(root.hasFather() && !(root.getFather().getDrawnFlag()))
            {
                Button memButton = new Button(root.getFather().getName());
                memButton.setLayoutX(root.getButton().getLayoutX() + 200);
                memButton.setLayoutY(root.getButton().getLayoutY() - 200);

                root.getFather().setButton(memButton);
                pane.getChildren().add(root.getFather().getButton());

                root.getFather().hasBeenDrawn(true);

                drawMembers(familyTree, root.getFather());
            }

            if(root.hasSpouse() && !(root.getSpouse().getDrawnFlag()))
            {
                Button memButton = new Button(root.getSpouse().getName());
                memButton.setLayoutX(root.getButton().getLayoutX() + 200);
                memButton.setLayoutY(root.getButton().getLayoutY());

                root.getSpouse().setButton(memButton);
                pane.getChildren().add(root.getSpouse().getButton());

                Line memLine = new Line(root.getButton().getLayoutX() + root.getButton().getWidth(), root.getButton().getLayoutY() + 12, root.getSpouse().getButton().getLayoutX(), root.getSpouse().getButton().getLayoutY() + 12);
                pane.getChildren().add(memLine);

                root.getSpouse().hasBeenDrawn(true);

                drawMembers(familyTree, root.getSpouse());
            }

            if(root.hasChildren())
            {
                for(int count = 0; count < root.getChildCount(); count++)
                {
                    if(!root.getChild(count).getDrawnFlag())
                    {
                        Button memButton = new Button(root.getChild(count).getName());
                        memButton.setLayoutX((root.getSpouse().getButton().getLayoutX() + root.getButton().getLayoutX()) / 2);
                        memButton.setLayoutY(root.getButton().getLayoutY() + 200);

                        root.getChild(count).setButton(memButton);
                        pane.getChildren().add(root.getChild(count).getButton());

                        root.getChild(count).hasBeenDrawn(true);
                    }

                    drawMembers(familyTree, root.getChild(count));
                }
            }

        });
    }
}