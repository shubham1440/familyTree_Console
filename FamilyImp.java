import java.util.Scanner;

public class FamilyImp {
	
	public static void main(String[] args) {

        // Scanners used for input (we could also just use one of them but this felt like a cleaner solution)
        Scanner input = new Scanner(System.in);
        Scanner stringInput = new Scanner(System.in);
        Scanner identifierInput = new Scanner(System.in);

        // Variable initialization
        FamilyTree tree = null;
        int selection = 0;
        String name;

        
        // Selection and Menu Logic
        while(selection != 6) {
            displayMenuOptions();
            String selected = identifierInput.nextLine();
            try {
                selection = Integer.parseInt(selected);   
            } catch(NumberFormatException e) {
                System.out.println("Not a valid input");
                selection = 7;
            }

            switch (selection) {
                case 1: 
                    System.out.println("Name your first ancestor: ");
                    name = stringInput.nextLine();
                    tree = new FamilyTree(name);
                    System.out.println(tree.toString());
                    break;
                case 2: 
                    if (tree != null) {
                        System.out.println(tree.toString());
                        System.out.println("Ancestor ID: ");
                        String line = identifierInput.nextLine();
                        int ancestorID = 0;
                        try {
                            ancestorID = Integer.parseInt(line);   
                        } catch(NumberFormatException e) {
                            System.out.println("Not a valid input");
                            break;
                        }
                        System.out.println("Partner Name");
                        name = stringInput.nextLine();
                        tree.addPartner(ancestorID, name);
                        System.out.println(tree.toString());
                    } else {
                        System.out.println("You haven't created any trees!");
                    }
                    break;
                case 3: 
                    if (tree != null) {
                        System.out.println(tree.toString());
                        System.out.println("Ancestor ID: ");
                        String line = identifierInput.nextLine();
                        int ancestorID = 0;
                        try {
                            ancestorID = Integer.parseInt(line);   
                        } catch(NumberFormatException e) {
                            System.out.println("Not a valid input");
                            break;
                        }
                        System.out.println("Child Name");
                        name = stringInput.nextLine();
                        tree.addChild(ancestorID, name);
                        System.out.println(tree.toString());
                    } else {
                        System.out.println("You haven't created any trees!");
                    }
                    break;
                case 4: 
                    if (tree != null) {
                        System.out.println("************************* Tree Revelation *************************");
                        System.out.println(tree.toString());
                    } else {
                        System.out.println("You haven't created any trees!");
                    }
                    break;
                case 5: 
                    if (tree != null) {
                        System.out.println("************************* Ancestor Lookup *************************");
                        System.out.println(tree.toString());
                        System.out.println("Tree size: " + tree.size);
                        System.out.println("Retrieve information for Ancestor with ID (number cannot be higher than size of tree): ");
                        String line = identifierInput.nextLine();
                        int ancestorID = 0;
                        try {
                            ancestorID = Integer.parseInt(line);   
                        } catch(NumberFormatException e) {
                            System.out.println("Not a valid input");
                            break;
                        }
                        System.out.println(tree.getNodeInfo(tree.getFamilyMember(ancestorID)));
                    } else {
                        System.out.println("You haven't created any trees!");
                    }
                    break;
                default: 
                    System.out.println("Input should be between 1-6");
                    break;
            }
        }

       
        input.close();
        stringInput.close();
        identifierInput.close();
    }

    // Displays the Menu 
    public static void displayMenuOptions() {
        System.out.println("************************* Family Tree *************************");
        System.out.println("1. Create Tree with first Person");
        System.out.println("2. Add Partner");
        System.out.println("3. Add Child");
        System.out.println("4. Reveal Tree");
        System.out.println("5. Ancestor Lookup");
        System.out.println("6. Quit");
        System.out.println("Input: ");
    }

}
