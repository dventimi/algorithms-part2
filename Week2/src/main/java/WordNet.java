package com.davidaventimiglia.algs2.week2;

import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordNet {
    Map<Integer, String> id2synset = new HashMap<>();
    Map<Integer, List<String>> id2nouns = new HashMap<>();
    Map<String, Integer> noun2id = new HashMap<>();
    Map<String, Integer> synset2id = new HashMap<>();
    Digraph d;
    SAP sap;

    // do unit testing of this class
    public static void main (String[] args) {
	WordNet w = new WordNet(args[0], args[1]);
	System.out.println(w);
	for (String n : w.nouns()) System.out.println(n);
    }

    public static Map head (Map map, int... n) {
	int k = n.length>0 ? n[0] : 10;
	Map head = new HashMap();
	int i = 0;
	for (Object key : map.keySet()) {
	    head.put(key, map.get(key));
	    i++;
	    if (i==k) break;}
	return head;}

    public WordNet () {}

    public WordNet (String synsets, String hypernyms) {
	if (synsets==null) throw new IllegalArgumentException();
	if (hypernyms==null) throw new IllegalArgumentException();
	parseSynsets(synsets.trim());
	generateNouns();
	parseHypernyms(hypernyms.trim());
	sap = new SAP(d);}

    @Override
    public String toString () {
	StringBuffer sb = new StringBuffer();
	sb.append(head(id2synset).toString()).append("\n");
	sb.append(head(synset2id).toString()).append("\n");
	sb.append(head(id2nouns).toString()).append("\n");
	sb.append(head(noun2id).toString()).append("\n");
	sb.append(d.toString()).append("\n");
	return sb.toString();}

    protected void parseSynsets (String filename) {
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

    protected void parseHypernyms (String filename) {
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
	for (String n : nouns()) if (n.equals(word)) return true;
	return false;}

    // distance between nounA and nounB (defined below)
    public int distance (String nounA, String nounB) {
	if (nounA==null) throw new IllegalArgumentException();
	if (nounB==null) throw new IllegalArgumentException();
	throw new UnsupportedOperationException();
    }

    public Integer noun2id (String noun) {
	return noun2id.get(noun);}

    public Digraph getDigraph () {
	return d;}

    // a synset (second field of synsets.txt) that is the common
    // ancestor of nounA and nounB in a shortest ancestral path
    // (defined below)
    public String sap (String nounA, String nounB) {
	if (nounA==null) throw new IllegalArgumentException();
	if (nounB==null) throw new IllegalArgumentException();
	return id2synset.get(sap.ancestor(noun2id.get(nounA), noun2id.get(nounB)));}
}
