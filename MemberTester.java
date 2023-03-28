import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Member> treeMembers = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String iName;
        String iBio;
        optionsTE();
        for (;;){ // main program loop.
            System.out.print("> ");
            String ans = sc.nextLine();
            switch ( ans ){
                case "c": // Create a new Member
                    System.out.println("| Enter the Name and Biography: ");
                    System.out.print("| Name> ");
                    iName = sc.nextLine();
                    System.out.print("| Bio> ");
                    iBio = sc.nextLine();
                    Member member = new Member(iName,iBio);
                    treeMembers.add(member);
                    break;
                case "d": // Delete a Member
                    System.out.println("| Enter the name of the member you wish to delete from the tree.");
                    System.out.print("| > ");
                    iName = sc.nextLine();
                    for ( int counter = 0; counter < treeMembers.size(); counter++ ){
                        if ( treeMembers.get(counter).getName().equals(iName) ) {
                            treeMembers.remove(treeMembers.get(counter));
                        }
                    }
                    break;
                case "e": // Edit an existing Member
                    System.out.println("| Edit who?");
                    System.out.print("| > ");
                    String who = sc.nextLine();
                    boolean found = false;
                    for ( int count = 0; count < treeMembers.size(); count++ ){
                        if ( treeMembers.get(count).getName().equals(who) ){
                            found = true;
                        }
                    }
                    if ( !found ){
                        System.out.println("There is no one in the tree with that name!");
                        break;
                    }
                    optionsME();
                    for (;;){ // The editing loop.
                        System.out.print("|| > ");
                        String eAns = sc.nextLine();
                        switch (eAns) {
                            case "n": // Edit the Name
                                System.out.print("||| New Name> ");
                                String newName = sc.nextLine();
                                for ( int count = 0; count < treeMembers.size(); count++ ){
                                    if ( treeMembers.get(count).getName().equals(who) ){
                                        treeMembers.get(count).setName(newName);
                                    }
                                }
                                break;
                            case "b": // Edit the Biography
                                System.out.print("||| New Biography> ");
                                String newBio = sc.nextLine();
                                for ( int count = 0; count < treeMembers.size(); count++ ){
                                    if ( treeMembers.get(count).getName().equals(who) ){
                                        treeMembers.get(count).setBio(newBio);
                                    }
                                }
                                break;
                            case "m": // Edit who the Mother is.
                                System.out.println("|| Create the Edit Mother Function.");
                                break;
                            case "f": // Edit who the Father is.
                                System.out.println("|| Create the Edit Father Function.");
                                break;
                            case "s": // Edit who the Spouse is.
                                System.out.println("|| Create the Edit Spouse Function.");
                                break;
                            case "q": // Quits Edit Loop.
                                break;
                            default: // Error message.
                                System.out.println("|| INVALID RESPONSE! ( n, Name | b, Biography | m, Mother | f, Father | s, Spouse )");
                        }
                        if (eAns.equals("q")) {
                            break;
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
                case "q": // Quits the Loop.
                    break;
                default:
                    System.out.println("INVALID RESPONSE! ( c, Create | d, Delete | list, Show list of Members | q, Quit )");
            }
            if (ans.equals("q")){
                break;
            }
        }
    }

    private static void optionsTE(){ // Tree Editor Options
        System.out.println("| Tree Editor Commands");
        System.out.println("|______________________");
        System.out.println("|   c, Create");
        System.out.println("|   d, Delete");
        System.out.println("|   e, Edit");
        System.out.println("|   l, List");
        System.out.println("|   q, Quit");

    }

    private static void optionsME(){ // Member Editor Options.
        System.out.println("|| Member Editor Commands");
        System.out.println("||________________________");
        System.out.println("||   n, Name");
        System.out.println("||   b, Biography");
        System.out.println("||   m, Mother");
        System.out.println("||   f, Father");
        System.out.println("||   s, Spouse");
        System.out.println("||   q, Quit");
    }

}
