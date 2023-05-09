package org.bastien.playwords.lexicaltree;

public abstract class AbstractNode {
    protected AbstractNode brother;

    public AbstractNode(AbstractNode brother) {
        this.brother = brother;
    }

    public abstract int count();

    public abstract AbstractNode add(String s);

    public abstract AbstractNode delete(String s);

    public abstract String toString(String s);
}
