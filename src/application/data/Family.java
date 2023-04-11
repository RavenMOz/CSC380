package application.data;

import java.util.ArrayList;

import application.storage.SQLCommands;

public class Family {
    /* Variables:
     */
    ArrayList<Member> familyMembers;
    private final long familyID;
    private final long ownerID;
    /* Constructor:
     */
    public Family(){ this.familyMembers = new ArrayList<>(); this.familyID = System.currentTimeMillis() + (long)(Math.random() * 100); this.ownerID = getOwnerID(); }
    
	public Family(long famID){ 
    	this.familyMembers = SQLCommands.getMembersByFamID(famID);
    	familyID = famID;
    	this.ownerID = SQLCommands.getOwnerID(famID);
    }
	
	public long getOwnerID() {
		return this.ownerID;
	}
    /* Methods:
     *      Get = Fetches variable following the get section of the method's title.
     *      Add = adds a value to a given variable.
     *      Remove = removes a value from a given variable.
     *      Clear = Clears an Array.
     */
    // Get:
    public Member getMember(String name) {
    	for (Member m : familyMembers) {
			if (m.getName().contains(name)) return m;
		}
    	return null;
    }
    public Member getMember( int index ){ return familyMembers.get(index); }
    public ArrayList<Member> getMembers() {return familyMembers;}
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
    }
    // Remove:
    public void removeMember( Member targetMember ){
        if ( familyMembers.contains(targetMember)){
            familyMembers.remove(targetMember);
        }
    }
    // Clear:
    public void clearMembers(){ familyMembers.clear(); }
	public long getFamilyID() {
		return familyID;
	}
	
	public String toString() {
		String s = "Family ID: " + familyID;
//		for (Member m : familyMembers) {
//			s += "\n" + m;
//		}
		return s;
	}
}
