package application.storage.testcases;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import application.data.Family;
import application.data.Member;
import application.storage.SQLCommands;

public class JUnitCases {

	Family f = new Family();
	
	long fID = 1680893907143L;
	
	@Test
	public void testWrite() {
		Member joe = new Member("Joe Mama", "The life of the party", f);
		Member jay = new Member("Hugh Janus", "The name says it all", f);
		Member john = new Member("Ben Dover", "My best friend ever", f);
		SQLCommands.writeFamily(f);
		assertEquals("Is this family written", true, SQLCommands.isWritten(f));
	}
	
	@Test
	public void testRead() {
		Family f2 = new Family(fID);
		assertEquals("Is this the same family?", f2.getMembers().size(), SQLCommands.getMembersByFamID(fID).size());
	}
	
	@Test
	public void testWriteAndRead() {
		Member joe = new Member("Joe Mama", "The life of the party", f);
		Member jay = new Member("Hugh Janus", "The name says it all", f);
		Member john = new Member("Ben Dover", "My best friend ever", f);
		SQLCommands.writeFamily(f);
		
		Family f3 = SQLCommands.readFamily(f.getFamilyID());
		
		assertEquals("Are these families equal", f, f3);
		
	}
	
	@Test
	public void readModifyWrite() {
		
		Family f4 = new Family(fID);
		System.out.println(f4);
		Member hailie = new Member("Hailie Jane", "I love that name", f4.getMember("john"), f4.getMember("abigail"), f4);
		
		SQLCommands.writeFamily(f4);
		
		Family f5 = new Family(fID);
		
		assertEquals("Did the change get properly written", f4, f5);
		
	}
	
}
