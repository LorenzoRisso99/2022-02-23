package it.polito.tdp.yelp.db;

import java.util.HashMap;
import java.util.Map;

import it.polito.tdp.yelp.model.Review;

public class TestDao {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,Review> idMap= new HashMap<>();
		
		YelpDao dao = new YelpDao();
		System.out.println(dao.getVertici("Guitar Center", "Avondale", idMap).size());
		
	}

}
