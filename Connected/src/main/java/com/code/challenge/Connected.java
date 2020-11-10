package com.code.challenge;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Connected {
	
	private static final List<String> allFileLines = new ArrayList<>();
	private static final Set<String> allCities = new HashSet<>();

	public static void main(String[] args) throws Exception {

		String sourceCity = null;
		String destCity = null;
		String filename = null;
		BufferedReader bufferReader = null;
		boolean isCityConnected = false;
		
		try {
			if (0 < args.length) {
				filename = args[0];
				if (filename == null && !"cities.txt".equalsIgnoreCase(filename.trim())) {
					throw new Exception(" Please input a valid filename!!");
				}
				if (args.length == 1) {
					throw new Exception(" Please input source & destination cities!!");
				} else if (args.length == 2) {
					throw new Exception(" Please input a destination city!!");
				}
				sourceCity = args[1];
				destCity = args[2];
				
				if (sourceCity == null || "".equals(sourceCity.trim())) {
					throw new Exception("Please input source & destination city !!");
				}
				if (destCity == null || "".equals(destCity.trim())) {
					throw new Exception(" Please input a destination city!!");
				}
				
				sourceCity = sourceCity.trim();
				destCity = destCity.trim();
			} else {
				throw new Exception("Please enter valid inputs !!n");
			}
			
			Connected app = new Connected();
			InputStream is = app.getFileFromResourceAsStream(filename);
			loadAllLinesToMemory(is);
			loadAllCities();
			validateInputCities(sourceCity, destCity);
			CityGraph graph = new CityGraph();
			for (String line: allFileLines) {
				String[] citiesInRoute = line.split(",");
				ArrayList<String> citiesList = new ArrayList<>();
				for (String city: citiesInRoute) {
					citiesList.add(city.trim());
				}
				if (citiesList.size() >=2) {
					for (int i=0;i<citiesList.size()-1;i++) {
						graph.addCityEdge(new CityNode(citiesList.get(i)), new CityNode(citiesList.get(i+1)));					
					}
				}
				graph.setDestCityNode(new CityNode(destCity));
			}
			isCityConnected = graph.searchDestinationUsingDfsAlg(new CityNode(sourceCity));
			if (isCityConnected) {
				System.out.println("\nYes\n");
			} else {
				System.out.println("\nNo\n");
			}
			
		} catch (Exception ex) {
			System.out.println("Unable to execute : "+ex.getMessage());
		} finally {
			try {
				if (bufferReader != null) {
					bufferReader.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private InputStream getFileFromResourceAsStream(final String fileName) throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(fileName);
		
		if (inputStream == null) {
			throw new Exception("File not found !!/n");
		} else {
			return inputStream;
		}
	}
	
	private static void loadAllLinesToMemory(final InputStream is) throws Exception {
		
		InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
		BufferedReader reader = new BufferedReader(streamReader);
		String line;
		while ((line = reader.readLine()) != null ) {
			allFileLines.add(line);
			//System.out.println(line);
		}		
	}
	
	private static void loadAllCities() {
		for (String s : allFileLines) {
			String[] cities = s.split(",");
			for (String city: cities) {
				allCities.add(city.trim());
			}
		}
	}
	
	private static boolean validateInputCities(final String city1, final String city2) throws Exception{
		if (!allCities.contains(city1)) {
			throw new Exception ("City : "+city1+ " not found in the file!\n");
		}
		if (!allCities.contains(city2)) {
			throw new Exception ("City : "+city2+ " not found in the file!\n");
		}
		return true;
	}
}
