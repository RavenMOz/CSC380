package application.storage.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import application.data.Family;
import application.data.Member;
import application.storage.SQLCommands;

public class JUnitCases {

	
	
	long fID = 1680893907143L;
	
	@Test
	public void testWrite() {
		Family f = new Family();
		Member joe = new Member("Joe Mama", "The life of the party", f);
		Member jay = new Member("Hugh Janus", "The name says it all", f);
		Member john = new Member("Ben Dover", "My best friend ever", f);
		SQLCommands.writeFamily(f);
		assertEquals("Is this family written", true, SQLCommands.isWritten(f));
		SQLCommands.deleteFamily(f);
	}
	
	@Test
	public void testRead() {
		Family f2 = new Family(fID);
		assertEquals("Is this the same family?", f2.getMembers().size(), SQLCommands.getMembersByFamID(fID).size());
	}
	
	@Test
	public void testWriteAndRead() {
		Family f = new Family();
		Member joe = new Member("Joe Mama", "The life of the party", f);
		Member jay = new Member("Hugh Janus", "The name says it all", f);
		Member john = new Member("Ben Dover", "My best friend ever", f);
		SQLCommands.writeFamily(f);
		
		Family f3 = SQLCommands.readFamily(f.getFamilyID());
		
		assertEquals("Are these families equal", f.getSize(), f3.getSize());
		
		SQLCommands.deleteFamily(f.getFamilyID());
		
	}
	
	@Test
	public void readModifyWrite() {
		
		Family f4 = new Family(fID);
		Member hailie = new Member("Hailie Jade", "I love that name", f4.getMember("John"), f4.getMember("Abigail"), f4);
		
		SQLCommands.writeFamily(f4);
		
		Family f5 = new Family(fID);
		
		assertEquals("Did the change get properly written", f4.getSize(), f5.getSize());
		
	}
	
}
