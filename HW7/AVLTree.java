/**
 * AVLTree is a self-balancing binary search tree that maintains the height balance property.
 * This class provides methods for inserting, deleting, and searching for stocks in the tree.
 */
public class AVLTree {
    /**
     * Node represents a single node in the AVL tree.
     */
    private class Node {
        Stock stock;
        Node left, right;
        int height;

        /**
         * Constructs a new Node with the specified stock.
         *
         * @param stock the stock to be stored in this node
         */
        Node(Stock stock) {
            this.stock = stock;
            this.height = 1;
        }
    }

    private Node root;

    /**
     * Returns the height of the specified node.
     *
     * @param N the node whose height is to be determined
     * @return the height of the node, or 0 if the node is null
     */
    private int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    /**
     * Returns the maximum of two integers.
     *
     * @param i the first integer
     * @param j the second integer
     * @return the maximum of a and b
     */
    private int max(int i, int j) {
        if (i > j) {
            return i;
        } else {
            return j;
        }    }

    /**
     * Performs a right rotation on the specified subtree.
     *
     * @param y the root of the subtree to be rotated
     * @return the new root of the rotated subtree
     */
    private Node rightRot(Node y) {
        Node x = y.left;
        Node y1 = x.right;

        x.right = y;
        y.left = y1;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    /**
     * Performs a left rotation on the specified subtree.
     *
     * @param x the root of the subtree to be rotated
     * @return the new root of the rotated subtree
     */
    private Node leftRot(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    /**
     * Returns the balance factor of the specified node.
     *
     * @param N the node whose balance factor is to be determined
     * @return the balance factor of the node
     */
    private int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    /**
     * Inserts a new stock into the subtree rooted at the specified node.
     *
     * @param node  the root of the subtree
     * @param stock the stock to be inserted
     * @return the new root of the subtree
     */
    private Node insert(Node node, Stock stock) {
        if (node == null)
            return (new Node(stock));

        if (stock.getSymbol().compareTo(node.stock.getSymbol()) < 0)
            node.left = insert(node.left, stock);
        else if (stock.getSymbol().compareTo(node.stock.getSymbol()) > 0)
            node.right = insert(node.right, stock);
        else {
            node.stock = stock;
            return node;
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) < 0)
            return rightRot(node);

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) > 0)
            return leftRot(node);

        if (balance > 1 && stock.getSymbol().compareTo(node.left.stock.getSymbol()) > 0) {
            node.left = leftRot(node.left);
            return rightRot(node);
        }

        if (balance < -1 && stock.getSymbol().compareTo(node.right.stock.getSymbol()) < 0) {
            node.right = rightRot(node.right);
            return leftRot(node);
        }

        return node;
    }

    /**
     * Returns the node with the minimum value in the subtree rooted at the specified node.
     *
     * @param node the root of the subtree
     * @return the node with the minimum value
     */
    private Node minValueNode(Node node) {
        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    /**
     * Deletes a stock with the specified symbol from the subtree rooted at the specified node.
     *
     * @param root   the root of the subtree
     * @param symbol the symbol of the stock to be deleted
     * @return the new root of the subtree
     */
    private Node deleteNode(Node root, String symbol) {
        if (root == null)
            return root;

        if (symbol.compareTo(root.stock.getSymbol()) < 0)
            root.left = deleteNode(root.left, symbol);
        else if (symbol.compareTo(root.stock.getSymbol()) > 0)
            root.right = deleteNode(root.right, symbol);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Node temp = minValueNode(root.right);
                root.stock = temp.stock;
                root.right = deleteNode(root.right, temp.stock.getSymbol());
            }
        }

        if (root == null)
            return root;

        root.height = max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRot(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRot(root.left);
            return rightRot(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRot(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRot(root.right);
            return leftRot(root);
        }

        return root;
    }

    /**
     * Searches for a stock with the specified symbol in the subtree rooted at the specified node.
     *
     * @param root   the root of the subtree
     * @param symbol the symbol of the stock to be searched
     * @return the stock if found, otherwise null
     */
    private Stock search(Node root, String symbol) {
        if (root == null || root.stock.getSymbol().equals(symbol))
            return root != null ? root.stock : null;

        if (root.stock.getSymbol().compareTo(symbol) > 0)
            return search(root.left, symbol);

        return search(root.right, symbol);
    }

    /**
     * Adds a new stock to the AVL tree.
     *
     * @param stock the stock to be added
     */
    public void addStock(Stock stock) {
        root = insert(root, stock);
    }

    /**
     * Removes a stock with the specified symbol from the AVL tree.
     *
     * @param symbol the symbol of the stock to be removed
     */
    public void removeStock(String symbol) {
        root = deleteNode(root, symbol);
    }

    /**
     * Searches for a stock with the specified symbol in the AVL tree.
     *
     * @param symbol the symbol of the stock to be searched
     * @return the stock if found, otherwise null
     */
    public Stock searchStock(String symbol) {
        return search(root, symbol);
    }

    /**
     * Performs an in-order traversal of the subtree rooted at the specified node.
     *
     * @param node the root of the subtree
     */
    public void inOrder(Node node) {
        if (node != null) {
            inOrder(node.left);
            System.out.println(node.stock);
            inOrder(node.right);
        }
    }

    /**
     * Performs a pre-order traversal of the subtree rooted at the specified node.
     *
     * @param node the root of the subtree
     */
    public void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.stock);
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    /**
     * Performs a post-order traversal of the subtree rooted at the specified node.
     *
     * @param node the root of the subtree
     */
    public void postOrder(Node node) {
        if (node != null) {
            postOrder(node.left);
            postOrder(node.right);
            System.out.println(node.stock);
        }
    }

    /**
     * Prints the AVL tree using an in-order traversal.
     */
    public void printInOrder() {
        inOrder(root);
    }

    /**
     * Prints the AVL tree using a pre-order traversal.
     */
    public void printPreOrder() {
        preOrder(root);
    }

    /**
     * Prints the AVL tree using a post-order traversal.
     */
    public void printPostOrder() {
        postOrder(root);
    }

    /**
     * Prints the AVL tree structure as text.
     */
    public void printTree() {
        printTree(root, "", true);
    }

    /**
     * Helper method to print the tree structure.
     *
     * @param node   the current node
     * @param indent the indentation string
     * @param last   whether the node is the last child
     */
    private void printTree(Node node, String indent, boolean last) {
        if (node != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R---- ");
                indent += "   ";
            } else {
                System.out.print("L---- ");
                indent += "|  ";
            }
            System.out.println(node.stock.getSymbol() + " (" + node.height + ")");
            printTree(node.left, indent, false);
            printTree(node.right, indent, true);
        }
    }
}
