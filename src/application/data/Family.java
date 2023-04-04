import java.util.ArrayList;

public class Family {
    /* Variables:
     */
    ArrayList<Member> familyMembers;
    Member root;
    /* Constructor:
     */
    public Family(){ this.familyMembers = new ArrayList<>(); this.root = null;}
    /* Methods:
     *      Get = Fetches variable following the get section of the method's title.
     *      Add = adds a value to a given variable.
     *      Remove = removes a value from a given variable.
     *      Clear = Clears an Array.
     */
    // Get:
    public Member getMember( int index ){ return familyMembers.get(index); }
    public Member getRootMember(){ return this.root; }
    public int getSize(){ return familyMembers.size(); }
    public Member getMember( Member targetMember ){
        if ( familyMembers.contains(targetMember)){
            for ( int count = 0; count < familyMembers.size(); count++ ){
                if ( familyMembers.get(count) == targetMember ){
                    return familyMembers.get(count);
                }
            }
        }
        return null;
    }
    // Add:
    public void addMember( Member newMember ){
        familyMembers.add(newMember);
        if (familyMembers.size() == 1 ){
            this.root = newMember;
        }
    }
    // Remove:
    public void removeMember( Member targetMember ){
        if ( familyMembers.contains(targetMember)){
            familyMembers.remove(targetMember);
        }
    }
    // Clear:
    public void clearMembers(){ familyMembers.clear(); }
}
