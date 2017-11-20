package com.davidaventimiglia.algs2.week2;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class SAP {
    Digraph graph = null;

    // do unit testing of this class
    public static void main (String[] args) {
    }

    // constructor takes a digraph (not necessarily a DAG)
    public SAP (Digraph G) {
	if (G==null) throw new IllegalArgumentException();
	graph = G;}

    protected int[] research (int v, int w) {
	BreadthFirstDirectedPaths pv = new BreadthFirstDirectedPaths(graph, v);
	BreadthFirstDirectedPaths pw = new BreadthFirstDirectedPaths(graph, w);
	int dw = -1, dv = -1;
	int aw = -1, av = -1;
	for (int i : ancestors(graph, v))
	    if (pw.hasPathTo(i)) {
		dv = pv.distTo(i) + pw.distTo(i);
		av = i;}
	for (int i : ancestors(graph, w))
	    if (pv.hasPathTo(i)) {
		dw = pv.distTo(i) + pw.distTo(i);
		aw = i;}
	if (dv<0 && dw<0) return new int[]{-1, -1};
	return dv < dw ? new int[]{av, dv} : new int[]{aw, dw};}

    protected int[] research (Iterable<Integer> v, Iterable<Integer> w) {
	int[] shortest = new int[]{-1, -1};
	for (int i : v)
	    for (int j : w) {
		int[] r = research(i, j);
		if (r[1]>0 && shortest[1]<0) shortest = r;
		if (r[1]>0 && shortest[1]>0)
		    if (r[1]<shortest[1]) shortest = r;}
	return shortest;}

    // length of shortest ancestral path between any vertex in v and
    // any vertex in w; -1 if no such path
    public int length (Iterable<Integer> v, Iterable<Integer> w) {
	return research(v, w)[1];}

    // a common ancestor that participates in shortest ancestral path;
    // -1 if no such path
    public int ancestor (Iterable<Integer> v, Iterable<Integer> w) {
	return research(v, w)[0];}

    // length of shortest ancestral path between v and w; -1 if no
    // such path
    public int length (int v, int w) {
	return length(Arrays.asList(v), Arrays.asList(w));}

    // a common ancestor of v and w that participates in a shortest
    // ancestral path; -1 if no such path
    public int ancestor (int v, int w) {
	return ancestor(Arrays.asList(v), Arrays.asList(w));}

    Iterable<Integer> ancestors (final Digraph g, final int s) {
	return new Iterable<Integer> () {
	    @Override
	    public Iterator<Integer> iterator () {
		boolean[] marked = new boolean[g.V()];
		final int[] edgeTo = new int[g.V()];
		edu.princeton.cs.algs4.Queue<Integer> q =
		    new edu.princeton.cs.algs4.Queue<>();
		q.enqueue(s);
		marked[s] = true;
		return new Iterator<Integer> () {
		    int v = s;
		    Iterator<Integer> a = g.adj(v).iterator();
		    @Override
		    public boolean hasNext () {
			if (!q.isEmpty()) return true;
			if (a.hasNext()) return true;
			return false;}
		    @Override
		    public Integer next () {
			if (!hasNext()) throw new NoSuchElementException();
			if (a.hasNext()) {
			    int w = a.next();
			    if (!marked[w]) {
				q.enqueue(w);
				marked[w] = true;
				edgeTo[w] = v;
				return v;}}
			if (!q.isEmpty()) {
			    v = q.dequeue();
			    a = g.adj(v).iterator();
			    return next();}
			throw new IllegalStateException();}};}};}}

