/**
 * This text analyzer reads a file from provided URL and outputs text of poem to be analyzed and 
 * the word frequencies of all words in the file in the form of pair 
 * sorted by the most frequently used word. 
 * User has an option to display the top 20 words on the console and to access web-site to see original text of poem. 
 * 
 * @author Julia Smith
 * @date   Oct 18, 2020
 */

package application;
	
import java.awt.Desktop;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Document;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Method creating layout and functionality of GUI
 *
 */
public class Main extends Application {
	
	/**
	 * Method, where customization of components of GUI is specified
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			
			/**
			 * Creating BorderPane and scene with padding and defying style
			 */
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,700,600);
			
			root.setPadding(new Insets(5, 5, 5, 5));
			root.setStyle("-fx-border-width: 5;-fx-border-color: #4d4b4b;  -fx-opacity: 0.9;");
										
			/**
			 * Creating a input stream for accessing background file on local 
			 */
			
           // FileInputStream input = new FileInputStream("C:\\Users\\Julia\\Documents\\SoftwareDevelopment1\\GUI\\library.jpg"); 
            	
			
			/**
			 * Creating a inputStream for accessing background file using URL 
			 */
			
			try {
			InputStream input = new URL("https://c0.wallpaperflare.com/preview/416/65/82/the-john-rylands-library-john-rylands-library-libraries.jpg").openStream();
            
			 
		//	try {
		//		FileInputStream input  = new FileInputStream("library.jpg");
		 
				
            
            /**
             * create a image for background
             */
            Image image = new Image(input); 
  
            /**
             * create a backgroundimage
             */
            BackgroundImage backgroundimage = new BackgroundImage(image,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundRepeat.NO_REPEAT,  
                                             BackgroundPosition.DEFAULT,  
                                             BackgroundSize.DEFAULT); 
  
            /**
             * creating background
             */ 
            Background background = new Background(backgroundimage); 
            
            
  
            /**
             * set background 
             * 
            */ 
           root.setBackground(background); 
            
 ////
           
           
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
////
           /**
            * adding Gridpane to the display
            * 
           */
          root.setCenter(addGridPane());
			
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
/**
 * This method creates gridpane for the app	
 * @return Gridpane
 */
	public static GridPane addGridPane() {
		
		/**
		 * setting grid, it's gaps and padding
		 */
		
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));

		/**
		 * creating columns, rows and their alignment
		 * adding them to the grid
		 * 	 */
		ColumnConstraints col = new ColumnConstraints();
		col.setHalignment(HPos.CENTER);
		grid.getColumnConstraints().add(col);

		RowConstraints row = new RowConstraints();
		row.setValignment(VPos.CENTER);
		grid.getRowConstraints().add(row);

/**
 * Creating text area for header
 * and it's style 
 */
		Text greeting = new Text("Welcome, Dear Guest!");
		 greeting.setFill(Color.web("#FFFFFF"));
		 greeting.setStyle("-fx-font-weight: bold");
		//-fx-text-inner-color: red;
	      greeting.setFont(Font.font("Franklin Gothic", FontWeight.BOLD, FontPosture.ITALIC, 40)); 
		
		GridPane.setValignment(greeting, VPos.CENTER);
		GridPane.setHalignment(greeting, HPos.CENTER);
		grid.add(greeting, 2,0); // column#, row#, column span, row span 2,0, 10, 1
		
 

		try {
			/**
			 * creating document to store information from accessed URL
			 * @throws IOException
			 */
			Document document = Jsoup.connect("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm").get();

			/**
			 * Getting headers
			 */
			Elements header = document.select("h1,h4,h3");

			/**
			 * Getting text from elements of class .poem 
			 */
			Elements poem = document.select("p.poem");

			/**
			 * Storing retrieved elements into String
			 */
			String elementsText = header.text() + " " + poem.text();

			/**
			 * getting rid off the punctuation 
			 */
			String text = elementsText.replaceAll("[^a-zA-Z0-9]", " "); 
			String stringToUse = text.replaceAll("\\s+", " ");

			
		/**
		 * Creating TextArea for specs
		 */
		TextArea specsArea = new TextArea("This program will access file containing poem *The Raven* by Edgar Allan Poe, "
				+ "\nstorred on the server of the Project Gutenberg and will find the most used words in its text\n");
		specsArea.setPrefHeight(150.0);
		specsArea.setWrapText(true);
		GridPane.setValignment(specsArea, VPos.CENTER);
		GridPane.setHalignment(specsArea, HPos.CENTER);
		grid.add(specsArea, 2, 3);

		/**
		 * Button that opens web location of the poem
		 */
		Button web = new Button("Project Gutenberg");
		web.setTextFill(Color.web("#282828"));
		web.setStyle("-fx-font-weight: bold");
	    web.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12)); 
			
	    web.setOnAction(new EventHandler<ActionEvent>() {
	    	   @Override public void handle(ActionEvent e) {
	    	        try {
	    	            Desktop.getDesktop().browse(new URI("https://www.gutenberg.org/files/1065/1065-h/1065-h.htm"));
	    	        } catch (IOException e1) {
	    	            e1.printStackTrace();
	    	        } catch (URISyntaxException e1) {
	    	            e1.printStackTrace();
	    	        }
	    	        }
	    	    }
	    	);
		    
		grid.add(web, 2, 2);
		

		
		
		/**
		 * Creating Label
		 */
		Label before = new Label();
		before.setPrefHeight(150.0);
		GridPane.setValignment(before, VPos.CENTER);
		GridPane.setHalignment(before, HPos.LEFT);
		
		before.setTextFill(Color.web("#FFFFFF"));
		before.setStyle("-fx-font-weight: bold");
		before.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12)); 
	    
		grid.add(before, 2, 4);
		before.setWrapText(true);
		before.setText("Text of poem for analyzing");
		
		

		
			
				/**
				 * Converting String to List
				 */
				String[] stringArray = stringToUse.split(" ");
				List<String> textToProcess = new ArrayList<String>();

				/**
				 * creating new map to hold data after iteration
				 */
				Map<String, Integer> map = new HashMap<String, Integer>();

				/**
				 *  counting words in array 
				 */
				for (String word : stringArray) {
					Integer num = map.get(word);
					num = (num == null) ? 1 : ++num;
					map.put(word, num);

				}

				/**
				 * Creating treeMap to sort data sets
				 */
				Map<String, Integer> treeMap = new TreeMap<String, Integer>() {

					public int compare(Integer o1, Integer o2) {
						return o2.compareTo(o1);
					}
				};
				treeMap.putAll(map);

				/**
				 * Creating textArea for displaying text to analyze 
				 */
				 TextArea beforeArea = new TextArea();//treeMap.toString()
				 beforeArea.setText(stringToUse); 
				 
					
					beforeArea.setPrefHeight(150.0);
					beforeArea.setWrapText(true);
					GridPane.setValignment(beforeArea, VPos.CENTER);
					GridPane.setHalignment(beforeArea, HPos.CENTER);
					grid.add(beforeArea, 2, 5);


					/**
					 * Creating label and styling it
					 */
							Label after = new Label();
							after.setPrefHeight(150.0);
							GridPane.setValignment(after, VPos.CENTER);
							GridPane.setHalignment(after, HPos.LEFT);
							
							after.setTextFill(Color.web("#FFFFFF"));
							after.setStyle("-fx-font-weight: bold");
							after.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12)); 
						    
							grid.add(after, 2, 6);
							after.setWrapText(true);
							after.setText("Frequency of each word in poem");
							
							
							/**
							 * Creating sorted map - sorted by value of integer - frequency
							 */
				Map<String, Integer> sortedMap = sortByValue(treeMap);
				
				/**
				 * Reversing order of elements of the map in descending order
				 */
				LinkedHashMap<String, Integer> reverseSortedMap = new LinkedHashMap<>();
				sortedMap.entrySet().stream().sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
						.forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));


				/**
				 * Textarea to display sorted words
				 */
				TextArea afterArea = new TextArea();
				afterArea.setPrefHeight(150.0);
				afterArea.setWrapText(true);
				GridPane.setValignment(afterArea, VPos.CENTER);
				GridPane.setHalignment(afterArea, HPos.CENTER);
				afterArea.setText(treeMap.toString());   
				grid.add(afterArea, 2, 7);
				
				/**
				 * Button that prints top 20 words on console
				 */
				Button save = new Button("GET TOP 20 words on your console");
				save.setTextFill(Color.web("#282828"));
				save.setStyle("-fx-font-weight: bold");
				save.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12)); 
				
    
				save.setOnAction(new EventHandler<ActionEvent>() {
			    	   @Override public void handle(ActionEvent e) {
			    	       System.out.println("Printed by clicking button"); 
			    		   printTop20(reverseSortedMap);

			    	        }
			    	    }
			    	);
				    
				grid.add(save, 2, 8);
				
				
		} catch (IOException e) {
			e.printStackTrace();
		}

		return grid;
	}

	
	/**
	 * Method that launches the app
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Method printing top 20 words
	 * @param reverseSortedMap
	 * @return void
	 */
	private static void printTop20(LinkedHashMap<String, Integer> reverseSortedMap) {

 /**
  * Creating list and subtracting first 20 elements
  */
		List array = new ArrayList(reverseSortedMap.keySet());
		array = array.subList(0, 20); 

		Set<Entry<String, Integer>> setOfEntries = reverseSortedMap.entrySet();
	/**
	 *  getting the iterator from entry set
	 */
		Iterator<Entry<String, Integer>> iterator = setOfEntries.iterator();

		/**
		 * iterating over map
		 */  
		while (iterator.hasNext()) {
			Entry<String, Integer> entry = iterator.next();
			Integer value = entry.getValue();

			if (value.compareTo(Integer.valueOf((int) 8)) < 0) { 
				iterator.remove(); // removing words that are not in the top 20

			}

		}

		/**
		 * printing top 20 
		 */
		for (Entry<String, Integer> entry : setOfEntries) {
			System.out.println("Word *" + entry.getKey() + "*" + " was used " + entry.getValue() + " times.");
		
		
/////
		System.out.print(setOfEntries.toString() + "DELETE THIS");	
/////
		
		
		}

	}

	
	/**
	 * Method that sorts treeMap by value of integer
	 * @param treeMap
	 * @return sorted map
	 */
	static Map<String, Integer> sortByValue(Map<String, Integer> treeMap) {

		/**
		 *  Map to List of Map
		 */
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(treeMap.entrySet());

		/**
		 * Sort list with Collections.sort()
		 */
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		/**
		 *  Loop the sorted list and put it into a new order Map LinkedHashMap
		 */
		Map<String, Integer> sortedMap = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> entry : list) {
			sortedMap.put(entry.getKey(), entry.getValue());
		}

		return sortedMap;
	}

	/**
	 * Method that formating printed output 
	 * @param <S>
	 * @param <I>
	 * @param map
	 */
	public static <S, I> void printMap(Map<S, I> map) {
		
		for (Map.Entry<S, I> entry : map.entrySet()) {
			 System.out.println("Word:" + entry.getKey() + "||" + " Frequency: " + entry.getValue());
					}
		
		
	}
}
