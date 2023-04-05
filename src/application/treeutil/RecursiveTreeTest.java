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

        /****************************************************************************************************************************/

        window = primaryStage;
        window.setTitle("Tree Line Algorithm Test");

        Line dividingLine1 = new Line(1200, 0, 1200, 1000);
        Line dividingLine2 = new Line(1200, 150, 1500, 150);
        Line dividingLine3 = new Line(1200, 550, 1500, 550);

        Label nameLabel = new Label("Enter Member Name:");
        nameLabel.setLayoutX(1225);
        nameLabel.setLayoutY(50);

        TextField nameInput = new TextField();
        nameInput.setLayoutX(1350);
        nameInput.setLayoutY(50);

        TextField spouseInput = new TextField();
        spouseInput.setLayoutX(1350);
        spouseInput.setLayoutY(250);
        spouseInput.setVisible(false);

        Label spouseLabel = new Label("Enter New Spouse:");
        spouseLabel.setLayoutX(1225);
        spouseLabel.setLayoutY(250);
        spouseLabel.setVisible(false);

        TextField motherInput = new TextField();
        motherInput.setLayoutX(1350);
        motherInput.setLayoutY(350);
        motherInput.setVisible(false);

        Label motherLabel = new Label("Enter New Mother:");
        motherLabel.setLayoutX(1225);
        motherLabel.setLayoutY(350);
        motherLabel.setVisible(false);

        TextField fatherInput = new TextField();
        fatherInput.setLayoutX(1350);
        fatherInput.setLayoutY(450);
        fatherInput.setVisible(false);

        Label fatherLabel = new Label("Enter New Father:");
        fatherLabel.setLayoutX(1225);
        fatherLabel.setLayoutY(450);
        fatherLabel.setVisible(false);

        TextField childInput = new TextField();
        childInput.setLayoutX(1350);
        childInput.setLayoutY(550);
        childInput.setVisible(false);

        Label childLabel = new Label("Enter New Child:");
        childLabel.setLayoutX(1225);
        childLabel.setLayoutY(550);
        childLabel.setVisible(false);

        Button spouseButton = new Button("Add Spouse");
        spouseButton.setLayoutX(1300);
        spouseButton.setLayoutY(200);
        spouseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                spouseInput.setVisible(true);
                spouseLabel.setVisible(true);

                motherInput.setVisible(false);
                motherInput.setText("");
                motherLabel.setVisible(false);

                fatherInput.setVisible(false);
                fatherInput.setText("");
                fatherLabel.setVisible(false);

                childInput.setVisible(false);
                childInput.setText("");
                childLabel.setVisible(false);
            }
        });

        Button motherButton = new Button("Add Mother");
        motherButton.setLayoutX(1300);
        motherButton.setLayoutY(300);
        motherButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                spouseInput.setVisible(false);
                spouseInput.setText("");
                spouseLabel.setVisible(false);

                motherInput.setVisible(true);
                motherLabel.setVisible(true);

                fatherInput.setVisible(false);
                fatherInput.setText("");
                fatherLabel.setVisible(false);

                childInput.setVisible(false);
                childInput.setText("");
                childLabel.setVisible(false);
            }
        });

        Button fatherButton = new Button("Add Father");
        fatherButton.setLayoutX(1300);
        fatherButton.setLayoutY(400);
        fatherButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                spouseInput.setVisible(false);
                spouseInput.setText("");
                spouseLabel.setVisible(false);

                motherInput.setVisible(false);
                motherInput.setText("");
                motherLabel.setVisible(false);

                fatherInput.setVisible(true);
                fatherLabel.setVisible(true);

                childInput.setVisible(false);
                childInput.setText("");
                childLabel.setVisible(false);
            }
        });

        Button childButton = new Button("Add Child");
        childButton.setLayoutX(1300);
        childButton.setLayoutY(500);
        childButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                spouseInput.setVisible(false);
                spouseInput.setText("");
                spouseLabel.setVisible(false);

                motherInput.setVisible(false);
                motherInput.setText("");
                motherLabel.setVisible(false);

                fatherInput.setVisible(false);
                fatherInput.setText("");
                fatherLabel.setVisible(false);

                childInput.setVisible(true);
                childLabel.setVisible(true);
            }
        });

        Button addButton = new Button("Add Member");
        addButton.setLayoutX(1300);
        addButton.setLayoutY(100);
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

                        myFamily.setRootMember(curMember);

                        break;
                    }
                }

                if(curMember == null)
                {
                    curMember = new Member(nameInput.getText());
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

                    if(curMember.hasFather())
                    {
                        curMember.getFather().addSpouse(curMemberRelative);
                        curMemberRelative.addSpouse(curMember.getFather());
                    }

                    myFamily.addMember(curMemberRelative);
                }

                if(!(fatherInput.getText().equals("")))
                {
                    curMemberRelative = new Member(fatherInput.getText());
                    
                    curMember.addFather(curMemberRelative);
                    curMemberRelative.addChild(curMember);

                    if(curMember.hasMother())
                    {
                        curMember.getMother().addSpouse(curMemberRelative);
                        curMemberRelative.addSpouse(curMember.getMother());
                    }

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

        Button removeButton = new Button("Remove Member");
        removeButton.setLayoutX(1300);
        removeButton.setLayoutY(575);
        removeButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                for(int count = 0; count < myFamily.getSize(); count++)
                {
                    if(myFamily.getMember(count).getName().equals(nameInput.getText()))
                    {
                        pane.getChildren().remove(myFamily.getMember(count).getButton());
                        myFamily.removeMember(myFamily.getMember(count));

                        break;
                    }
                }
            }
        });

        Button removeSpouseButton = new Button("Remove Spouse");
        removeSpouseButton.setLayoutX(1240);
        removeSpouseButton.setLayoutY(625);
        removeSpouseButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
            }
        });

        Button removeMotherButton = new Button("Remove Mother");
        removeMotherButton.setLayoutX(1240);
        removeMotherButton.setLayoutY(665);
        removeMotherButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
            }
        });

        Button removeFatherButton = new Button("Remove Father");
        removeFatherButton.setLayoutX(1360);
        removeFatherButton.setLayoutY(665);
        removeFatherButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
            }
        });

        Button removeChildButton = new Button("Remove Child");
        removeChildButton.setLayoutX(1360);
        removeChildButton.setLayoutY(625);
        removeChildButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) 
            {
                // Stuff...
            }
        });

        pane.getChildren().add(dividingLine1);
        pane.getChildren().add(dividingLine2);
        pane.getChildren().add(dividingLine3);
        pane.getChildren().add(addButton);
        pane.getChildren().add(nameLabel);
        pane.getChildren().add(nameInput);
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
        pane.getChildren().add(removeButton);
        pane.getChildren().add(removeSpouseButton);
        pane.getChildren().add(removeMotherButton);
        pane.getChildren().add(removeFatherButton);
        pane.getChildren().add(removeChildButton);

        scene = new Scene(pane, 1500, 700);
        window.setScene(scene);
        window.show();
    }

    public static void drawMembers(Family familyTree, Member root)
    {
        if(!(root.getDrawnFlag()))
        {
            Button memButton = new Button(root.getName());
            memButton.setLayoutX(500);
            memButton.setLayoutY(275);

            pane.getChildren().add(memButton);
            scene.snapshot(null);

            root.setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.setButton(memButton);
            pane.getChildren().add(root.getButton());

            root.hasBeenDrawn(true);
        }

        if(root.hasMother() && !(root.getMother().getDrawnFlag()))
        {
            Button memButton = new Button(root.getMother().getName());
            pane.getChildren().add(memButton);
            scene.snapshot(null);

            memButton.setLayoutX(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2) - 100 - memButton.getWidth());
            memButton.setLayoutY(root.getButton().getLayoutY() - root.getButton().getHeight() - 232);

            root.getMother().setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getMother().setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getMother().setButton(memButton);
            pane.getChildren().add(root.getMother().getButton());

            scene.snapshot(null);
            Line memLine1 = new Line(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY(), root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 216);
            Line memLine2 = new Line(memLine1.getEndX(), memLine1.getEndY(), root.getMother().getButton().getLayoutX() + (root.getMother().getButton().getWidth() / 2), memLine1.getEndY());
            pane.getChildren().add(memLine1);
            pane.getChildren().add(memLine2);
            pane.getChildren().add(root.getTopLine());
            pane.getChildren().add(root.getMother().getBottomLine());

            root.getMother().hasBeenDrawn(true);

            drawMembers(familyTree, root.getMother());
        }

        if(root.hasFather() && !(root.getFather().getDrawnFlag()))
        {
            Button memButton = new Button(root.getFather().getName());
            pane.getChildren().add(memButton);
            scene.snapshot(null);

            memButton.setLayoutX(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2) + 100);
            memButton.setLayoutY(root.getButton().getLayoutY() - root.getButton().getHeight() - 232);

            root.getFather().setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getFather().setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getFather().setButton(memButton);
            pane.getChildren().add(root.getFather().getButton());

            scene.snapshot(null);
            Line memLine1 = new Line(root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY(), root.getButton().getLayoutX() + (root.getButton().getWidth() / 2), root.getButton().getLayoutY() - 216);
            Line memLine2 = new Line(memLine1.getEndX(), memLine1.getEndY(), root.getFather().getButton().getLayoutX() + (root.getFather().getButton().getWidth() / 2), memLine1.getEndY());
            pane.getChildren().add(memLine1);
            pane.getChildren().add(memLine2);
            pane.getChildren().add(root.getTopLine());
            pane.getChildren().add(root.getFather().getBottomLine());

            root.getFather().hasBeenDrawn(true);

            drawMembers(familyTree, root.getFather());
        }

        if(root.hasSpouse() && !(root.getSpouse().getDrawnFlag()))
        {
            Button memButton = new Button(root.getSpouse().getName());
            pane.getChildren().add(memButton);
            scene.snapshot(null);

            memButton.setLayoutX(root.getButton().getLayoutX() + root.getButton().getWidth() + 200);
            memButton.setLayoutY(root.getButton().getLayoutY());

            root.getSpouse().setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getSpouse().setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getSpouse().setButton(memButton);
            pane.getChildren().add(root.getSpouse().getButton());

            scene.snapshot(null);
            Line memLine = new Line(root.getBottomLine().getEndX(), root.getBottomLine().getEndY(), root.getSpouse().getBottomLine().getEndX(), root.getSpouse().getBottomLine().getEndY());
            pane.getChildren().add(memLine);
            pane.getChildren().add(root.getBottomLine());
            pane.getChildren().add(root.getSpouse().getBottomLine());

            root.getSpouse().hasBeenDrawn(true);

            drawMembers(familyTree, root.getSpouse());
        }

        if(root.hasChildren())
        {
            for(int count = 0; count < root.getChildCount(); count++)
            {
                if(!root.getChild(count).getDrawnFlag())
                {
                    if(root.getChildCount() == 1)
                    {
                        Button memButton = new Button(root.getChild(count).getName());
                        pane.getChildren().add(memButton);
                        scene.snapshot(null);

                        memButton.setLayoutX(((root.getButton().getLayoutX() + root.getButton().getWidth() + root.getSpouse().getButton().getLayoutX()) / 2) - (memButton.getWidth() / 2));
                        memButton.setLayoutY(root.getButton().getLayoutY() + root.getButton().getHeight() + 232);

                        root.getChild(count).setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
                        root.getChild(count).setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
                        pane.getChildren().remove(memButton);

                        root.getChild(count).setButton(memButton);
                        pane.getChildren().add(root.getChild(count).getButton());

                        scene.snapshot(null);
                        Line memLine = new Line(((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 16, ((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 216); 
                        pane.getChildren().add(memLine);
                        pane.getChildren().add(root.getChild(count).getTopLine());

                        root.getChild(count).hasBeenDrawn(true);
                    }

                    else if(root.getChildCount() % 2 == 0)
                    {
                        addEvenChildren(root, root.getChildCount());
                    }

                    else if(root.getChildCount() % 2 == 1)
                    {
                        addEvenChildren(root, root.getChildCount() - 1);

                        Button memButton = new Button(root.getChild(root.getChildCount() - 1).getName());
                        pane.getChildren().add(memButton);
                        scene.snapshot(null);
                        
                        memButton.setLayoutX(((root.getButton().getLayoutX() + root.getButton().getWidth() + root.getSpouse().getButton().getLayoutX()) / 2) - (memButton.getWidth() / 2));
                        memButton.setLayoutY(root.getChild(0).getButton().getLayoutY());
                        
                        root.getChild(root.getChildCount() - 1).setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
                        root.getChild(root.getChildCount() - 1).setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
                        pane.getChildren().remove(memButton);

                        root.getChild(root.getChildCount() - 1).setButton(memButton);
                        pane.getChildren().add(root.getChild(root.getChildCount() - 1).getButton());
                        pane.getChildren().add(root.getChild(root.getChildCount() - 1).getTopLine());

                        root.getChild(count).hasBeenDrawn(true);
                    }
                }

                drawMembers(familyTree, root.getChild(count));
            }
        }
    }

    public static void addEvenChildren(Member root, int childAmt)
    {
        for(int i = 0; i < childAmt; i++)
        {
            pane.getChildren().remove(root.getChild(i).getTopLine());
            pane.getChildren().remove(root.getChild(i).getButton());

            Button memButton = new Button(root.getChild(i).getName());
            pane.getChildren().add(memButton);
            scene.snapshot(null);
            
            if(i % 2 == 0)
            {
                memButton.setLayoutX(root.getButton().getLayoutX() - (i * 50));
                memButton.setLayoutY(root.getButton().getLayoutY() + root.getButton().getHeight() + 232);
            }

            else
            {
                memButton.setLayoutX(root.getSpouse().getButton().getLayoutX() + (i * 50));
                memButton.setLayoutY(root.getSpouse().getButton().getLayoutY() + root.getSpouse().getButton().getHeight() + 232);
            }

            root.getChild(i).setTopLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() - 16));
            root.getChild(i).setBottomLine(new Line(memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight(), memButton.getLayoutX() + (memButton.getWidth() / 2), memButton.getLayoutY() + memButton.getHeight() + 16));
            pane.getChildren().remove(memButton);

            root.getChild(i).setButton(memButton);
            pane.getChildren().add(root.getChild(i).getButton());
            pane.getChildren().add(root.getChild(i).getTopLine());

            root.getChild(i).hasBeenDrawn(true);
        }

        scene.snapshot(null);
        Line memLine1 = new Line(((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 16, ((root.getButton().getLayoutX() + root.getButton().getWidth()) + root.getSpouse().getButton().getLayoutX()) / 2, root.getButton().getLayoutY() + root.getButton().getHeight() + 216); 
        Line memLine2 = new Line(root.getChild(childAmt - 2).getButton().getLayoutX() + (root.getChild(childAmt - 2).getButton().getWidth() / 2), root.getChild(childAmt - 2).getButton().getLayoutY() - 16, root.getChild(childAmt - 1).getButton().getLayoutX() + (root.getChild(childAmt - 1).getButton().getWidth() / 2), root.getChild(childAmt - 1).getButton().getLayoutY() - 16); 
        pane.getChildren().add(memLine1);
        pane.getChildren().add(memLine2);
    }
}