package org.bastien.playwords.lexicaltree;

public class EmptyNode extends AbstractNode {

    private static final EmptyNode instance = new EmptyNode();

    public static EmptyNode getInstance() {
        return instance;
    }

    public EmptyNode() {
        super(null);
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public AbstractNode add(String s) {
        AbstractNode n = new LeafNode(EmptyNode.getInstance());
        for (int i = s.length() - 1; i >= 0; i--)
            n = new Node(this, n, s.charAt(i));
        return n;
    }

    @Override
    public AbstractNode delete(String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString(String s) {
        return "";
    }
}
