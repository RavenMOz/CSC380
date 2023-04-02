import java.util.ArrayList;
import java.util.Scanner;

public class MemberTester {

    public static void main(String[] args) {
        ArrayList<Member> treeMembers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String iName; String iBio;
        int iBirthDay; int iBirthMonth; int iBirthYear;
        optionsTE();
        for (;;){ // main program loop.
            System.out.print("> ");
            String ans = sc.nextLine();
            switch ( ans ){
                case "help":
                    optionsTE();
                    break;
                case "create": // Create a new Member
                    System.out.println("| Enter the new member's name: ");
                    System.out.print("| Name> "); iName = sc.nextLine();
                    Member member = new Member(iName);
                    treeMembers.add(member);
                    break;
                case "delete": // Delete a Member
                    System.out.println("| Enter the name of who you want to delete: ");
                    System.out.print("| > ");
                    iName = sc.nextLine();
                    for ( int counter = 0; counter < treeMembers.size(); counter++ ){
                        if ( treeMembers.get(counter).getName().equals(iName) ) {
                            treeMembers.remove(treeMembers.get(counter));
                        }
                    }
                    break;
                case "list": // Prints the List.
                    System.out.print("| Tree Members: ");
                    for ( int counter = 0; counter < treeMembers.size(); counter++ ){
                        System.out.print(treeMembers.get(counter).getName()+", ");
                    }
                    System.out.println("");
                    break;
                case "edit": // Edit an existing Member
                    boolean found = false;
                    Member requestedMember = null;
                    System.out.println("| Enter the name: ");
                    System.out.print("| > "); iName = sc.nextLine();
                    for ( int count = 0; count < treeMembers.size(); count++ ){
                        if ( treeMembers.get(count).getName().equals(iName) ){
                            found = true;
                            requestedMember = treeMembers.get(count);
                        }
                    }
                    if ( !found ){
                        System.out.println("There is no one in the tree with that name!");
                        break;
                    }
                    optionsME();
                    memberEditor(treeMembers,requestedMember);
                    break;
                case "quit": // Quits the Loop.
                    break;
                default:
                    System.out.println("INVALID RESPONSE! ( c, Create | d, Delete | list, Show list of Members | q, Quit )");
            }
            if (ans.equals("quit")){
                break;
            }
        }
    }

    /* Printing Methods:
     *      These methods print the options for each editor.
     */
    private static void optionsTE(){ // Tree Editor Options
        System.out.println("| Tree Editor Commands");
        System.out.println("|_________________________________________");
        System.out.println("|   create, Create      delete, Delete");
        System.out.println("|   edit, Edit          list, List");
        System.out.println("|   quit, Quit          help, Display this command list.");

    }
    private static void optionsME(){ // Member Editor Options.
        System.out.println("|+| Member Editor Commands");
        System.out.println("|+|________________________________________");
        System.out.println("|+|   name, Name         bio, Biography");
        System.out.println("|+|   mother, Mother     father, Father");
        System.out.println("|+|   spoouse, Spouse    quit, Quit");
        System.out.println("|+|   help, Display this command list.");
    }

    /* Function Methods:
     *      These are the functions for certain sections of the editor.
     */
    private static Member memberEditor( ArrayList<Member> treeMembers, Member requestedMember ){
        for (;;){
            Scanner sc = new Scanner(System.in);
            String memberName = requestedMember.getName();
            System.out.print("|+| > ");
            String eAns = sc.nextLine();
            switch (eAns) {
                case "help":
                    optionsME();
                    break;
                case "name": // Edit the Name
                    System.out.print("|+|+| New Name> ");
                    String newName = sc.nextLine();
                    for ( int count = 0; count < treeMembers.size(); count++ ){
                        if ( treeMembers.get(count).getName().equals(memberName) ){
                            treeMembers.get(count).setName(newName);
                        }
                    }
                    break;
                case "bio": // Edit the Biography
                    System.out.print("|+|+| New Biography> ");
                    String newBio = sc.nextLine();
                    for ( int count = 0; count < treeMembers.size(); count++ ){
                        if ( treeMembers.get(count).getName().equals(memberName) ){
                            treeMembers.get(count).setBio(newBio);
                        }
                    }
                    break;
                case "mother": // Edit who the Mother is.
                    System.out.println("|+|+| Create the Edit Mother Function.");
                    break;
                case "father": // Edit who the Father is.
                    System.out.println("|+|+| Create the Edit Father Function.");
                    break;
                case "spouse": // Edit who the Spouse is.
                    System.out.println("|+|+| Create the Edit Spouse Function.");
                    break;
                case "quit": // Quits Edit Loop.
                    break;
                default: // Error message.
                    System.out.println("|+|+| INVALID RESPONSE! ( n, Name | b, Biography | m, Mother | f, Father | s, Spouse )");
            }
            if (eAns.equals("quit")) { // Quiting the Editing Loop.
                break;
            }
        }
        return requestedMember;
    }

}
