package org.bastien.playwords.lexicaltree;

public class LeafNode extends AbstractNode {

    public LeafNode(AbstractNode brother) {
        super(brother);
    }

    @Override
    public int count() {
        return 1 + brother.count();
    }

    @Override
    public AbstractNode add(String s) {
        if (s.isEmpty())
            return this;
        brother = brother.add(s);
        return this;
    }

    @Override
    public AbstractNode delete(String s) {
        if (s.isEmpty())
            return brother;
        brother = brother.delete(s);
        return this;
    }

    @Override
    public String toString(String s) {
        return s + "\n" + brother.toString(s);
    }
}
