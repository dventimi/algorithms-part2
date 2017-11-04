import edu.princeton.cs.algs4.*;
import java.util.*;

public class WordNet {
    Map<Integer, String> id2synset = new HashMap<>();
    Map<Integer, List<String>> id2nouns = new HashMap<>();
    Map<String, Integer> synset2id = new HashMap<>();
    Map<String, Integer> noun2id = new HashMap<>();
    Digraph d;
    SAP sap;

    // do unit testing of this class
    public static void main (String[] args) {
	new WordNet(args[0], args[1]);
    }

    // constructor takes the name of the two input files
    public WordNet (String synsets, String hypernyms) {
	if (synsets==null) throw new IllegalArgumentException();
	if (hypernyms==null) throw new IllegalArgumentException();
	parseSynsets(synsets);
	parseNouns(id2synset);
	parseHypernyms(hypernyms);
	sap = new SAP(d);}

    @Override
    public String toString () {
	StringBuffer sb = new StringBuffer();
	sb.append(id2nouns.toString());
	sb.append("\n");
	sb.append(noun2id.toString());
	sb.append("\n");
	sb.append(d);
	sb.append("\n");
	return sb.toString();}

    protected void parseSynsets (String filename) {
	In in = new In(filename);
	String line = in.readLine();
	while (line!=null) {
	    String[] fields = line.split(",");
	    id2synset.put(Integer.parseInt(fields[0]), fields[1]);
	    synset2id.put(fields[1], Integer.parseInt(fields[0]));}}

    protected void parseNouns (Map<Integer, String> synsets) {
	for (Map.Entry<Integer, String> e : synsets.entrySet()) {
	    id2nouns.putIfAbsent(e.getKey(), new ArrayList<>());
	    id2nouns.get(e.getKey()).addAll(Arrays.asList(e.getValue().split(" ")));
	    for (String n : id2nouns.get(e.getKey()))
		noun2id.put(n, e.getKey());}}

    protected void parseHypernyms (String filename) {
	In in = new In(filename);
	String line = in.readLine();
	d = new Digraph(id2nouns.keySet().size());
	while (line!=null) {
	    String[] fields = line.split(",");
	    for (int i = 1; i<fields.length; i++)
		d.addEdge(Integer.parseInt(fields[i]),
			  Integer.parseInt(fields[0]));}}

    // returns all WordNet nouns
    public Iterable<String> nouns () {
	return new Iterable<String> () {
	    @Override
	    public Iterator<String> iterator () {
		return new Iterator<String> () {
		    Iterator<List<String>> iterables = id2nouns.values().iterator();
		    Iterator<String> source;
		    @Override
		    public boolean hasNext () {
			if (source==null && iterables.hasNext())
			    source = iterables.next().iterator();
			if (source.hasNext()) return true;
			return false;}
		    @Override
		    public String next () {
			if (!hasNext()) throw new NoSuchElementException();
			return source.next();}};}};}

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

    // a synset (second field of synsets.txt) that is the common
    // ancestor of nounA and nounB in a shortest ancestral path
    // (defined below)
    public String sap (String nounA, String nounB) {
	if (nounA==null) throw new IllegalArgumentException();
	if (nounB==null) throw new IllegalArgumentException();
	// sap.ancestor(noun2id.get(nounA), noun2id.get(nounB))
	throw new UnsupportedOperationException();
    }
}
