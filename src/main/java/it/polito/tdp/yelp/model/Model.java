package it.polito.tdp.yelp.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.yelp.db.YelpDao;

public class Model {
	
	private YelpDao dao;
	
	private Graph<Review, DefaultWeightedEdge> grafo;
	
	List<Review> vertici;
	
	List<Adiacenze> adiac;
	
	Map<String,Review> idMap;
	
	public Model() {
		
		dao = new YelpDao();
		
		vertici = new ArrayList<>();
		
		idMap = new HashMap<>();
		
		adiac = new ArrayList<>();
		
	}
	
	public void creaGrafo(String name, String city) {
		
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		
		//vertici
		
		vertici = this.dao.getVertici(name, city, idMap);
		
		Graphs.addAllVertices(this.grafo, vertici);
		
		System.out.println("n vertici " + grafo.vertexSet().size());
		
		
		//archi
		
		adiac = this.dao.getAdiacenze(name, city, idMap);
		
		for(Adiacenze a : adiac) {
			
				Graphs.addEdgeWithVertices(this.grafo, a.getR1(), a.getR2(), a.getPeso());
			
		}
		
		
	}
	
	public List<String> getCity() {
		List<String> citta = new ArrayList<>(this.dao.getAllCity());
		return citta;
	}
	
	public List<Business> getLocali(String city) {
		List<Business> locali = new ArrayList<>(this.dao.getLocaliCommerciali(city));
		return locali;
	}

	public int nVertici() {
		return this.grafo.vertexSet().size();
	}

	public int nArchi() {
		return this.grafo.edgeSet().size();
	}
	
	
}
