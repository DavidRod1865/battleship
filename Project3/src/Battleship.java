import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Battleship{
	
	// Define static variables to keep track of the number of sunk ships and guesses
	static int shipsSunk = 0;
	static int guessCount = 0;
	
	public static void main(String[] args) {
		
	    // Create a 2D array to represent the game board where ships will be placed
	    Ship[][] shipLocations = new Ship[11][11];

	    // Place five different ships on the board
	    placeShip(new Ship("Carrier", 5), shipLocations);
	    placeShip(new Ship("Battleship", 4), shipLocations);
	    placeShip(new Ship("Cruiser", 3), shipLocations);
	    placeShip(new Ship("Submarine", 3), shipLocations);
	    placeShip(new Ship("Destroyer", 2), shipLocations);

	    // Create the game's interface
	    JFrame frame = new JFrame("BattleShip");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    JPanel gridPanel = createGrid(11, 11, shipLocations, frame);
	    frame.add(gridPanel);
	    frame.setPreferredSize(new Dimension(600, 600));
	    frame.pack();
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);

	    // Show a welcome message to the player
	    JOptionPane.showMessageDialog(frame, "Welcome to Battleship");
	}
	
	//randomly places a ship on the board, avoiding conflicts with other ships
	private static void placeShip(Ship ship, Ship[][] shipLocations) {
		boolean conflict;
		int startRow, startCol;
		
		//horizontal ship placement
		if(Math.random() < 0.5) {
			do {
				conflict = false;
				startRow = (int)(Math.random() * (11 - ship.getSize()) + 1);
				startCol = (int)(Math.random() * (11 - ship.getSize()) + 1);
				for(int i = 0; i < ship.getSize(); i++) {
					if(shipLocations[startRow][startCol + i] != null) {
						conflict = true;
					}
				}
			}while(conflict);
			for(int i = 0; i < ship.getSize(); i++) {
				shipLocations[startRow][startCol + i] = ship;
			}
			
		//vertical ship placement
		}else {
			do {
				conflict = false;
				startRow = (int)(Math.random() * (11 - ship.getSize()) + 1);
				startCol = (int)(Math.random() * (11 - ship.getSize()) + 1);
				for(int i = 0; i < ship.getSize(); i++) {
					if(shipLocations[startRow + i][startCol] != null) {
						conflict = true;
					}
				}
			}while(conflict);
			for(int i = 0; i < ship.getSize(); i++) {
				shipLocations[startRow + i][startCol] = ship;
			}
		}
	}
	
	//creates grid for the JFrame and adds ships / buttons to the grid
	private static JPanel createGrid(int rows, int columns, Ship[][] shipLocations, JFrame frame) {
	    JPanel panel = new JPanel(new GridLayout(rows, columns));
	    panel.add(new JLabel(""));

	    // Add column labels (1 to 10) at the top of the grid
	    for (int i = 1; i < columns; i++) {
	        panel.add(new JLabel(Integer.toString(i), SwingConstants.CENTER));
	    }

	    for (int row = 0; row < rows; row++) {
	        if (row == 0) { // Skip the first row (row 0) as it's already filled with column labels
	            continue;
	        }
	        // Add the row labels ('A' to 'J') on the left side of the grid
	        panel.add(new JLabel(String.valueOf((char) ('A' + row - 1)), SwingConstants.CENTER));
	        for (int col = 1; col < columns; col++) { // Loop through columns (1 to 10)
	            if (col == 0) { // Skip the first column (column 0) as it's already filled with row labels
	                continue;
	            }
	            JButton button = new JButton(".");
	            button.setBorder(BorderFactory.createEmptyBorder());
	            button.setBackground(Color.CYAN);
	            button.setOpaque(true);
	            int finalRow = row - 1; // Adjust the row index to match the shipLocations array
	            int finalCol = col - 1; // Adjust the column index to match the shipLocations array
	            button.addActionListener(new ActionListener() {
	                public void actionPerformed(ActionEvent e) {
	                    handleButtonClick(finalRow, finalCol, shipLocations, button, frame);
	                }
	            });
	            panel.add(button);
	        }
	    }

	    return panel;
	}

	private static void handleButtonClick(int row, int col, Ship[][] shipLocations, JButton buttonClicked, JFrame frame) {
		
		// Checks for out of bounds
	    if (row < 0 || row >= shipLocations.length || col < 0 || col >= shipLocations[0].length) {
	        return;
	    }
	    
	    // If clicked already, alert the player that they already made this choice
	    if (Color.WHITE.equals(buttonClicked.getBackground()) || Color.RED.equals(buttonClicked.getBackground())) {
	        JOptionPane.showMessageDialog(buttonClicked, "You already guessed that space!");
	        return;
	    }
	    
	    // Determine if the location has a ship and update the button accordingly
	    Ship ship = shipLocations[row + 1][col + 1]; // Adjust the row and column indices
	    if (ship != null) {
	        buttonClicked.setBackground(Color.RED);
	        JOptionPane.showMessageDialog(buttonClicked, String.valueOf((char) ('A' + row)) + String.valueOf(col + 1) + ": Hit!");
	        Battleship.guessCount++; // Update the guess count (assuming Battleship.guessCount is a static variable)
	        ship.hit();
	        
	        // Check if the ship is sunk
	        if (ship.isSunk()) {
	            Battleship.shipsSunk++; // Update the number of sunk ships (assuming Battleship.shipsSunk is a static variable)
	            JOptionPane.showMessageDialog(buttonClicked, "You sunk the " + ship + "!");
	            
	            if (Battleship.shipsSunk == 5) { // Check if all ships are sunk
	                JOptionPane.showMessageDialog(frame, "Victory! You sunk all ships in " + Battleship.guessCount + " guesses.");
	            }
	        }
	    } else {
	        buttonClicked.setBackground(Color.WHITE);
	        JOptionPane.showMessageDialog(buttonClicked, String.valueOf((char) ('A' + row)) + String.valueOf(col + 1) + ": Miss!");
	        Battleship.guessCount++; // Update the guess count
	    }
	}
}