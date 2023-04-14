package application.data;

import java.util.ArrayList;

public class Member {
	
    // Variables:
    String name, biography;
    int bDay, bMonth, bYear;
    ArrayList<Member> children = new ArrayList<>();
    Member parentOne, parentTwo, spouse;
    public final long memberID;
    public final long familyID;
    
/////////////////////////////////////////////////////////////////////////
// These constructors used for creating new dynamic members with no relations.
/////////////////////////////////////////////////////////////////////////
    
    public Member( Family f ){ this("", "", f); }
    public Member( String iName, Family f ){ this (iName, "", f); }
    public Member( String iName, String iBiography, Family fam ) {
        this(newID(), fam.getFamilyID(), iName, iBiography, 1, 1, 1, null ,null, null);
        fam.addMember(this);
    }
    
/////////////////////////////////////////////////////////////////////////
// These constructors used for creating new dynamic members rooted to other dynamic members.
/////////////////////////////////////////////////////////////////////////
    
    // Creates a child with specified dynamic parents.
    public Member(String name, String bio, Member p1, Member p2, Family fam) {
    	this(newID(), fam.getFamilyID(), name, bio, 1, 1, 1, p1, p2, null);
    	if (p1 != null) p1.addChild(this);
    	if (p2 != null) p2.addChild(this);
    	fam.addMember(this);
    }
    // Creates a spouse for the specified dynamic member.
    public Member(String name, String bio, Member sps, Family fam) {
    	this(newID(), sps.getFamilyID(), name, bio, 1, 1, 1, null, null, sps);
    	sps.setSpouse(this);
    	fam.addMember(this);
    }
    
/////////////////////////////////////////////////////////////////////////
// These constructors build full members with all parameters
/////////////////////////////////////////////////////////////////////////  
    
    //New dynamic member from relation IDs
    public Member(long mID, long famID, String name2, String bio, int bDay2, int bMonth2, int bYear2, long p1,
                  long p2, long sps) {
        this.name = name2;
        this.bDay = bDay2;
        this.bMonth = bMonth2;
        this.bYear = bYear2;
        this.biography = bio;
        familyID = famID;
        memberID = mID;
	
    }
    
    //New dynamic member from other dynamic members
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
		
    }

    /* The Methods of this class are the following:
     * 
     *      Get = Fetches variable following the get section of the method's title.
     *      Set = Replaces a variable with a given input.
     *      Add = adds a value to a given variable.
     *      Remove = Removes (nulls?) a value from a variable.
     *      Clear = Clears an array.
     *      Has = Boolean methods that checks if a variable is filled in or not. [ NEW! ]
     *      Utility = Functions which are used by other functions.
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
    public void setSpouse( Member newSpouse ){ this.spouse = newSpouse; newSpouse.spouse = this; }
    public void setChildren( ArrayList<Member> newChildren ) { children = newChildren; }
    
    //Add:
    public void addChild( Member iChild ){ this.children.add(iChild); }
    public void addParent( Member newParent ){
        if ( parentOne == null ){ this.parentOne = newParent; }
        else if ( parentTwo == null ){ this.parentTwo = newParent; }
    }

    //Remove:
    public void removeChild( Member iChild ){ children.remove(iChild); }
    public void removeParent( Member iParent ) { 
    	if ( parentOne == iParent ) parentOne = null;
    	else if ( parentTwo == iParent ) parentTwo = null; 
    }

    //Clear:
    public void clearChildren(){ this.children.clear(); }

    //Has:
    public boolean hasParent(){ return (parentOne != null || parentTwo != null); }
    public boolean hasParentOne() { return parentOne != null; }
    public boolean hasParentTwo() { return parentTwo != null; }
    public boolean hasSpouse(){ return spouse != null; }
    public boolean hasChildren(){ return children.size() > 0; }
    
    // Utility
    private static long newID() { return System.currentTimeMillis() + (long)(Math.random() * 100); }
    public String toString(){ 
    	String s = "MemberID: "+memberID+ " | FamilyID: "+familyID+" | Name: "+name+" | Bio: "+biography;
    	if (spouse != null) s += " | Spouse: " + spouse.getName();
    	if (parentOne != null) s += " | Parent 1: " + parentOne.getName();
    	if (parentTwo != null) s += " | Parent 2: " + parentTwo.getName();
    	for (Member m : children) { s += " | Child: " + m.getName(); } 
    	return s;
    }
	public void setParentOne(Member pone) { parentOne = pone; }
	public void setParentTwo(Member ptwo) { parentTwo = ptwo; }
    
}
