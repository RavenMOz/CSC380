package treelinecalculationsalgorithm;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RecursiveTreeTest extends Application
{
    static Stage window;
    static Scene scene;

    static Pane pane = new Pane();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Family myFamily = new Family();
/*
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
/*
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

        window = primaryStage;
        window.setTitle("Tree Line Algorithm Test");

        Line dividingLine = new Line(700, 0, 700, 1000);

        Label nameLabel = new Label("Enter Member Name:");
        nameLabel.setLayoutX(725);
        nameLabel.setLayoutY(50);

        TextField nameInput = new TextField();
        nameInput.setLayoutX(850);
        nameInput.setLayoutY(50);

        Label bioLabel = new Label("Enter Member Bio:");
        bioLabel.setLayoutX(725);
        bioLabel.setLayoutY(150);

        TextField bioInput = new TextField();
        bioInput.setLayoutX(850);
        bioInput.setLayoutY(150);

        TextField spouseInput = new TextField();
        spouseInput.setLayoutX(850);
        spouseInput.setLayoutY(250);
        spouseInput.setVisible(false);

        Label spouseLabel = new Label("Enter New Spouse:");
        spouseLabel.setLayoutX(725);
        spouseLabel.setLayoutY(250);
        spouseLabel.setVisible(false);

        Button spouseButton = new Button("Add Spouse");
        spouseButton.setLayoutX(800);
        spouseButton.setLayoutY(200);
        spouseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                spouseInput.setVisible(true);
                spouseLabel.setVisible(true);
            }
        });

        TextField motherInput = new TextField();
        motherInput.setLayoutX(850);
        motherInput.setLayoutY(350);
        motherInput.setVisible(false);

        Label motherLabel = new Label("Enter New Mother:");
        motherLabel.setLayoutX(725);
        motherLabel.setLayoutY(350);
        motherLabel.setVisible(false);

        Button motherButton = new Button("Add Mother");
        motherButton.setLayoutX(800);
        motherButton.setLayoutY(300);
        motherButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                motherInput.setVisible(true);
                motherLabel.setVisible(true);
            }
        });

        TextField fatherInput = new TextField();
        fatherInput.setLayoutX(850);
        fatherInput.setLayoutY(450);
        fatherInput.setVisible(false);

        Label fatherLabel = new Label("Enter New Father:");
        fatherLabel.setLayoutX(725);
        fatherLabel.setLayoutY(450);
        fatherLabel.setVisible(false);

        Button fatherButton = new Button("Add Father");
        fatherButton.setLayoutX(800);
        fatherButton.setLayoutY(400);
        fatherButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                fatherInput.setVisible(true);
                fatherLabel.setVisible(true);
            }
        });

        TextField childInput = new TextField();
        childInput.setLayoutX(850);
        childInput.setLayoutY(550);
        childInput.setVisible(false);

        Label childLabel = new Label("Enter New Child:");
        childLabel.setLayoutX(725);
        childLabel.setLayoutY(550);
        childLabel.setVisible(false);

        Button childButton = new Button("Add Child");
        childButton.setLayoutX(800);
        childButton.setLayoutY(500);
        childButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                childInput.setVisible(true);
                childLabel.setVisible(true);
            }
        });

        Button addButton = new Button("Add Member");
        addButton.setLayoutX(800);
        addButton.setLayoutY(600);
        addButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                Member curMember         = null;
                Member curMemberRelative = null;

                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(nameInput.getText()))
                    {
                        curMember = myFamily.getMember(count);

                        break;
                    }
                }

                if(curMember == null)
                {
                    curMember = new Member(nameInput.getText(), bioInput.getText());
                }

                if(!(spouseInput.getText().equals("")))
                {
                    curMemberRelative = new Member(spouseInput.getText());

                    curMember.addSpouse(curMemberRelative);
                    curMemberRelative.addSpouse(curMember);

                    myFamily.addMember(curMemberRelative);
                }

                if(!(motherInput.getText().equals("")))
                {
                    curMemberRelative = new Member(motherInput.getText());
                    
                    curMember.addMother(curMemberRelative);
                    curMemberRelative.addChild(curMember);

                    myFamily.addMember(curMemberRelative);
                }

                if(!(fatherInput.getText().equals("")))
                {
                    curMemberRelative = new Member(fatherInput.getText());
                    
                    curMember.addFather(curMemberRelative);
                    curMemberRelative.addChild(curMember);

                    myFamily.addMember(curMemberRelative);
                }

                if(!(childInput.getText().equals("")))
                {
                    curMemberRelative = new Member(childInput.getText());
                    
                    curMember.addChild(curMemberRelative);
                    
                    if(curMember.hasMother())
                    {
                        curMemberRelative.addFather(curMember);
                    }

                    else
                    {
                        curMemberRelative.addMother(curMember);
                    }

                    myFamily.addMember(curMemberRelative);
                }

                myFamily.addMember(curMember);

                drawMembers(myFamily, myFamily.getRootMember());
            }
        });

        pane.getChildren().add(dividingLine);
        pane.getChildren().add(addButton);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(nameInput);
        pane.getChildren().add(bioLabel);
        pane.getChildren().add(bioInput);
        pane.getChildren().add(spouseLabel);
        pane.getChildren().add(spouseInput);
        pane.getChildren().add(spouseButton);
        pane.getChildren().add(motherLabel);
        pane.getChildren().add(motherInput);
        pane.getChildren().add(motherButton);
        pane.getChildren().add(fatherLabel);
        pane.getChildren().add(fatherInput);
        pane.getChildren().add(fatherButton);
        pane.getChildren().add(childLabel);
        pane.getChildren().add(childInput);
        pane.getChildren().add(childButton);

        scene = new Scene(pane, 1000, 700);
        window.setScene(scene);
        window.show();
    }

    public static void drawMembers(Family familyTree, Member root)
    {
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

            scene.snapshot(null);
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

                    scene.snapshot(null);
                    Line memLine = new Line(((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + 12, ((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + 200); 
                    pane.getChildren().add(memLine);

                    root.getChild(count).hasBeenDrawn(true);
                }

                drawMembers(familyTree, root.getChild(count));
            }
        }
    }
}