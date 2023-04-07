package application.storage.testcases;

import application.data.Family;
import application.data.Member;
import application.storage.SQLCommands;

public class Case1 {

	public static void main(String[] args) {
		
		/*
		 * This test case simulates the creation of a new family, along with the addition of
		 * a root member, and a spouse and child. These members are contained within a 
		 * family object, and then written to the MySQL database. A new family will be
		 * created with new members on every run.
		 * 
		 */
		
		//USER CREATES A NEW FAMILY
		Family fam = new Family();
		long fID = fam.getFamilyID();
		SQLCommands.activeFamilies.add(fam);
		
		//USER ADDS NEW ROOT MEMBER
		Member root = new Member("Son Goku", "The Worlds Strongest", fID);
		
		//USER CREATES A SPOUSE FOR ROOT MEMBER
		Member spouse = new Member(root); root.setSpouse(spouse);
		
		//USER ADDS INFO TO THE SPOUSE
		spouse.setName("Son Chichi");
		spouse.setBio("Nasty ass tiger mom");
		
		//USER CREATES A CHILD FOR ROOT AND SPOUSE
		Member child1 = new Member(root, spouse); root.addChild(child1); spouse.addChild(child1);
		
		//USER ADDS INFO TO THE CHILD
		child1.setName("Son Gohan");
		child1.setBio("The Great Saiyaman himself");
		
		System.out.println(root);
		System.out.println(spouse);
		System.out.println(child1);
		
		SQLCommands.writeFamily(fam);
		
		System.out.println(fam);
		
//		fam = new Family(fID);
		
		System.out.println(fam);
		
	}
	
}
