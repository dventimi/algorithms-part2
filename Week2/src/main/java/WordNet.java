package com.davidaventimiglia.algs2.week2;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordNet {
    Map<Integer, List<String>> sns;
    Digraph d;

    public WordNet (String synsets, String hypernyms) {
	if (synsets==null) throw new IllegalArgumentException();
	if (hypernyms==null) throw new IllegalArgumentException();
	sns = parseSynsets(synsets);
	d = parseHypernyms(hypernyms);}

    protected Map<Integer, List<String>> parseSynsets (String filename) {
	Map<Integer, List<String>> sns = new HashMap<>();
	In in = new In(filename);
	for (String line = in.readLine(); line!=null;) {
	    String[] fields = line.split(",");
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

    // is the word a WordNet noun?
    public boolean isNoun (String word) {
	if (word==null) throw new IllegalArgumentException();
	throw new UnsupportedOperationException();
    }

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
