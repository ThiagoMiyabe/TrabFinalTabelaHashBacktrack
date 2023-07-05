package tree;

public class Tree<K, V> {
    private Node root;

    private Node insert(Node root, int key, Object value) {
        if (root == null) {
            Node node = new Node();
            node.setKey(key);
            node.setValue(value);
            return node;
        } else {
            if (key < root.getKey()) {
                root.setLeft(this.insert(root.getLeft(), key, value));
            } else if (key > root.getKey()) {
                root.setRight(this.insert(root.getRight(), key, value));
            }
            return root;
        }
    }

    public void insert(int key, Object value) {
        if (this.root == null) {
            this.root = new Node();
            this.root.setKey(key);
            this.root.setValue(value);
        } else {
            this.root = this.insert(this.root, key, value);
        }
    }

    public Object get(int key) {
        return this.get(this.root, key);
    }

    private Object get(Node root, int key) {
        if (root != null) {
            if (key < root.getKey()) {
                return this.get(root.getLeft(), key);
            } else if (key > root.getKey()) {
                return this.get(root.getRight(), key);
            } else {
                return root.getValue();
            }
        } else {
            return null;
        }
    }

    private String print(Node root, int lvl) {
        if (root == null) {
            return "";
        }

        StringBuilder out = new StringBuilder();
        for (int i = 0; i < lvl; i++) {
            out.append("\t");
        }
        out.append(root.getKey()).append(": ").append(root.getValue() != null ? root.getValue() : "null").append("\n");
        out.append(print(root.getLeft(), lvl + 1));
        out.append(print(root.getRight(), lvl + 1));
        return out.toString();
    }

    @Override
    public String toString() {
        return print(this.root, 0);
    }

    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(Node node) {
        if (node != null) {
            System.out.println(node.getValue());
            preOrder(node.getLeft());
            preOrder(node.getRight());
        }
    }

    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(Node node) {
        if (node != null) {
            inOrder(node.getLeft());
            System.out.println(node.getValue());
            inOrder(node.getRight());
        }
    }

    public void postOrder() {
        postOrder(this.root);
    }

    private void postOrder(Node node) {
        if (node != null) {
            postOrder(node.getLeft());
            postOrder(node.getRight());
            System.out.println(node.getValue());
        }
    }

    public void delete(int key) {
        this.root = delete(this.root, key);
    }

    private Node delete(Node root, int key) {
        if (root == null) {
            return root;
        }

        if (key < root.getKey()) {
            root.setLeft(delete(root.getLeft(), key));
        } else if (key > root.getKey()) {
            root.setRight(delete(root.getRight(), key));
        } else {
            if (root.getLeft() == null && root.getRight() == null) {
                root = null;
            } else if (root.getLeft() == null) {
                root = root.getRight();
            } else if (root.getRight() == null) {
                root = root.getLeft();
            } else {
                Node successor = findMinimum(root.getRight());
                root.setKey(successor.getKey());
                root.setValue(successor.getValue());
                root.setRight(delete(root.getRight(), successor.getKey()));
            }
        }

        return root;
    }

    private Node findMinimum(Node node) {
        if (node.getLeft() != null) {
            return findMinimum(node.getLeft());
        }
        return node;
    }


}
