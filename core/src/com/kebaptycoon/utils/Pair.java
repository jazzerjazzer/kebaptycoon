package com.kebaptycoon.utils;

/*
 * Pair.java
 * 
 * A generic class that holds two arbitrary values
 */
public class Pair<L,R> {

	public L left;
	public R right;

	public Pair(){}
	public Pair(L left, R right) {
		this.left = left;
		this.right = right;
	}

	public L getLeft() { return left; }
	public R getRight() { return right; }

	@Override
	public int hashCode() { return left.hashCode() ^ right.hashCode(); }

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Pair)) return false;
		Pair<?, ?> pairo = (Pair<?, ?>) o;
		return this.left.equals(pairo.getLeft()) &&
				this.right.equals(pairo.getRight());
	}

    public String toString()
    {
        return "[" + left.toString() + ", " + right.toString() + "]";
    }

}
