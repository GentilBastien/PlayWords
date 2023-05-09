package org.bastien.playwords.lexicaltree;

public class Node extends AbstractNode {
    private AbstractNode child;

    private final char value;

    public Node(AbstractNode brother, AbstractNode child, char value) {
        super(brother);
        this.child = child;
        this.value = value;
    }

    @Override
    public int count() {
        return brother.count() + child.count();
    }

    @Override
    public AbstractNode add(String s) {
        if (s.isEmpty())
            return new LeafNode(this);
        char c = s.charAt(0);
        if (c < value) {
            AbstractNode n = EmptyNode.getInstance().add(s);
            n.brother = this;
            return n;
        }
        if (c == value) {
            child = child.add(s.substring(1));
            return this;
        }
        brother = brother.add(s);
        return this;
    }

    @Override
    public AbstractNode delete(String s) {
        if (s.isEmpty())
            throw new UnsupportedOperationException();
        char c = s.charAt(0);
        if (c < value)
            throw new UnsupportedOperationException();
        if (c == value) {
            child = child.delete(s.substring(1));
            return child == EmptyNode.getInstance() ? brother : this;
        }
        brother = brother.delete(s);
        return this;
    }

    @Override
    public String toString(String s) {
        return child.toString(s + value) + brother.toString(s);
    }
}
