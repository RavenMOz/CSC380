package application.data;

import java.util.ArrayList;

import application.storage.SQLCommands;
import application.swing.logininterface.util.UserDB;
import application.swing.main.Main;

public class Family {
    /* Variables:
     */
    ArrayList<Member> familyMembers;
    private final long familyID;
    private final long ownerID;
    private String name;
    public Member root = null;
    /* Constructor:
     */
    public Family(String name){ 
    	this.familyMembers = new ArrayList<>(); 
    	this.familyID = System.currentTimeMillis() + (long)(Math.random() * 100); 
    	this.ownerID = UserDB.activeUser;
    	this.root = new Member("New", this);
    	this.name = name;
    }
    
	public Family(long famID){ 
    	this.familyMembers = SQLCommands.getMembersByFamID(famID);
    	familyID = famID;
    	this.ownerID = SQLCommands.getOwnerID(famID);
    	this.name = SQLCommands.getFamilyName(famID);
    	if (familyMembers.size() > 0) root = familyMembers.get(0);
    	SQLCommands.setRelations(this);
    }
	
	public Family(ArrayList<Member> members, long famID, long ownID) {
		this.familyMembers = members;
		this.familyID = famID;
		this.ownerID = ownID;
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
	public String getName() { return name; }
    public Member getMember(String name) {
    	for (Member m : familyMembers) {
			if (m.getName().contains(name)) return m;
		}
    	return null;
    }
    public Member getMember( int index ){ return familyMembers.get(index); }
    public Member getMember(long mID){
    	if (mID == 0) return null;
    	for (Member m : getMembers()) {
    		if (m.getMemberID() == mID) return m;
    	}
    	return null;
    }
    public Member getRootMember()
    {
        return this.root;
    }

    public void setRootMember(Member targetMember)
    {
        if(familyMembers.contains(targetMember))
        {
            for(int count = 0; count < familyMembers.size(); count++)
            {
                if(familyMembers.get(count) == targetMember)
                {
                    this.root = familyMembers.get(count);
                }
            }
        }
    }

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
            for (Member m : familyMembers) {
            	if (m.getParentOne() == targetMember) m.setParentOne(null);
            	if (m.getParentTwo() == targetMember) m.setParentTwo(null);
            	if (m.getSpouse() == targetMember) m.setSpouse(null);
            	if (m.getChildren().contains(targetMember)) m.removeChild(targetMember);
            }
            setRootMember(familyMembers.get(0));
        }
    }
    // Clear:
    public void clearMembers(){ familyMembers.clear(); }
	public long getFamilyID() {
		return familyID;
	}
	
	public String toString() { return name; }
}
