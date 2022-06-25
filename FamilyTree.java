

public class FamilyTree {

	class FamilyTreeNode {
        String name;
        int identifier;
        FamilyTreeNode partner;
        FamilyTreeNode sibling;
        FamilyTreeNode child;

        // CONSTRUCTOR
        public FamilyTreeNode(String name, int id, FamilyTreeNode partner, FamilyTreeNode sibling, FamilyTreeNode child) {
            this.name = name;
            this.identifier = id;
            this.partner = partner;
            this.sibling = sibling;
            this.child = child;
        }

    }

    FamilyTreeNode ancestor;
    int size = 1;
    boolean found = false;
    FamilyTreeNode foundAncestor; 

    // CONSTRUCTOR
    public FamilyTree(String ancestorName) {

        // Create Nodes
        FamilyTreeNode ancestorNode = new FamilyTreeNode(ancestorName, size, null, null, null);

        // Set Ancestor to the created Node
        this.ancestor = ancestorNode;

        size++;
    }

    // Adds a child to a Specified Node
    public void addChild(int ancestorID, String childName) {
        System.out.println("Adding Child!");
        getFamilyMember(ancestorID);
        FamilyTreeNode current = foundAncestor;
        FamilyTreeNode newChild = new FamilyTreeNode(childName, size, null, null, null);

        // If there is no partner we cannot add children
        if (current.partner != null) {
            // If there is no first child, add a child
            if (current.child == null) {
                current.child = newChild;
                current.partner.child = newChild;
            } else {
                // Add sibling
                current = current.child;
                while (current.sibling != null) {
                    current = current.sibling;
                }
                current.sibling = newChild;
            }
            size++;
        } else {
            System.out.println("This ancestor has no partner and therefore cannot have children!");
        }
       
    }

    // Add a partner to the ancestor with the specified ID 
    public void addPartner(int ancestorID, String partnerName) {
        System.out.println("Adding Partner!");
        getFamilyMember(ancestorID);
        if (foundAncestor != null) {
            FamilyTreeNode current = foundAncestor;
            System.out.println(getNodeInfo(current));
            FamilyTreeNode newPartner = new FamilyTreeNode(partnerName, size, null, null, null);
            
            if (current.partner == null) {
                if (current.identifier == ancestorID) {
                    current.partner = newPartner;
                    current.partner.partner = current;
                    size++;
                }
            } else {
                System.out.println("Ancestor " + current.name + " already has a partner with name: " + current.partner.name + " and it cannot be changed!");
            }
        } else {
            System.out.println("Ancestor Not Found");
        }
        
    }

    // Returns specified tree node info
    public FamilyTreeNode getFamilyMember(int id) {
        FamilyTreeNode current = this.ancestor;
        found = false;
        if (id > size) {
            System.out.println("Ancestor ID Given doesn't exist!");
            return null;
        } else {
            preOrderTraversal(id, current);
            current = foundAncestor;
        }
        return current;
    }

    // Depth-First Search pre-order traversal with recursion
    public void preOrderTraversal(int id, FamilyTreeNode node) {
        if (!found) {
            if (node != null) {
                if (node.identifier != id) {
                    preOrderTraversal(id, node.child);
                    preOrderTraversal(id, node.partner);
                    preOrderTraversal(id, node.sibling);
                } else {
                    found = true;
                    System.out.println("Found Node!");
                    foundAncestor = node;
                    return;
                }
            }
        }
    }

    @Override
    public String toString() {
        String output = getNodeInfo(ancestor);
        
        return output;
    }

    // Returns info of specific FamilyTreeNode
    public String getNodeInfo(FamilyTreeNode current) {
        String output;
        if (current != null) {
            // ANCESTOR
            output = current.name + " (identifier " + current.identifier + ")";
            // PARTNER
            if (current.partner != null) {
                output += " partner " + current.partner.name + " (identifier " + current.partner.identifier + ")";
            } else {
                output += " has no partner";
            }
            
            // CHILD
            if (current.child != null) {
                output += "\n " + current.child.name + " (identifier " + current.child.identifier + ")";
                // CHILD PARTNER
                if (current.child.partner != null) {
                    output += " partner " + current.child.partner.name + " (identifier " + current.child.partner.identifier + ")";
                } else {
                    output += " has no partner";
                }
                // SIBLINGS
                FamilyTreeNode siblingNode = current.child.sibling;
                while (siblingNode != null) {
                    output += "\n " + siblingNode.name + " (identifier " + siblingNode.identifier + ")";
                    // SIBLING PARTNER
                    if (siblingNode.partner != null) {
                        output += " partner " + siblingNode.partner.name + " (identifier " + siblingNode.partner.identifier + ")";
                    } else {
                        output += " has no partner";
                    }
                    siblingNode = siblingNode.sibling;
                }
            } else {
                output += "\n No children";
            }
        } else {
            output = "Node Null";
        }
        
       return output;
    }
}
