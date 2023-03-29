package treelinecalculationsalgorithm;

// Test Program for the Family and Member Classes
public class RecursiveTreeTest
{
    public static void main(String[] args)
    {
        Family myFamily = new Family();

        Member member1 = new Member("John Young", "Lives in NYC");
        Member member2 = new Member("Elise Young", "Works at a School");
        Member member3 = new Member("Mark Young", "Loves to Cook");
        Member member4 = new Member("Zachary Young", "");
        Member member5 = new Member("Jennifer White", "");
        Member member6 = new Member("Stacy Young", "");
        Member member7 = new Member("Anthony White", "");
        Member member8 = new Member("Emily Young", "");
        Member member9 = new Member("Harold Young", "");
        Member member10 = new Member("Rebecca White", "");

        /****************************************************************************************************************************/

        myFamily.addMember(member5);
        myFamily.addMember(member2);
        myFamily.addMember(member3);
        myFamily.addMember(member4);
        myFamily.addMember(member1);
        myFamily.addMember(member6);
        myFamily.addMember(member7);
        myFamily.addMember(member8);
        myFamily.addMember(member9);
        myFamily.addMember(member10);

        myFamily.getMember(member2).addSpouse(member3);
        myFamily.getMember(member3).addSpouse(member2);

        myFamily.getMember(member2).addChild(member4);
        myFamily.getMember(member2).addChild(member1);
        myFamily.getMember(member2).addChild(member5);

        myFamily.getMember(member3).addChild(member4);
        myFamily.getMember(member3).addChild(member1);
        myFamily.getMember(member3).addChild(member5);

        myFamily.getMember(member4).addMother(member2);
        myFamily.getMember(member4).addFather(member3);

        myFamily.getMember(member1).addMother(member2);
        myFamily.getMember(member1).addFather(member3);

        myFamily.getMember(member5).addMother(member2);
        myFamily.getMember(member5).addFather(member3);

        myFamily.getMember(member4).addSpouse(member6);
        myFamily.getMember(member6).addSpouse(member4);

        myFamily.getMember(member4).addChild(member8);
        myFamily.getMember(member4).addChild(member9);

        myFamily.getMember(member6).addChild(member8);
        myFamily.getMember(member6).addChild(member9);

        myFamily.getMember(member8).addFather(member4);
        myFamily.getMember(member8).addMother(member6);

        myFamily.getMember(member9).addFather(member4);
        myFamily.getMember(member9).addMother(member6);

        myFamily.getMember(member5).addSpouse(member7);
        myFamily.getMember(member7).addSpouse(member5);

        myFamily.getMember(member5).addChild(member10);

        myFamily.getMember(member7).addChild(member10);

        myFamily.getMember(member10).addMother(member5);
        myFamily.getMember(member10).addFather(member7);

        /****************************************************************************************************************************/
/*
        System.out.println(myFamily.getMember(member1).getName());
        System.out.println(myFamily.getMember(member2).getName());
        System.out.println(myFamily.getMember(member3).getName());
        System.out.println(myFamily.getMember(member4).getName());
        System.out.println(myFamily.getMember(member5).getName());
        System.out.println(myFamily.getMember(member6).getName());
        System.out.println(myFamily.getMember(member7).getName());
        System.out.println(myFamily.getMember(member8).getName());
        System.out.println(myFamily.getMember(member9).getName());
        System.out.println(myFamily.getMember(member10).getName());
        System.out.println(myFamily.getRootMember().getName());
*/
        getMembers(myFamily, myFamily.getRootMember());
    }

    public static void getMembers(Family familyTree, Member root)
    {
        Member mother = null;
        Member father = null;
        Member spouse = null;
        Member child = null;

        if(root.hasMother())
        {
            mother = root.getMother();
            System.out.println(mother.getName());
        }

        if(root.hasFather())
        {
            father = root.getFather();
            System.out.println(father.getName());
        }

        if(root.hasSpouse())
        {
            spouse = root.getSpouse();
            System.out.println(spouse.getName());
        }

        if(root.hasChildren())
        {
            for(int count = 0; count < root.getChildCount(); count++)
            {
                child = root.getChild(count);
                System.out.println(spouse.getName());

                getMembers(familyTree, child);
            }
        }
    }

    public static void drawMembers(Family familyTree)
    {

    }
}

/*
private void createTree(DefaultMutableTreeNode top, FamilyMember root) {
        DefaultMutableTreeNode parents = null;
        DefaultMutableTreeNode father = null;
        DefaultMutableTreeNode mother = null;
        DefaultMutableTreeNode spouse = null;
        DefaultMutableTreeNode children = null;
        DefaultMutableTreeNode child = null;
        DefaultMutableTreeNode spouseNode = null;

        if (root.has(FamilyMember.Attribute.PARENTS) && root == currentFamilyTree.getRoot()) {
            parents = new DefaultMutableTreeNode("Parents");
            //add parent node
            top.add(parents);

            if (root.has(FamilyMember.Attribute.FATHER)) {
                father = new DefaultMutableTreeNode(root.getFather());
                //add father to parent node
                parents.add(father);
            }

            if (root.has(FamilyMember.Attribute.MOTHER)) {
                mother = new DefaultMutableTreeNode(root.getMother());
                //add mother to parent node
                parents.add(mother);
            }
        }

//        }
        if (root.has(FamilyMember.Attribute.SPOUSE)) {
            spouseNode = new DefaultMutableTreeNode("Spouse");
            spouse = new DefaultMutableTreeNode(root.getSpouse());
            //add spouse node
            spouseNode.add(spouse);
            //add the spouse node 
            top.add(spouseNode);
        }

        if (root.has(FamilyMember.Attribute.CHILDREN)) {
            children = new DefaultMutableTreeNode("Children");
            for (FamilyMember f : root.getChildren()) {
                child = new DefaultMutableTreeNode(f);
                //for each child, call create tree to populate their subtree nodes 
                createTree(child, f);
                //ad that child to the top node 
                children.add(child);
            }
            top.add(children);
        }

    }
 */