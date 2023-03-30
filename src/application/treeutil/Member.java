package treelinecalculationsalgorithm;

import java.util.ArrayList;
import javafx.scene.control.*;

// Member Class
public class Member 
{
    private String name;                                    // First and Last Name of the Member
    private String biography;                               // Holds extra information about the Member
    private ArrayList<Member> children = new ArrayList<>(); // List of Children that the Member has
    private int childCount;                                 // Count of children that the Member has
    private Member mother;                                  // First Parent of the Member
    private Member father;                                  // Second Parent of the Member
    private Member spouse;                                  // Spouse of the Member
    private boolean drawnFlag;                              // Has the member been drawn on the GUI?
    private Button memberButton;                            // Button tied to the Member that is displayed on the GUI

    // Default Constructor
    public Member()
    {
        this.name         = "";
        this.biography    = "";
        this.childCount   = 0;
        this.mother       = null;
        this.father       = null;
        this.spouse       = null;
        this.drawnFlag    = false;
        this.memberButton = null;
    }

    // Constructor with a Name and Biography parameters
    public Member(String name, String biography)
    {
        this.name         = name;
        this.biography    = biography;
        this.childCount   = 0;
        this.mother       = null;
        this.father       = null;
        this.spouse       = null;
        this.drawnFlag    = false;
        this.memberButton = null;
    }

    // Get Member's Name
    public String getName()
    {
        return this.name;
    }

    // Set Member's Name
    public void setName(String newName)
    {
        this.name = newName;
    }

    // Get Member's Biography
    public String getBiography()
    {
        return this.biography;
    }

    // Set Member's Biography
    public void setBiography(String newBiography)
    {
        this.biography = newBiography;
    }

    // Adds a Child
    public void addChild(Member newChild)
    {
        this.children.add(childCount, newChild);
        childCount++;
    }

    // Removes a Child
    public void removeChild(Member targetChild)
    {
        for(int count = 0; count < childCount; count++)
        {
            if(children.get(count) == targetChild)
            {
                children.remove(count);
            }
        }
    }

    // Clears all Children
    public void clearChidren()
    {
        children.removeAll(children);
    }

    // Gets all Children
    public ArrayList<Member> getChildren()
    {
        return this.children;
    }

    public Member getChild(Member targetChild)
    {
        if(children.contains(targetChild))
        {
            for(int count = 0; count < childCount; count++)
            {
                if(children.get(count) == targetChild)
                {
                    return children.get(count);
                }
            }
        }

        return null;
    }

    public Member getChild(int index)
    {
        return children.get(index);
    }

    public int getChildCount()
    {
        return this.childCount;
    }

    public boolean hasChildren()
    {
        if(childCount != 0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    // Adds a Parent
    public void addMother(Member newMother)
    {
        if(mother == null)
        {
            this.mother = newMother;
        }

        else
        {
            System.out.println("Can't add another mother!\n");
        }
    }

    // Adds a Parent
    public void addFather(Member newFather)
    {
        if(father == null)
        {
            this.father = newFather;
        }

        else
        {
            System.out.println("Can't add another father!\n");
        }
    }

    // Removes a Parent
    public void removeMother()
    {
        this.mother = null;
    }

    // Removes a Parent
    public void removeFather()
    {
        this.father = null;
    }

    // Gets both Parents
    public Member getMother()
    {
        return this.mother;
    }

    // Gets both Parents
    public Member getFather()
    {
        return this.father;
    }

    public boolean hasMother()
    {
        if(mother != null)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean hasFather()
    {
        if(father != null)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    // Adds a Spouse
    public void addSpouse(Member newSpouse)
    {
        this.spouse = newSpouse;
    }

    // Removes a Spouse
    public void removeSpouse()
    {
        this.spouse = null;
    }

    // Gets the Spouse
    public Member getSpouse()
    {
        return this.spouse;
    }

    public boolean hasSpouse()
    {
        if(spouse != null)
        {
            return true;
        }

        else
        {
            return false;
        }
    }

    public boolean getDrawnFlag()
    {
        return this.drawnFlag;
    }

    public void hasBeenDrawn(boolean flag)
    {
        this.drawnFlag = flag;
    }

    public Button getButton()
    {
        return this.memberButton;
    }

    public void setButton(Button newButton)
    {
        this.memberButton = newButton;
    }
}