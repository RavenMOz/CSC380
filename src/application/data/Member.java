package application.data;

import java.util.ArrayList;

import application.storage.SQLCommands;

public class Member {
    /* Updated Changes:
     *      o Created series of Has methods.
     *          I   hasSpouse()
     *          II  hasChildren()
     *          III hasParent()
     *      o Wrote the clearChildren() method.
     *      o Reintroduced the parent methods and variables.
     *      o Removed the mother and father variables and their associated methods.
     *      o Created two more Constructors, one with a given Name and one with the given Name AND Biography.
     *      o Created a childCount variable.
     *      o Applied Sam's Changes:
     *          - Family ID & Member ID
                - toString()
     */
    // Variables:
    String name;
    String biography;
    int bDay; int bMonth; int bYear;
    ArrayList<Member> children;
    int childCount;
    Member parentOne; Member parentTwo;
    Member spouse;
    public final long memberID;
    public final long familyID;

    /* Constructors:
     *      Emtpy
     *      Name
     *      Name & Bio
     *      Name, Bio & FamID
     *      The Weird One...
     */
    // Empty:
    public Member(){
        this.name = null;
        this.bDay = 1;
        this.bMonth = 1;
        this.bYear = 1;
        this.biography = null;
        this.parentOne = null;
        this.parentTwo = null;
        this.children = null;
        this.childCount = 0;
        this.spouse = null;
        this.familyID = 0;
        this.memberID = newID();

    }
    // Name:
    public Member( String iName ){
        this.name = iName;
        this.biography = null;
        this.bDay = 1;
        this.bMonth = 1;
        this.bYear = 1;
        this.parentOne = null;
        this.parentTwo = null;
        this.children = null;
        this.childCount = 0;
        this.spouse = null;
        this.familyID = 0;
        this.memberID = newID();
    }
    // Name & Biography:
    public Member ( String iName, String iBio ){
        this.name = iName;
        this.biography = iBio;
        this.bDay = 1;
        this.bMonth = 1;
        this.bYear = 1;
        this.parentOne = null;
        this.parentTwo = null;
        this.children = null;
        this.childCount = 0;
        this.spouse = null;
        this.familyID = 0;
        this.memberID = newID();
    }
    // Name, Bio and FamID:
    public Member ( String iName, String iBiography, long famID ){
        this.name = iName;
        this.bDay = 1;
        this.bMonth = 1;
        this.bYear = 1;
        this.biography = iBiography;
        memberID = 
        familyID = famID;
        parentOne = null;
        parentTwo = null;
        children = new ArrayList<Member>();
        spouse = null;

    }
    // Used for adding a new child to an existing member
    public Member(Member p1, Member p2) {
    	this(newID(), p1.getFamilyID(), "", "", 1, 1, 1, p1, p2, null);
    }
    // Used for adding a new spouse to an existing member
    public Member(Member sps) {
    	this(newID(), sps.getFamilyID(), "", "", 1, 1, 1, null, null, sps);
    }
    // Retrieve a full member from database constructor
    public Member(long mID, long famID, String name2, String bio, int bDay2, int bMonth2, int bYear2, long p1,
                  long p2, long sps) {
        this.name = name2;
        this.bDay = bDay2;
        this.bMonth = bMonth2;
        this.bYear = bYear2;
        this.biography = bio;
        familyID = famID;
        memberID = mID;
        
		parentOne = SQLCommands.getMemberByID(p1);
		parentTwo = SQLCommands.getMemberByID(p2);
		spouse = SQLCommands.getMemberByID(sps);
		children = SQLCommands.getChildrenByParentID(memberID);

    }
    
 // Write a full member from dynamic
    public Member(long mID, long famID, String name2, String bio, int bDay2, int bMonth2, int bYear2, Member p1,
                  Member p2, Member sps) {
        this.name = name2;
        this.bDay = bDay2;
        this.bMonth = bMonth2;
        this.bYear = bYear2;
        this.biography = bio;
        familyID = famID;
        memberID = mID;
		parentOne = p1;
		parentTwo = p2;
		spouse = sps;
		children = SQLCommands.getChildrenByParentID(memberID);

		for (Family f : SQLCommands.activeFamilies) {
			if (f.getFamilyID() == famID) f.addMember(this);
		}
		
    }

    /* The Methods of this class are the following:
     *      Get = Fetches variable following the get section of the method's title.
     *      Set = Replaces a variable with a given input.
     *      Add = adds a value to a given variable.
     *      Remove = Removes (nulls?) a value from a variable.
     *      Clear = Clears an array.
     *      Has = Boolean methods that checks if a variable is filled in or not. [ NEW! ]
     *      toString = Prints a String.
    */
    // Get:
    public String getName(){ if ( name == null ) return "EMPTY"; else return this.name; }
    public String getBio(){ if ( biography == null ) return "EMPTY"; else return this.biography; }
    public int getBDay(){ return this.bDay; }
    public int getBMonth(){ return this.bMonth; }
    public int getBYear(){ return this.bYear; }
    public Member getParentOne(){ return this.parentOne; }
    public Member getParentTwo(){ return this.parentTwo; }
    public ArrayList<Member> getChildren(){ return this.children;  }
    public Member getChild( Member iChild ){
        if ( children.contains(iChild) ){
            for ( int counter = 0; counter < children.size(); counter++ ){
                if ( children.get(counter) == iChild ){
                    return children.get(counter);
                }
            }
        }
        return null;
    }
    public Member getChild( int index ){
        return children.get(index);
    }
    public Member getSpouse(){ return this.spouse; }
    public long getMemberID() { return memberID; }
    public long getFamilyID() { return familyID; }

    //Set:
    public void setName( String newName ){ this.name = newName; }
    public void setBio( String newBio ){ this.biography = newBio; }
    public void setBDay( int newBirthDay ){ this.bDay = newBirthDay; }
    public void setBMonth( int newBirthMonth ){ this.bMonth = newBirthMonth; }
    public void setBYear( int newBirthYear ){ this.bYear = newBirthYear; }
    public void setSpouse( Member newSpouse ){ this.spouse = newSpouse; }

    //Add:
    public void addChild( Member iChild ){ this.children.add(childCount,iChild); childCount++; } // NEW!
    public void addParent( Member newParent ){
        if ( parentOne == null ){ this.parentOne = newParent; }
        else if ( parentTwo == null ){ this.parentTwo = newParent; }
    } // NEW!

    //Remove:
    public void removeChild( Member iChild ){ children.remove(iChild); } // NEW!
    public void removeParent( Member iParent ){ if ( parentOne == iParent ){ parentOne = null; } else if ( parentTwo == iParent ) { parentTwo = null; } } // NEW!

    //Clear:
    public void clearChildren(){ this.children.removeAll(children); childCount = 0; } // NEW!

    //Has:
    public boolean hasParent(){ if ( parentOne != null ) { return true; } else return parentTwo != null; } // NEW!
    public boolean hasParentOne() { return parentOne != null; } // NEW!
    public boolean hasParentTwo() { return parentTwo != null; } // NEW!
    public boolean hasSpouse(){ return spouse != null; } // NEW!
    public boolean hasChildren(){ return children != null; } // NEW!

    //toString:
    public String toString(){ 
    	String s = "MemberID: "+memberID+ " | FamilyID: "+familyID+" | Name: "+name+" | Bio: "+biography;
    	if (spouse != null) s += " | Spouse: " + spouse.getName();
    	if (parentOne != null) s += " | Parent 1: " + parentOne.getName();
    	if (parentTwo != null) s += " | Parent 2: " + parentTwo.getName();
    	for (Member m : children) { s += " | Child: " + m.getName(); } 
    	return s;
    }
    
    // Utility
    private static long newID() { return System.currentTimeMillis() + (long)(Math.random() * 100); }
  
}
