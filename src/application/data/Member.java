package application.data;

import java.util.ArrayList;

public class Member {
    /* This Object holds the name, Biography, Parents, Children and Spouse of a
     * given member of the tree.
     */
    // Variables:
    String name; String biography;
    int bDay; int bMonth; int bYear;
    ArrayList<Member> children;
    Member mother; Member father; Member spouse;
    public final long memberID;
    public final long familyID;

    /* Constructor: Creates a member with inputted name and inputted biography
     */
    public Member( String iName , long famID ){
    	this(iName, null, famID);
    }
    
    public Member( String iName, String bio , long famID){
        this.name = iName;
        this.bDay = 0;
        this.bMonth = 0;
        this.bYear = 0;
        this.biography = bio;
        memberID = System.currentTimeMillis() + (long)(Math.random() * 100);
        familyID = famID;
        mother = null;
        father = null;
        children = null;
        spouse = null;

    }

    public Member(long mID, String name2, String bio, int bDay2, int bMonth2, int bYear2, int children2, int mother2,
			int father2, int spouse2, long famID) {
    	this.name = name2;
        this.bDay = bDay2;
        this.bMonth = bMonth2;
        this.bYear = bYear2;
        this.biography = bio;
        familyID = famID;
        memberID = mID;
        mother = null;
        father = null;
        children = null;
        spouse = null;
	}

	/* The Methods of this class are the following:
     *      Get = Fetches variable following the get section of the method's title.
     *      Set = Replaces a variable with a given input.
     *      Add = adds a value to a given variable.
     *      Remove = Removes (nulls?) a value from a variable.
     *      Clear = Clears an array.
    */
    // Get: ( ! )
    public String getName(){ return this.name;      }
    public String getBio(){  return this.biography; }
    public int getBDay(){    return this.bDay;      }
    public int getBMonth(){  return this.bMonth;    }
    public int getBYear(){   return this.bYear;     }
    public Member getChildren(){
        // Return the whole list of children? A single child? Which child?
        return null;
    }
    public Member getParents(){
        // return a parent? which parent?
        return null;
    }
    public Member getSpouse(){ return this.spouse;   }

    //Set: ( ! )
    public void setName( String newName ){      this.name = newName;         }
    public void setBio( String newBio ){        this.biography = newBio;     }
    public void setBDay( int newBirthDay ){     this.bDay = newBirthDay;     }
    public void setBMonth( int newBirthMonth ){ this.bMonth = newBirthMonth; }
    public void setBYear( int newBirthYear ){   this.bYear = newBirthYear;   }
    public void setSpouse( Member iSpouse ){    this.spouse = iSpouse;       }

    //Add: ( ! )
    public void addChild( Member iChild ){
        // Adds a child to the array of children Members.
    }
    public void addParent( Member iParent ){
        // adds a parent... to? parentOne? parentTwo?
    }

    //Remove: ( ! )
    public void removeChild( Member iChild ){
        // removes a child from the array of the children Members.
    }
    public void removeParent( Member iParent ){
        // Check to make sure that the parent exits.
    }

    //Clear: ( ! )
    public void clearChildren(){
        // clear all the children from the array.
    }
    
    public String toString() {
    	return "ID: " + memberID + " | Name: " + name + " | Bio: " + biography; 
    }

}
