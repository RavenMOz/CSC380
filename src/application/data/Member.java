import java.util.ArrayList;

public class Member {
    /* This Object holds the name, Biography, Parents, Children and Spouse of a
     * given member of the tree.
     */
    // Variables:
    String name;
    String bio;
    ArrayList<Member> children;
    Member mother;
    Member father;
    Member spouse;

    /* Constructor: Creates a member with inputted name and inputted biography
     */
    public Member( String iName, String iBio ){
        this.name = iName;
        this.bio = iBio;
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
    // Get:
    public String getName(){
        return this.name;
    }
    public String getBio(){
        return this.bio;
    }
    public Member getChildren(){
        // Return the whole list of children? A single child? Which child?
        return null;
    }
    public Member getParents(){
        // return a parent? which parent?
        return null;
    }
    public Member getSpouse(){
        return this.spouse;
    }
    //Set:
    public void setName( String iName ){
        this.name = iName;
    }
    public void setBio( String iBio ){
        this.bio = iBio;
    }
    public void setSpouse( Member iSpouse ){
        this.spouse = iSpouse;
    }
    //Add:
    public void addChild( Member iChild ){
        // Adds a child to the array of children Members.
    }
    public void addParent( Member iParent ){
        // adds a parent... to? parentOne? parentTwo?
    }
    //Remove:
    public void removeChild( Member iChild ){
        // removes a child from the array of the children Members.
    }
    public void removeParent( Member iParent ){
        // Check to make sure that the parent exits.
    }
    //Clear:
    public void clearChildren(){
        // clear all the children from the array.
    }

}
