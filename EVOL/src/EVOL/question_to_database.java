package EVOL;

import java.sql.*;
import java.util.Scanner;

public class question_to_database {
	static final String ASK_OPTION = "ask"; 
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Connection con = EVOL.connectToDataBase("EVOL");
		
		SQLquestions(con, ASK_OPTION);
		//printAvailables(con, EVOL.SCOUTER_STR);
		
		return;
	}
	
	public static void printAvailables(Connection con, String vehcleType) throws SQLException{
		Statement stmt = con.createStatement();
		Statement stmt2 = con.createStatement();
		
		
		
		String selectQuery = "";
		ResultSet resultSet;
		String selectQuery2 = "";
		ResultSet resultSet2;

		
		String vehicleID = "";
		String vehicleBrand = "";
		String vehicleModel = "";
		String vehicleColor = "";
		double vehicleKM = 0;
		double vehiclecost = 0;
		double vehicleinsuranceCost = 0;
		String vehicleType = "";
		int vehiclePassengesNum = 0;
		
		
		
		
		if(vehcleType == EVOL.CAR_STR) {
			selectQuery = "SELECT Αναγνωριστικός_αριθμός_οχήματος, Χρώμα, Αυτονομία_σε_χιλιόμετρα, Κόστος_ενοικίασης, Κόστος_Ασφάλισης, Τύπος_Αυτοκινητου, Αρ_επιβατών  FROM "+ vehcleType +" WHERE Βλάβη IS NULL AND Ενοικιασμενο = '" + EVOL.NO_STR + "'";
		}
		else {
			selectQuery = "SELECT Αναγνωριστικός_αριθμός_οχήματος, Χρώμα, Αυτονομία_σε_χιλιόμετρα, Κόστος_ενοικίασης, Κόστος_Ασφάλισης FROM "+ vehcleType +" WHERE Βλάβη IS NULL AND Ενοικιασμενο = '" + EVOL.NO_STR + "'";
		}
		
		resultSet = stmt.executeQuery(selectQuery);
		
		if(!resultSet.isBeforeFirst()) {
			System.err.println("There are no "+ vehcleType + " that can be rented right now.");
		}
		
		int i = 1;
		while (resultSet.next()) {
			vehicleID = resultSet.getString("Αναγνωριστικός_αριθμός_οχήματος");
			
			selectQuery2 = "SELECT Μάρκα, Μοντέλο FROM Μάρκα_Οχήματος WHERE Αναγνωριστικός_αριθμός_οχήματος = " +vehicleID;
			resultSet2 = stmt2.executeQuery(selectQuery2);
			while(resultSet2.next()) {
				vehicleBrand = resultSet2.getString("Μάρκα");
				vehicleModel = resultSet2.getString("Μοντέλο");
			}
			
			vehicleColor = resultSet.getString("Χρώμα");
			vehicleKM = resultSet.getDouble("Αυτονομία_σε_χιλιόμετρα");
			vehiclecost = resultSet.getDouble("Κόστος_ενοικίασης");
			vehicleinsuranceCost = resultSet.getDouble("Κόστος_Ασφάλισης");
			if(vehcleType == EVOL.CAR_STR) {
				vehicleType = resultSet.getString("Τύπος_Αυτοκινητου");
				vehiclePassengesNum = resultSet.getInt("Αρ_επιβατών");
			}
			System.out.println("Option:" + i);
			System.out.println("	ID: " + vehicleID);
			System.out.println("	Brand: " + vehicleBrand);
			System.out.println("	Model: " + vehicleModel);
			System.out.println("	Color: " + vehicleColor);
			System.out.println("	Range in km: " + vehicleKM);
			System.out.println("	Cost per day: " + vehiclecost);
			System.out.println("	Insurance Cost per day: " + vehicleinsuranceCost);
			
			if(vehcleType == EVOL.CAR_STR) {
				System.out.println("	Type: " + vehicleType);
				System.out.println("	Passengers: " + vehiclePassengesNum);
			}
			
			System.out.println();
			i++;
	    }
	}
	
	
	public static void SQLquestions(Connection con, String question) throws SQLException{
		Statement stmt = con.createStatement();
		ResultSet resultSet;
		
		if(question != ASK_OPTION)resultSet = stmt.executeQuery(question);
		else {
			try (Scanner scanner = new Scanner(System.in)) {
				System.out.print("Enter your SLQ query:\n>");
				resultSet = stmt.executeQuery(scanner.nextLine());
			}
		}
		
		ResultSetMetaData metaData = resultSet.getMetaData();

		while (resultSet.next()) {
		    for (int i = 1; i <= metaData.getColumnCount(); i++) {
		        String columnName = metaData.getColumnName(i);
		        Object value = resultSet.getObject(i);
		        System.out.println(columnName + ": " + value);
		    }
		    System.out.println();
		}
		
	}
		
}
