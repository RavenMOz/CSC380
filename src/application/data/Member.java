import java.util.ArrayList;

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
     */
    // Variables:
    String name;
    String biography;
    int bDay; int bMonth; int bYear;
    ArrayList<Member> children;
    int childCount;
    Member parentOne; Member parentTwo;
    Member spouse;

    /* Default Constructor:
    */
    public Member(){
        this.name = null;
        this.bDay = 0;
        this.bMonth = 0;
        this.bYear = 0;
        this.biography = null;
        this.parentOne = null;
        this.parentTwo = null;
        this.children = null;
        this.childCount = 0;
        this.spouse = null;

    }
    // with Name:
    public Member( String iName ){
        this.name = iName;
        this.biography = null;
        this.bDay = 0;
        this.bMonth = 0;
        this.bYear = 0;
        this.parentOne = null;
        this.parentTwo = null;
        this.children = null;
        this.childCount = 0;
        this.spouse = null;
    }
    // with Name & Biography:
    public Member ( String iName, String iBio ){
        this.name = iName;
        this.biography = iBio;
        this.bDay = 0;
        this.bMonth = 0;
        this.bYear = 0;
        this.parentOne = null;
        this.parentTwo = null;
        this.children = null;
        this.childCount = 0;
        this.spouse = null;
    }

    /* The Methods of this class are the following:
     *      Get = Fetches variable following the get section of the method's title.
     *      Set = Replaces a variable with a given input.
     *      Add = adds a value to a given variable.
     *      Remove = Removes (nulls?) a value from a variable.
     *      Clear = Clears an array.
     *      Has = Boolean methods that checks if a variable is filled in or not. [ NEW! ]
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
    public void removeChild( Member iChild ){
        for ( int counter = 0; counter < childCount; counter++ ){
            if ( children.get(counter) == iChild ){
                this.children.remove(counter);
                this.childCount--;
            }
        }
    } // NEW!
    public void removeParent( Member iParent ){ if ( parentOne == iParent ){ parentOne = null; } else if ( parentTwo == iParent ) { parentTwo = null; } } // NEW!

    //Clear:
    public void clearChildren(){ this.children.removeAll(children); childCount = 0; } // NEW!

    //Has:
    public boolean hasParent(){ if ( parentOne != null ) { return true; } else return parentTwo != null; } // NEW!
    public boolean hasParentOne() { return parentOne != null; } // NEW!
    public boolean hasParentTwo() { return parentTwo != null; } // NEW!
    public boolean hasSpouse(){ return spouse != null; } // NEW!
    public boolean hasChildren(){ return children != null; } // NEW!
}
