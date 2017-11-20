package com.davidaventimiglia.algs2.week2;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordNet {
    Map<Integer, List<String>> fidx;
    Map<String, Integer> ridx = new HashMap<>();
    Digraph d;

    public WordNet (String synsets, String hypernyms) {
	if (synsets==null) throw new IllegalArgumentException();
	if (hypernyms==null) throw new IllegalArgumentException();
	fidx = parseSynsets(synsets);
	d = parseHypernyms(hypernyms);}

    protected Map<Integer, List<String>> parseSynsets (String filename) {
	Map<Integer, List<String>> fidx = new HashMap<>();
	In in = new In(filename);
	for (String line = in.readLine(); line!=null;) {
	    String[] fields = line.split(",");
<<<<<<< HEAD
	    sns
		.putIfAbsent(Integer.parseInt(fields[0]),
			     new ArrayList<>());
	    sns
		.get(Integer.parseInt(fields[0]))
		.addAll(Arrays.asList(fields[1].split(" ")));
	    line = in.readLine();}
	return sns;}

    protected Digraph parseHypernyms (String filename) {
	In in = new In(filename);
	Digraph d = new Digraph(sns.keySet().size());
	for (String line = in.readLine(); line!=null;) {
=======
	    fidx.putIfAbsent(Integer.parseInt(fields[0]), new ArrayList<>());
	    fidx.get(fields[0]).addAll(Arrays.asList(fields[1].split(" ")));}
	return fidx;}

    protected Digraph parseHypernyms (String filename) {
	In in = new In(filename);
	String line = in.readLine();
	Digraph d = new Digraph(fidx.keySet().size());
	while (line!=null) {
>>>>>>> 89021b7... Adding a reverse index
	    String[] fields = line.split(",");
	    for (int i = 1; i<fields.length; i++)
		d.addEdge(Integer.parseInt(fields[i]),
			  Integer.parseInt(fields[0]));
	    line = in.readLine();}
	return d;}

    // returns all WordNet nouns
    public Iterable<String> nouns () {
	return new Iterable<String> () {
	    @Override
	    public Iterator<String> iterator () {
		return new Iterator<String> () {
<<<<<<< HEAD
		    Iterator<List<String>> sources = sns.values().iterator();
		    Iterator<String> source;
		    @Override
		    public boolean hasNext () {
			if (source==null && sources.hasNext()) source = sources.next().iterator();
			if (source!=null && !source.hasNext() && sources.hasNext()) source = sources.next().iterator();
			if (source!=null && source.hasNext()) return true;
			return false;}
		    @Override
		    public String next () {
			if (source.hasNext()) return source.next();
			throw new NoSuchElementException();}};}};}
=======
		    Iterator<List<String>> iterables = fidx.values().iterator();
		    Iterator<String> source;
		    @Override
		    public boolean hasNext () {
			if (source==null && iterables.hasNext()) source = iterables.next().iterator();
			if (source.hasNext()) return true;
			return false;}
		    @Override
		    public String next () {
			if (!hasNext()) throw new NoSuchElementException();
			return source.next();}};}};}
>>>>>>> 89021b7... Adding a reverse index

    // is the word a WordNet noun?
    public boolean isNoun (String word) {
	if (word==null) throw new IllegalArgumentException();
	for (String n : nouns()) if (n.equals(word)) return true;
	return false;}

    // distance between nounA and nounB (defined below)
    public int distance (String nounA, String nounB) {
	if (nounA==null) throw new IllegalArgumentException();
	if (nounB==null) throw new IllegalArgumentException();
	throw new UnsupportedOperationException();
    }

    // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // in a shortest ancestral path (defined below)
    public String sap (String nounA, String nounB) {
	if (nounA==null) throw new IllegalArgumentException();
	if (nounB==null) throw new IllegalArgumentException();
	throw new UnsupportedOperationException();
    }

    // do unit testing of this class
    public static void main (String[] args) {
    }
}
