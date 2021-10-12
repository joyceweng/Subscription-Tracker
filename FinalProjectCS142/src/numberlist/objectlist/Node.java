package numberlist.objectlist;

import java.io.Serializable;

/**
 * This class represents the node, to link values together in lists using
 * references.
 *
 * @author Joyce Weng
 * @author Phuoc Nguyen
 * @author Samuel Kosasih
 *
 * @version %I%, %G%
 * @since JDK 8u271
 * 
 * @see java.io.Serializable
 */
class Node implements Serializable {

    //private fields
    private Node nextNode;
    private Copiable value;

    /**
     * Constructor
     *
     * @param obj of the object
     */
    public Node(Copiable obj) {
        this.value = obj;
        nextNode = null;
    }

    /**
     * Accessor method for value
     *
     * @return value of the value
     */
    public Copiable getValue() {
        return value;
    }

    /**
     * Mutator method for value
     *
     * @param value of the value to be set
     */
    public void setValue(Copiable value) {
        this.value = value;
    }

    /**
     * Accessor method of nextNode
     *
     * @return nextNode for the next node
     */
    public Node getNextNode() {
        return nextNode;
    }

    /**
     * Mutator method of nextNode
     *
     * @param nextNode to set the next node
     */
    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

}
