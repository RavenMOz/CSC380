package application.swing.projectinterface.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import application.data.Family;
import application.data.Member;
import application.swing.main.Main;

public class FamilyTree
{
	
    static JLabel currentMemberLabel = new JLabel();

    public static void setupFamilyPanel()
    {
    	Main.treePanel = new FamilyTreePanel();

        /****************************************************************************************************************************/

//        JFrame frame = new JFrame("Family Tree");
//        frame.getContentPane();
/*
        JLabel nameLabel = new JLabel("Enter Member Name:");
        nameLabel.setBounds(1225, 10, nameLabel.getPreferredSize().width, nameLabel.getPreferredSize().height);

        JTextField nameInput = new JTextField();
        nameInput.setBounds(1350, 10, nameInput.getPreferredSize().width, nameInput.getPreferredSize().height);

        JLabel currentLabel = new JLabel("CURRENT MEMBER:");
        currentLabel.setBounds(1215, 110, currentLabel.getPreferredSize().width, currentLabel.getPreferredSize().height);

        currentMemberLabel = new JLabel();
        currentMemberLabel.setBounds(1225, 160, currentMemberLabel.getPreferredSize().width, currentMemberLabel.getPreferredSize().height);

        JTextField spouseInput = new JTextField();
        spouseInput.setBounds(1350, 310, spouseInput.getPreferredSize().width, spouseInput.getPreferredSize().height);

        JLabel spouseLabel = new JLabel("Enter New Spouse:");
        spouseLabel.setBounds(1225, 310, spouseLabel.getPreferredSize().width, spouseLabel.getPreferredSize().height);

        JTextField parentInput = new JTextField();
        parentInput.setBounds(1350, 410, parentInput.getPreferredSize().width, parentInput.getPreferredSize().height);

        JLabel parentLabel = new JLabel("Enter New Parent:");
        parentLabel.setBounds(1225, 410, parentLabel.getPreferredSize().width, parentLabel.getPreferredSize().height);

        JTextField childInput = new JTextField();
        childInput.setBounds(1350, 510, childInput.getPreferredSize().width, childInput.getPreferredSize().height);

        JLabel childLabel = new JLabel("Enter New Child:");
        childLabel.setBounds(1225, 510, childLabel.getPreferredSize().width, childLabel.getPreferredSize().height);

        JButton spouseButton = new JButton("Add Spouse");
        spouseButton.setBounds(1300, 260, spouseButton.getPreferredSize().width, spouseButton.getPreferredSize().height);
        spouseButton.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent event)
            {
                spouseButtonPress(myFamily, spouseInput);
            }
        });

        JButton parentButton = new JButton("Add Parent");
        parentButton.setBounds(1300, 360, parentButton.getPreferredSize().width, parentButton.getPreferredSize().height);
        parentButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                parentButtonPress(myFamily, parentInput);
            }
        });

        JButton childButton = new JButton("Add Child");
        childButton.setBounds(1300, 460, childButton.getPreferredSize().width, childButton.getPreferredSize().height);
        childButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                childButtonPress(myFamily, childInput);
            }
        });

        JButton addButton = new JButton("Add Member");
        addButton.setBounds(1300, 60, addButton.getPreferredSize().width, addButton.getPreferredSize().height);
        addButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                addButtonPress(myFamily, nameInput);
            }
        });

        JButton removeSpouseButton = new JButton("Remove Spouse");
        removeSpouseButton.setBounds(1240, 625, removeSpouseButton.getPreferredSize().width, removeSpouseButton.getPreferredSize().height);
        removeSpouseButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) 
            {
                removeSpouseButtonPress(myFamily);
            }
        });

        JButton removeParent1Button = new JButton("Remove Parent 1");
        removeParent1Button.setBounds(1240, 665, removeParent1Button.getPreferredSize().width, removeParent1Button.getPreferredSize().height);
        removeParent1Button.addActionListener(new ActionListener() {
    
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                removeParent1ButtonPress(myFamily);
             }
        });

        JButton removeParent2Button = new JButton("Remove Parent 2");
        removeParent2Button.setBounds(1360, 665, removeParent2Button.getPreferredSize().width, removeParent2Button.getPreferredSize().height);
        removeParent2Button.addActionListener(new ActionListener() {
      
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                removeParent2ButtonPress(myFamily);
            }
        });

        JTextField removeChildInput = new JTextField();
        removeChildInput.setBounds(1275, 580, removeChildInput.getPreferredSize().width, removeChildInput.getPreferredSize().height);

        JButton removeChildButton = new JButton("Remove Child");
        removeChildButton.setBounds(1360, 625, removeChildButton.getPreferredSize().width, removeChildButton.getPreferredSize().height);
        removeChildButton.addActionListener(new ActionListener() {
       
            @Override
            public void actionPerformed(ActionEvent event) 
            {
                removeChildButtonPress(myFamily);
            }
        });
*/
    	Main.treePanel.setBackground(Color.black);
    	Main.treePanel.setLayout(null);
    	Main.treePanel.setPreferredSize(new Dimension(2000, 2000));
/*        
        panel.add(addButton);
        panel.add(nameLabel);
        panel.add(nameInput);
        panel.add(currentLabel);
        panel.add(currentMemberLabel);
        panel.add(spouseLabel);
        panel.add(spouseInput);
        panel.add(spouseButton);
        panel.add(parentLabel);
        panel.add(parentInput);
        panel.add(parentButton);
        panel.add(childLabel);
        panel.add(childInput);
        panel.add(childButton);
        panel.add(removeSpouseButton);
        panel.add(removeParent1Button);
        panel.add(removeParent2Button);
        panel.add(removeChildInput);
        panel.add(removeChildButton);
*/
    	Main.treePanel.setMinimumSize(new Dimension(1000,1000));
    }
    
    public static void draw() {
        drawMembers(Main.activeFamily, Main.activeFamily.root);
    }

    public static void drawMembers(Family familyTree, Member root)
    {
        if(!(root.getDrawnFlag()))
        {
            JButton memButton = new JButton(root.getName());
            memButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    familyTree.setRootMember(root);
                    currentMemberLabel.setText(root.getName());
                }
            });

            memButton.setBounds(500, 275, memButton.getPreferredSize().width, memButton.getPreferredSize().height);

            Main.treePanel.add(memButton);

            root.setTopLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() - 16));
            root.setBottomLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight() + 16));
            Main.treePanel.remove(memButton);

            root.setButton(memButton);
            Main.treePanel.add(root.getButton());

            root.hasBeenDrawn(true);
        }

        if(root.hasParentOne() && !(root.getParentOne().getDrawnFlag()))
        {
            JButton memButton = new JButton(root.getParentOne().getName());
            memButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getParentOne());
                    currentMemberLabel.setText(root.getParentOne().getName());
                }
            });

            Main.treePanel.add(memButton);

            memButton.setBounds(root.getButton().getX() + (root.getButton().getWidth() / 2) - 100 - memButton.getWidth(), root.getButton().getY() - root.getButton().getHeight() - 216, memButton.getPreferredSize().width, memButton.getPreferredSize().height);

            root.getParentOne().setTopLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() - 16));
            root.getParentOne().setBottomLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight() + 16));
            Main.treePanel.remove(memButton);

            root.getParentOne().setButton(memButton);
            Main.treePanel.add(root.getParentOne().getButton());

            Line memLine1 = new Line(root.getTopLine().getEndX(), root.getTopLine().getEndY(), root.getButton().getX() + (root.getButton().getWidth() / 2), root.getButton().getY() - 200);
            Line memLine2 = new Line(root.getParentOne().getBottomLine().getEndX(), root.getParentOne().getBottomLine().getEndY(), memLine1.getEndX(), memLine1.getEndY());
            
            if(root.getLine(memLine1.getStartX(), memLine1.getStartY()) == null)
            {
                root.addLine(memLine1);
                root.getParentOne().addLine(memLine1);
                Main.treePanel.addLine(root.getLine(memLine1.getStartX(), memLine1.getStartY()));
            }
            
            else if(root.getLine(memLine1.getStartX(), memLine1.getStartY()).getParent() != Main.treePanel)
            {
            	Main.treePanel.addLine(root.getLine(memLine1.getStartX(), memLine1.getStartY()));
            }
            
            root.addLine(memLine2);
            root.getParentOne().addLine(memLine2);
            Main.treePanel.addLine(root.getLine(memLine2));
            
            if(root.getTopLine().getParent() != Main.treePanel)
            {
            	Main.treePanel.addLine(root.getTopLine());
            }
            
            Main.treePanel.addLine(root.getParentOne().getBottomLine());

            root.getParentOne().hasBeenDrawn(true);

            drawMembers(familyTree, root.getParentOne());
        }

        if(root.hasParentTwo() && !(root.getParentTwo().getDrawnFlag()))
        {
            JButton memButton = new JButton(root.getParentTwo().getName());
            memButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getParentTwo());
                    currentMemberLabel.setText(root.getParentTwo().getName());
                }
            });

            Main.treePanel.add(memButton);

            memButton.setBounds(root.getButton().getX() + (root.getButton().getWidth() / 2) + 100, root.getButton().getY() - root.getButton().getHeight() - 216, memButton.getPreferredSize().width, memButton.getPreferredSize().height);

            root.getParentTwo().setTopLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() - 16));
            root.getParentTwo().setBottomLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight() + 16));
            Main.treePanel.remove(memButton);

            root.getParentTwo().setButton(memButton);
            Main.treePanel.add(root.getParentTwo().getButton());

            Line memLine1 = new Line(root.getTopLine().getEndX(), root.getTopLine().getEndY(), root.getButton().getX() + (root.getButton().getWidth() / 2), root.getButton().getY() - 200);
            Line memLine2 = new Line(memLine1.getEndX(), memLine1.getEndY(), root.getParentTwo().getButton().getX() + (root.getParentTwo().getButton().getWidth() / 2), memLine1.getEndY());
            
            if(root.getLine(memLine1.getStartX(), memLine1.getStartY()) == null)
            {
                root.addLine(memLine1);
                root.getParentTwo().addLine(memLine1);
                Main.treePanel.addLine(root.getLine(memLine1.getStartX(), memLine1.getStartY()));
            }

            else if(root.getLine(memLine1.getStartX(), memLine1.getStartY()).getParent() != Main.treePanel)
            {
            	Main.treePanel.addLine(root.getLine(memLine1));
            }
            
            root.addLine(memLine2);
            root.getParentTwo().addLine(memLine2);
            Main.treePanel.addLine(root.getLine(memLine2));
            
            if(root.getTopLine().getParent() != Main.treePanel)
            {
            	Main.treePanel.addLine(root.getTopLine());
            }
            
            Main.treePanel.addLine(root.getParentTwo().getBottomLine());

            root.getParentTwo().hasBeenDrawn(true);

            drawMembers(familyTree, root.getParentTwo());
        }

        if(root.hasSpouse() && !(root.getSpouse().getDrawnFlag()))
        {
            JButton memButton = new JButton(root.getSpouse().getName());
            memButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getSpouse());
                    currentMemberLabel.setText(root.getSpouse().getName());
                }
            });

            Main.treePanel.add(memButton);

            memButton.setBounds(root.getButton().getX() + root.getButton().getWidth() + 300, root.getButton().getY(), memButton.getPreferredSize().width, memButton.getPreferredSize().height);

            root.getSpouse().setTopLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() - 16));
            root.getSpouse().setBottomLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight() + 16));
            Main.treePanel.remove(memButton);

            root.getSpouse().setButton(memButton);
            Main.treePanel.add(root.getSpouse().getButton());

            Line memLine1 = new Line(root.getBottomLine().getEndX(), root.getBottomLine().getEndY(), (root.getBottomLine().getEndX() + root.getSpouse().getBottomLine().getEndX()) / 2, root.getSpouse().getBottomLine().getEndY());
            Line memLine2 = new Line(root.getSpouse().getBottomLine().getEndX(), root.getSpouse().getBottomLine().getEndY(), memLine1.getEndX(), memLine1.getEndY());
            root.addLine(memLine1);
            root.getSpouse().addLine(memLine2);
            Main.treePanel.addLine(root.getLine(memLine1));
            Main.treePanel.addLine(root.getSpouse().getLine(memLine2));
            
            if(root.getBottomLine().getParent() != Main.treePanel)
            {
            	Main.treePanel.addLine(root.getBottomLine());
            }
            
            Main.treePanel.addLine(root.getSpouse().getBottomLine());

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
                    	System.out.println(root.getChildren());
                        JButton memButton = new JButton(root.getChild(count).getName());
                        memButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent event) 
                            {
                                familyTree.setRootMember(root.getChild(memButton.getText()));
                                currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                                
                            }
                        });

                        Main.treePanel.add(memButton);
                    
                        try {
                        	Line memLine = new Line(root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY() + 216); 
                            root.addLine(memLine);
                            memButton.setBounds(memLine.getEndX() - (memButton.getWidth() / 2), memLine.getEndY() + 16, memButton.getPreferredSize().width, memButton.getPreferredSize().height);

                            root.getChild(count).setTopLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() - 16));
                            root.getChild(count).setBottomLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight() + 16));
                            Main.treePanel.remove(memButton);

                            root.getChild(count).setButton(memButton);
                            Main.treePanel.add(root.getChild(count).getButton());

                            Main.treePanel.addLine(root.getLine(memLine));
                            Main.treePanel.addLine(root.getChild(count).getTopLine());

                            root.getChild(count).hasBeenDrawn(true);
                        } catch (Exception e) {
                        	e.printStackTrace();
                        }
                        

                        
                    }

                    else if(root.getChildCount() % 2 == 0)
                    {
                        addEvenChildren(familyTree, root, root.getChildCount());
                    }

                    else if(root.getChildCount() % 2 == 1)
                    {
                        addEvenChildren(familyTree, root, root.getChildCount() - 1);

                        JButton memButton = new JButton(root.getChild(root.getChildCount() - 1).getName());
                        memButton.addActionListener(new ActionListener() {

                            @Override
                            public void actionPerformed(ActionEvent event) 
                            {
                                familyTree.setRootMember(root.getChild(memButton.getText()));
                                currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                            }
                        });

                        Main.treePanel.add(memButton);
                        
                        memButton.setBounds(root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX() - (memButton.getWidth() / 2), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY() + 232, memButton.getPreferredSize().width, memButton.getPreferredSize().height);

                        root.getChild(root.getChildCount() - 1).setTopLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() - 16));
                        root.getChild(root.getChildCount() - 1).setBottomLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight() + 16));
                        Main.treePanel.remove(memButton);

                        root.getChild(root.getChildCount() - 1).setButton(memButton);
                        Main.treePanel.add(root.getChild(root.getChildCount() - 1).getButton());
                        Main.treePanel.addLine(root.getChild(root.getChildCount() - 1).getTopLine());

                        root.getChild(count).hasBeenDrawn(true);
                    }
                }

                drawMembers(familyTree, root.getChild(count));
            }
        }
    }

    public static void addEvenChildren(Family familyTree, Member root, int childAmt)
    {
        Line memLine = new Line(root.getBottomLine().getEndX(), root.getBottomLine().getEndY(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndX(), root.getLine(root.getBottomLine().getEndX(), root.getBottomLine().getEndY()).getEndY() + 216);

        for(int i = 0; i < childAmt; i++)
        {
        	Main.treePanel.removeLine(root.getChild(i).getTopLine());
        	Main.treePanel.remove(root.getChild(i).getButton());

            JButton memButton = new JButton(root.getChild(i).getName());
            memButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent event) 
                {
                    familyTree.setRootMember(root.getChild(memButton.getText()));
                    currentMemberLabel.setText(root.getChild(memButton.getText()).getName());
                }
            });

            Main.treePanel.add(memButton);

            if(i % 2 == 0)
            {
                memButton.setBounds(memLine.getEndX() - ((i + 1) * 150) - memButton.getWidth(), memLine.getEndY() + 16, memButton.getPreferredSize().width, memButton.getPreferredSize().height);
            }

            else
            {
                memButton.setBounds(memLine.getEndX() + (i * 150), memLine.getEndY() + 16, memButton.getPreferredSize().width, memButton.getPreferredSize().height);
            }

            root.getChild(i).setTopLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() - 16));
            root.getChild(i).setBottomLine(new Line(memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight(), memButton.getX() + (memButton.getWidth() / 2), memButton.getY() + memButton.getHeight() + 16));
            Main.treePanel.remove(memButton);

            root.getChild(i).setButton(memButton);
            Main.treePanel.add(root.getChild(i).getButton());
            Main.treePanel.addLine(root.getChild(i).getTopLine());

            root.getChild(i).hasBeenDrawn(true);
        }

        Line memLine2 = new Line(root.getChild(childAmt - 2).getButton().getX() + (root.getChild(childAmt - 2).getButton().getWidth() / 2), root.getChild(childAmt - 2).getButton().getY() - 16, root.getChild(childAmt - 1).getButton().getX() + (root.getChild(childAmt - 1).getButton().getWidth() / 2), root.getChild(childAmt - 1).getButton().getY() - 16); 
        root.addLine(memLine2);
        Main.treePanel.addLine(root.getLine(memLine2));
    }

    public static void removeMembers(Family familyTree, Member root, Member originalMember)
    {
        for(int i = 0; i < root.getLineCount(); i++)
        {
        	Main.treePanel.removeLine(root.getLine(i));
        }

        root.clearLines();
        
        if(root.getTopLine().getParent() == Main.treePanel)
        {
        	Main.treePanel.removeLine(root.getTopLine());
        }

        if(root.getBottomLine().getParent() == Main.treePanel)
        {
        	Main.treePanel.removeLine(root.getBottomLine());
        }
        
        Main.treePanel.remove(root.getButton());
        root.hasBeenDrawn(false);
       
        if(root.hasParentOne() && root.getParentOne() != originalMember && originalMember.getSpouse() != root.getParentOne())
        {
            Member curMember = root.getParentOne();

            root.removeParent(root.getParentOne());

            removeMembers(familyTree, curMember, originalMember);
        }

        if(root.hasParentTwo() && root.getParentTwo() != originalMember && originalMember.getSpouse() != root.getParentTwo())
        {
            Member curMember = root.getParentTwo();

            root.removeParent(root.getParentTwo());

            removeMembers(familyTree, curMember, originalMember);
        }
        
        if(root.hasChildren())
        {
            for(int i = 0; i < root.getChildCount(); i++)
            {
                if(root.getChild(i) != originalMember)
                {
                    if(originalMember.hasChildren())
                    {
                        if(originalMember.getChild(i) != root.getChild(i))
                        {
                            Member curMember = root.getChild(i);

                            root.removeChild(root.getChild(i));

                            removeMembers(familyTree, curMember, originalMember);
                        }
                    }
                }
            }

            root.clearChildren();
        }

        familyTree.removeMember(root);
    }

//    public static void spouseButtonPress(Family myFamily, JTextField spouseInput)
//    {
//        Member curMember         = null;
//        Member curMemberRelative = null;
//
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            if(myFamily.getMember(count).getName().equals(currentMemberLabel.getText()))
//            {
//                curMember = myFamily.getMember(count);
//
//                myFamily.setRootMember(curMember);
//
//                break;
//            }
//        }
//
//        if(curMember == null)
//        {
//            curMember = new Member(currentMemberLabel.getText());
//        }
//
//        if(!(spouseInput.getText().equals("")) && !(curMember.hasSpouse()))
//        {
//            curMemberRelative = new Member("", "", curMember, Main.activeFamily);
//
//            if(curMember.getSpouse().hasChildren())
//            {
//                for(int i = 0; i < curMember.getSpouse().getChildCount(); i++)
//                {
//                    curMember.addChild(curMember.getSpouse().getChild(i));
//                }
//            }
//
//            if(curMemberRelative.getSpouse().hasChildren())
//            {
//                for(int i = 0; i < curMemberRelative.getSpouse().getChildCount(); i++)
//                {
//                    curMemberRelative.addChild(curMemberRelative.getSpouse().getChild(i));
//                }
//            }
//
//            myFamily.addMember(curMemberRelative);
//        }
//
//        myFamily.addMember(curMember);
//
//        spouseInput.setText("");
//
//        drawMembers(myFamily, myFamily.getRootMember());
//    }
//
//    public static void parentButtonPress(Family myFamily, JTextField parentInput)
//    {
//        Member curMember         = null;
//        Member curMemberRelative = null;
//
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            if(myFamily.getMember(count).getName().equals(currentMemberLabel.getText()))
//            {
//                curMember = myFamily.getMember(count);
//
//                myFamily.setRootMember(curMember);
//
//                break;
//            }
//        }
//
//        if(curMember == null)
//        {
//            curMember = new Member(currentMemberLabel.getText());
//        }
//
//        if(!(parentInput.getText().equals("")) && !(curMember.hasTwoParents()))
//        {
//            curMemberRelative = new Member(parentInput.getText());
//            
//            curMember.addParent(curMemberRelative);
//            curMemberRelative.addChild(curMember);
//
//            if(curMember.hasTwoParents())
//            {
//                if(curMember.getParentOne() == curMemberRelative)
//                {
//                    curMember.getParentTwo().addSpouse(curMemberRelative);
//                    curMemberRelative.addSpouse(curMember.getParentTwo());
//                }
//
//                else if(curMember.getParentTwo() == curMemberRelative)
//                {
//                    curMember.getParentOne().addSpouse(curMemberRelative);
//                    curMemberRelative.addSpouse(curMember.getParentOne());
//                }
//            }
//
//            myFamily.addMember(curMemberRelative);
//        }
//
//        myFamily.addMember(curMember);
//
//        parentInput.setText("");
//
//        drawMembers(myFamily, myFamily.getRootMember());
//    }
//
//    public static void childButtonPress(Family myFamily, JTextField childInput)
//    {
//        Member curMember         = null;
//        Member curMemberRelative = null;
//
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            if(myFamily.getMember(count).getName().equals(currentMemberLabel.getText()))
//            {
//                curMember = myFamily.getMember(count);
//
//                myFamily.setRootMember(curMember);
//
//                break;
//            }
//        }
//
//        if(curMember == null)
//        {
//            curMember = new Member(currentMemberLabel.getText());
//        }
//
//        if(!(childInput.getText().equals("")))
//        {
//            curMemberRelative = new Member(childInput.getText());
//            
//            curMember.addChild(curMemberRelative);
//
//            if(curMember.hasSpouse())
//            {
//                curMember.getSpouse().addChild(curMemberRelative);
//            }
//            
//            curMemberRelative.addParent(curMember);
//            curMemberRelative.addParent(curMember.getSpouse());
//            
//            myFamily.addMember(curMemberRelative);
//        }
//
//        myFamily.addMember(curMember);
//
//        childInput.setText("");
//
//        drawMembers(myFamily, myFamily.getRootMember());
//    }
//
//    public static void addButtonPress(Family myFamily, JTextField nameInput)
//    {
//        Member curMember = null;
//
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            if(myFamily.getMember(count).getName().equals(nameInput.getText()))
//            {
//                curMember = myFamily.getMember(count);
//
//                myFamily.setRootMember(curMember);
//
//                break;
//            }
//        }
//
//        if(curMember == null)
//        {
//            curMember = new Member(nameInput.getText());
//        }
//
//        myFamily.addMember(curMember);
//
//        currentMemberLabel.setText(curMember.getName());
//        nameInput.setText("");
//
//        drawMembers(myFamily, myFamily.getRootMember());
//    }
//
//    public static void removeSpouseButtonPress(Family myFamily)
//    {
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getSpouse().getName()))
//            {
//                myFamily.getRootMember().removeSpouse();
//
//                removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());
//
//                if(!(myFamily.getRootMember().hasChildren()))
//                {
//                    panel.removeLine(myFamily.getRootMember().getLine(myFamily.getRootMember().getBottomLine().getEndX(), myFamily.getRootMember().getBottomLine().getEndY()));
//                    panel.removeLine(myFamily.getRootMember().getBottomLine());
//                }
//
//                break;
//            }
//        }
//    }
//
//    public static void removeParent1ButtonPress(Family myFamily)
//    {
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getParentOne().getName()))
//            {
//                panel.removeLine(myFamily.getRootMember().getLine(myFamily.getRootMember().getParentOne().getBottomLine().getEndX(), myFamily.getRootMember().getParentOne().getBottomLine().getEndY()));
//
//                myFamily.getRootMember().removeParent(myFamily.getRootMember().getParentOne());
//
//                removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());
//
//                if(myFamily.getRootMember().hasNoParents())
//                {
//                    panel.removeLine(myFamily.getRootMember().getLine(myFamily.getRootMember().getTopLine().getEndX(), myFamily.getRootMember().getTopLine().getEndY()));
//                    panel.removeLine(myFamily.getRootMember().getTopLine());
//                }
//
//                break;
//            }
//        }
//    }
//
//    public static void removeParent2ButtonPress(Family myFamily)
//    {
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getParentTwo().getName()))
//            {
//                myFamily.getRootMember().removeParent(myFamily.getRootMember().getParentTwo());
//
//                removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());
//
//                panel.removeLine(myFamily.getRootMember().getLine(myFamily.getRootMember().getButton().getX() + (myFamily.getRootMember().getButton().getWidth() / 2), myFamily.getRootMember().getButton().getY() - 200));
//
//                if(myFamily.getRootMember().hasNoParents())
//                {
//                    panel.removeLine(myFamily.getRootMember().getLine(myFamily.getRootMember().getTopLine().getEndX(), myFamily.getRootMember().getTopLine().getEndY()));
//                    panel.removeLine(myFamily.getRootMember().getTopLine());
//                }
//
//                break;
//            }
//        }
//    }
//
//    public static void removeChildButtonPress(Family myFamily)
//    {
//        for(int count = 0; count < myFamily.getSize(); count++)
//        {
//            for(int i = 0; i < myFamily.getRootMember().getChildCount(); i++)
//            {
//                if(myFamily.getMember(count).getName().equals(myFamily.getRootMember().getChild(i).getName()))
//                {
//                    panel.removeLine(myFamily.getRootMember().getLine(myFamily.getRootMember().getChild(i).getTopLine().getEndX(), myFamily.getRootMember().getChild(i).getTopLine().getEndY() - 216));
//
//                    if(!(myFamily.getRootMember().hasSpouse()))
//                    {
//                        panel.removeLine(myFamily.getRootMember().getLine(myFamily.getRootMember().getBottomLine().getEndX(), myFamily.getRootMember().getBottomLine().getEndY()));
//                        panel.removeLine(myFamily.getRootMember().getBottomLine());
//                    }
//
//                    else
//                    {
//                        myFamily.getRootMember().getSpouse().removeChild(myFamily.getRootMember().getChild(i));
//                    }
//
//                    myFamily.getRootMember().removeChild(myFamily.getRootMember().getChild(i));
//
//                    removeMembers(myFamily, myFamily.getMember(count), myFamily.getRootMember());
//
//                    break;
//                }
//            }
//        }
//    }
}