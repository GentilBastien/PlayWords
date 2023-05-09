package org.bastien.playwords.lexicaltree;

import java.util.Set;

public class LexicalTree {
    private AbstractNode root;

    private LexicalTree() {
        this.root = EmptyNode.getInstance();
    }

    public static LexicalTree buildTree(Set<String> strings) {
        LexicalTree tree = new LexicalTree();
        strings.forEach(tree::add);
        return tree;
    }

    public int count() {
        return this.root.count();
    }

    private void add(String s) {
        try {
            this.root = this.root.add(s);
        } catch (UnsupportedOperationException ignored) {
        }
    }

    private void delete(String s) {
        try {
            this.root = this.root.delete(s);
        } catch (UnsupportedOperationException ignored) {
        }
    }

    public String toString() {
        return this.root.toString("");
    }
}
