package EVOL;

import java.sql.*;
import java.util.Calendar;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class EVOL {
	static final String DatabaseName = "EVOL";
	
	static final double COST_OF_CAR_RENT_PER_DAY = 60;
	static final double COST_OF_BIKE_RENT_PER_DAY = 40;
	static final double COST_OF_BIKECYCLE_RENT_PER_DAY = 20;
	static final double COST_OF_SCOYTER_RENT_PER_DAY = 10;
	
	static final double PERSENTAGE_OF_DAYLY_COST_FOR_INSURANCE = 0.05;
	
	static final double COST_OF_CAR_INCURANCE_PER_DAY = PERSENTAGE_OF_DAYLY_COST_FOR_INSURANCE * COST_OF_CAR_RENT_PER_DAY;
	static final double COST_OF_BIKE_INCURANCE_PER_DAY = PERSENTAGE_OF_DAYLY_COST_FOR_INSURANCE * COST_OF_BIKE_RENT_PER_DAY;
	static final double COST_OF_BIKECYCLE_INCURANCE_PER_DAY = PERSENTAGE_OF_DAYLY_COST_FOR_INSURANCE * COST_OF_BIKECYCLE_RENT_PER_DAY;
	static final double COST_OF_SCOYTER_INCURANCE_PER_DAY = PERSENTAGE_OF_DAYLY_COST_FOR_INSURANCE * COST_OF_SCOYTER_RENT_PER_DAY;
	
	static final String NORMAL_RETURN_STR = "NORMAL";
	static final String ACCIDENT_RETURN_STR = "ACCIDENT";
	static final String VEHICLE_FAIL_RETURN_STR = "VEHICLE_FAIL";
	
	//what percentage of the total rent will be the penalty per hour
	static final double PERCENTAGE_FOR_PENALDY_PER_HPUR = 0.1;
	
	static final String CAR_STR = "Αυτοκίνητο";
	static final String BIKE_STR = "Μηχανή";
	static final String BIKE_CYCLE_STR = "Ποδηλατο";
	static final String SCOUTER_STR = "Πατινι";
	
	static final String RENT_PENDING = "Ενεργη";
	static final String RENT_FINALIZED = "Ολοκλρομενη";
	
	static final String YES_STR = "ΝΑΙ";
	static final String NO_STR = "ΟΧΙ";
	
	static final String NO_LISENCE = null;


	public static void main(String[] args) throws ClassNotFoundException, SQLException {
    	Connection con = connectToDataBase("EVOL");
 
    	createTables(con);
    	
    	registerClient(con, "1111111111111111", 19, 8, 2003, "Γιανικου", 32, 71201, "gkami", "koutikoy", null);
    	registerClient(con, "2222222222222222", 19, 8, 2003, "Γιανικου", 32, 71201, "COOL gkami", "koutikoy", "1232132131");
    	registerClient(con, "3333333333333333", 25, 1, 2003, "Παππαναστασιου", 182, 71409, "Γεωργιος", "Ξηρουδακης", "11111111");
    	registerClient(con, "4444444444444444", 1, 1, 1704, "καπου", 1, 77777, "Πανος", "Γεωργιτσεας", null);
    	registerClient(con, "5555555555555555", 1, 1, 1704, "καπου αλλου", 1, 77777, "COOL Πανος", "Γεωργιτσεας", "23423423423");
    	
    	registerCar(con, "1111111", "red", 200000.43, "suv", 5, "ford", "fiesta");
        registerBike(con, "2222222", "blue", 150000, "yamaha", "somestuff");
        registerBicycle(con, "3636363", "white", 10000, "bicycle1", "BMX");
        registerScouter(con, "1472583", "blue", 190000, "scouter1", "vroooom");
        
        registerCar(con, "888888888", "red", 200000.43,"suv", 5, "ford", "fiesta");
       
       registerRent(con, 1, 25, 1, 2024, 3, 25, 2, 2024, 3, YES_STR, "2222222222222222", "1111111", "φιλος της γκαμπης", "επιθετο φιλου της γκαμπης", "21412", CAR_STR);
       registerRent(con, 2, 25, 1, 2024, 3, 25, 2, 2024, 3, NO_STR, "1111111111111111", "2222222", "Γιωργος", "Ξηρουδακης", "11111", BIKE_STR);
    	
       registerReturn(con, 25, 2, 2024, 3, NORMAL_RETURN_STR, 1);
       
       
      registerBike(con, "333333", "red", 150000,"test", "test");
      registerReturn(con, 25, 2, 2024, 3, VEHICLE_FAIL_RETURN_STR, 2);
    	
    	
       //dropTables(con);
        
    	con.close();
    }
	
    
    public static Connection connectToDataBase(String DtabaseName) throws ClassNotFoundException, SQLException {
    	Class.forName("com.mysql.cj.jdbc.Driver");

        String url = "jdbc:mysql://localhost";
        String databaseName = "EVOL";
        int port = 3306;
        String username = "root";
        String password = "";
        
        return  DriverManager.getConnection(
                url + ":" + port + "/" + databaseName + "?characterEncoding=UTF-8", username, password);
    }
    
    
    public static void createTables(Connection con) throws SQLException {
    	createdRegClientTable(con);
    	createDriverTable(con);
        createdDriverInfoTable(con);
        createCarTable(con);
        createCarBrandTable(con);
        createBikeTable(con);
        createBicycleTable(con);
        createScouterTable(con);
        createRentTable(con);
        createReturnTable(con);
    }
    
    
    public static void dropTables(Connection con) throws SQLException {
    	dropTableHelper(con, "Επιστροφή");
    	dropTableHelper(con, "Ενοικίαση");
    	dropTableHelper(con, "Οδηγός");
    	dropTableHelper(con, "Αυτοκίνητο");
    	dropTableHelper(con, "Μάρκα_Οχήματος");
    	dropTableHelper(con, "Μηχανή");
    	dropTableHelper(con, "Ποδηλατο");
    	dropTableHelper(con, "Πατινι");
    	dropTableHelper(con, "Εγγεγραμμένος_πελάτης");
    	dropTableHelper(con, "Στοιχεία_οδήγησης_πελάτη");
    }
    
    
    public static void dropTableHelper(Connection con, String tableName) throws SQLException {
        try (Statement stmt = con.createStatement()) {
            String dropTableStr = "DROP TABLE IF EXISTS " + tableName;
            stmt.executeUpdate(dropTableStr);
            System.out.println("Table " + tableName + " dropped successfully");
        }
    }
    
    
    public static void createdRegClientTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createRegClientStr = "CREATE TABLE IF NOT EXISTS Εγγεγραμμένος_πελάτης " +
    	        "(Αρ_Πιστωτικής_Κάρτας VARCHAR(16) NULL PRIMARY KEY, " +
    	        "Ημέρα_Γέννησης INT, " +
    	        "Μήνας_Γέννησης INT, " +
    	        "Έτος_Γέννησης INT, " +
    	        "Οδός VARCHAR(50), " +
    	        "Αριθμός INT, " +
    	        "ΤΚ INT)";

        stmt.executeUpdate(createRegClientStr);
        System.out.println("RegClient Table created successfully");
    }
    
    
    public static void createdDriverInfoTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createdDriverInfoStr = "CREATE TABLE IF NOT EXISTS Στοιχεία_οδήγησης_πελάτη " +
    	        "(Αρ_Πιστωτικής_Κάρτας VARCHAR(16) PRIMARY KEY, " +
    	        "Όνομα VARCHAR(50), " +
    	        "Επώνυμο VARCHAR(50), " +
    	        "Άδεια_Οδήγησης VARCHAR(20))";

        stmt.executeUpdate(createdDriverInfoStr);
        System.out.println("DriverInfo Table created successfully");
    }
    
    
    public static void createRentTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createRentTableStr = "CREATE TABLE IF NOT EXISTS Ενοικίαση " +
                "(Αριθμος_Ενοικίασης INT PRIMARY KEY, " +
                "Κόστος DOUBLE, " +
                "Μέρα_ενοικίασης INT, " +
                "Μήνας_ενοικίασης INT, " +
                "Έτος_ενοικίασης INT, " +
                "Ώρα_ενοικίασης INT, " +
                "Μέρα_δηλωμένης_επιστροφής INT, " +
                "Μήνας_δηλωμένης_επιστροφής INT, " +
                "Έτος_δηλωμένης_επιστροφής INT, " +
                "Ώρα_δηλωμένης_επιστροφής INT, " +
                "Ασφάλιση VARCHAR(5), " +
                "Αρ_Πιστωτικής_Κάρτας VARCHAR(16), " +
                "Αν_Αριθμός_Οχήματος VARCHAR(20), " +
                "Ειδος_Οχηματος VARCHAR(20), " +
                "Κατασταση VARCHAR(20), " +
                "FOREIGN KEY (Αρ_Πιστωτικής_Κάρτας) REFERENCES Εγγεγραμμένος_πελάτης(Αρ_Πιστωτικής_Κάρτας))";

        stmt.executeUpdate(createRentTableStr);
        System.out.println("Rent Table created successfully");
    }
    
    
    public static void createDriverTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createDriverTableStr = "CREATE TABLE IF NOT EXISTS Οδηγός " +
    	        "(Αριθμος_Ενοικίασης INT PRIMARY KEY , " +
    	        "Όνομα_Οδηγού VARCHAR(50), " +
    	        "Επώνυμο_Οδηγού VARCHAR(50), " +
    	        "Άδεια_Οδήγησης_Οδηγού VARCHAR(20))";

        stmt.executeUpdate(createDriverTableStr);
        System.out.println("Driver Table created successfully");
    }
    
    
    public static void createCarTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	 String createcarTableStr = "CREATE TABLE IF NOT EXISTS Αυτοκίνητο " +
    	            "(Αναγνωριστικός_αριθμός_οχήματος INT PRIMARY KEY , " +
    	            "Χρώμα VARCHAR(50), " +
    	            "Αυτονομία_σε_χιλιόμετρα DOUBLE, " +
    	            "Κόστος_ενοικίασης DOUBLE, " +
    	            "Κόστος_Ασφάλισης DOUBLE, " +
    	            "Βλάβη VARCHAR(5), " +
    	            "Κόστος_Επιδιόρθωσης DOUBLE, " +
    	            "Είδος_Επιδιόρθωσης VARCHAR(50), " +
    	            "Τύπος_Αυτοκινητου VARCHAR(50), " +
    	            "Αρ_επιβατών INT, " +
    	            "Ενοικιασμενο VARCHAR(10), " +
    	            "Αρ_Πιστωτικής_Κάρτας VARCHAR(16) NULL, " +
    	            "FOREIGN KEY (Αρ_Πιστωτικής_Κάρτας) REFERENCES Εγγεγραμμένος_πελάτης(Αρ_Πιστωτικής_Κάρτας))";

        stmt.executeUpdate(createcarTableStr);
        System.out.println("Car Table created successfully");
    }
    
    
    public static void createCarBrandTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createCarBrandTableStr = "CREATE TABLE IF NOT EXISTS Μάρκα_Οχήματος " +
    	        "(Αναγνωριστικός_αριθμός_οχήματος INT PRIMARY KEY , " +
    	        "Μάρκα VARCHAR(50), " +
    	        "Μοντέλο VARCHAR(50))";

        stmt.executeUpdate(createCarBrandTableStr);
        System.out.println("CarBrand Table created successfully");
    }
    
    
    public static void createBikeTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createBikeTableStr = "CREATE TABLE IF NOT EXISTS Μηχανή " +
    	        "(Αναγνωριστικός_αριθμός_οχήματος INT PRIMARY KEY , " +
    	        "Χρώμα VARCHAR(50), " +
    	        "Αυτονομία_σε_χιλιόμετρα DOUBLE, " +
    	        "Κόστος_ενοικίασης DOUBLE, " +
    	        "Κόστος_Ασφάλισης DOUBLE, " +
    	        "Βλάβη VARCHAR(5), " +
    	        "Κόστος_Επιδιόρθωσης DOUBLE, " +
    	        "Είδος_Επιδιόρθωσης VARCHAR(30), " +
    	        "Ενοικιασμενο VARCHAR(10), " +
    	        "Αρ_Πιστωτικής_Κάρτας VARCHAR(16), " +
	            "FOREIGN KEY (Αρ_Πιστωτικής_Κάρτας) REFERENCES Εγγεγραμμένος_πελάτης(Αρ_Πιστωτικής_Κάρτας))";

        stmt.executeUpdate(createBikeTableStr);
        System.out.println("Bike Table created successfully");
    }
    
    
    public static void createBicycleTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createBicycleTableStr = "CREATE TABLE IF NOT EXISTS Ποδηλατο " +
    	        "(Αναγνωριστικός_αριθμός_οχήματος INT PRIMARY KEY , " +
    	        "Χρώμα VARCHAR(50), " +
    	        "Αυτονομία_σε_χιλιόμετρα DOUBLE, " +
    	        "Κόστος_ενοικίασης DOUBLE, " +
    	        "Κόστος_Ασφάλισης DOUBLE, " +
    	        "Βλάβη VARCHAR(50), " +
    	        "Κόστος_Επιδιόρθωσης DOUBLE, " +
    	        "Είδος_Επιδιόρθωσης VARCHAR(30), " +
    	        "Ενοικιασμενο VARCHAR(10), " +
    	        "Αρ_Πιστωτικής_Κάρτας VARCHAR(16), " +
    	        "FOREIGN KEY (Αρ_Πιστωτικής_Κάρτας) REFERENCES Εγγεγραμμένος_πελάτης(Αρ_Πιστωτικής_Κάρτας))";

        stmt.executeUpdate(createBicycleTableStr);
        System.out.println("Bicycle Table created successfully");
    }
    
    
    public static void createScouterTable(Connection con) throws SQLException{
    	Statement stmt = con.createStatement();
    	
    	String createScouterTableStr = "CREATE TABLE IF NOT EXISTS Πατινι " +
    	        "(Αναγνωριστικός_αριθμός_οχήματος INT PRIMARY KEY , " +
    	        "Χρώμα VARCHAR(50), " +
    	        "Αυτονομία_σε_χιλιόμετρα DOUBLE, " +
    	        "Κόστος_ενοικίασης DOUBLE, " +
    	        "Κόστος_Ασφάλισης DOUBLE, " +
    	        "Βλάβη VARCHAR(30), " +
    	        "Κόστος_Επιδιόρθωσης DOUBLE, " +
    	        "Είδος_Επιδιόρθωσης VARCHAR(50), " +
    	        "Ενοικιασμενο VARCHAR(10), " +
    	        "Αρ_Πιστωτικής_Κάρτας VARCHAR(16), " +
    	        "FOREIGN KEY (Αρ_Πιστωτικής_Κάρτας) REFERENCES Εγγεγραμμένος_πελάτης(Αρ_Πιστωτικής_Κάρτας))";

        stmt.executeUpdate(createScouterTableStr);
        System.out.println("Scouter Table created successfully");
    }
    
    public static void createReturnTable(Connection con) throws SQLException {
        Statement stmt = con.createStatement();

        String createReturnTableStr = "CREATE TABLE IF NOT EXISTS Επιστροφή " +
                "(Ημέρα_επιστροφής INT, " +
                "Μήνας_Επιστροφής INT, " +
                "Έτος_Επιστροφής INT, " +
                "Ώρα_Επιστροφής INT, " +
                "Λόγος_Επιστροφής VARCHAR(30), " +
                "Επιπλέον_Κόστος DOUBLE, " +
                "Αρ_Ενοικίασης INT, " +
                "FOREIGN KEY (Αρ_Ενοικίασης) REFERENCES Ενοικίαση(Αριθμος_Ενοικίασης))";

        stmt.executeUpdate(createReturnTableStr);
        System.out.println("Return Table created successfully");
    }
    
    
    public static void registerClient(Connection con, String CardNUm, int day, int month, int year, String address, int num, int pk, String Fname, String Lname, String liesenceNum) throws SQLException {
    	Statement stmt = con.createStatement();
    	if(!(day >= 1 && day <= 31 && month >= 1 && month <= 12 && year <= Calendar.getInstance().get(Calendar.YEAR) - 16 ) ) {System.err.println("invalid date of birth in clinet registration!!!!"); return;}
    	
    	String regString = "INSERT INTO Εγγεγραμμένος_πελάτης VALUES ('" + 
    	        CardNUm + "', " + 
    	        day + ", " + 
    	        month + ", " + 
    	        year + ", '" + 
    	        address + "', " + 
    	        num + ", " + 
    	        pk + 
    	        ")";
    	 stmt.executeUpdate(regString);
    	 
    	 String driverString;
    	 if (liesenceNum != null) {
    		  driverString = "INSERT INTO Στοιχεία_οδήγησης_πελάτη (Αρ_Πιστωτικής_Κάρτας, Όνομα, Επώνυμο, Άδεια_Οδήγησης) VALUES ('" +
     		        CardNUm + "', '" +
     		        Fname + "', '" +
     		        Lname + "', '" +
     		        liesenceNum + "')";
    	 }else {
    		  driverString = "INSERT INTO Στοιχεία_οδήγησης_πελάτη (Αρ_Πιστωτικής_Κάρτας, Όνομα, Επώνυμο, Άδεια_Οδήγησης) VALUES ('" +
      		        CardNUm + "', '" +
      		        Fname + "', '" +
      		        Lname + "', NULL)";
    	 }
    	 stmt.executeUpdate(driverString);
    	 
         System.out.println("client with card Number:"  + CardNUm + " registered successfully");
    	
    }
    
    public static void registerCar(Connection con, String RegNUm, String color, double km, 
    		 String type, int passengers, String brand, String model) throws SQLException {
    	
    	Statement stmt = con.createStatement();
    	
    	String CarStr = "INSERT INTO Αυτοκίνητο VALUES ('" +
    	        RegNUm + "', '" +
    	        color + "', " +
    	        km + ", " +
    	        COST_OF_CAR_RENT_PER_DAY + ", " +
    	        COST_OF_CAR_INCURANCE_PER_DAY + ", " +
    	        "NULL" + ", " +
    	        "NULL" + ", " +
    	        "NULL" + ", '" +
    	        type + "', " +
    	        passengers + ", '" +
    	        NO_STR + "' ," +
    	        " NULL)";
    	
    	 stmt.executeUpdate(CarStr);
    	 
    	 String brandStr = "INSERT INTO Μάρκα_Οχήματος VALUES (" +
    			 	RegNUm + ", '" +
    	            brand + "', '" +
    	            model + "')";
    	 stmt.executeUpdate(brandStr);
    	 
         System.out.println("car with registration Number:"  + RegNUm + " registered successfully");
    	
    }
    
    public static void registerBike(Connection con, String RegNUm, String color, double km,
    		String brand, String model) throws SQLException {
    	
    	Statement stmt = con.createStatement();
    	
    	String BikeStr = "INSERT INTO Μηχανή VALUES (" +
                RegNUm + ", '" +
                color + "', " +
                km + ", " +
                COST_OF_BIKE_RENT_PER_DAY + ", " +
                COST_OF_BIKE_INCURANCE_PER_DAY + ", " +
                "NULL" + ", " +
                "NULL" + ", " +
                "NULL" + ", '" +
                NO_STR + "' ," +
    	        " NULL)";
    	
    	 stmt.executeUpdate(BikeStr);
    	 
    	 String brandStr = "INSERT INTO Μάρκα_Οχήματος VALUES (" +
    			 	RegNUm + ", '" +
    	            brand + "', '" +
    	            model + "')";
    	 stmt.executeUpdate(brandStr);
    	 
         System.out.println("bike with registration Number:"  + RegNUm + " registered successfully");
    	
    }
    
    public static void registerBicycle(Connection con, String RegNUm, String color, double km,
    		String brand, String model) throws SQLException {
    	
    	Statement stmt = con.createStatement();
    	
    	String BicycleStr = "INSERT INTO Ποδηλατο VALUES (" +
                RegNUm + ", '" +
                color + "', " +
                km + ", " +
                COST_OF_BIKECYCLE_RENT_PER_DAY + ", " +
                COST_OF_BIKECYCLE_INCURANCE_PER_DAY + ", " +
                "NULL" + ", " +
                "NULL" + ", " +
                "NULL" + ", '" +
                NO_STR + "' ," +
    	        " NULL)";
    	
    	 stmt.executeUpdate(BicycleStr);
    	 
    	 String brandStr = "INSERT INTO Μάρκα_Οχήματος VALUES (" +
    			 	RegNUm + ", '" +
    	            brand + "', '" +
    	            model + "')";
    	 stmt.executeUpdate(brandStr);
    	 
         System.out.println("bicycle with registration Number:"  + RegNUm + " registered successfully");
    	
    }
    
    public static void registerScouter(Connection con, String RegNUm, String color, double km,
    		String brand, String model) throws SQLException {
    	
    	Statement stmt = con.createStatement();
    	
    	String ScouterStr = "INSERT INTO Πατινι VALUES (" +
                RegNUm + ", '" +
                color + "', " +
                km + ", " +
                COST_OF_SCOYTER_RENT_PER_DAY + ", " +
                COST_OF_SCOYTER_INCURANCE_PER_DAY + ", " +
                "NULL" + ", " +
                "NULL" + ", " +
                "NULL" + ", '" +
                NO_STR + "' ," +
    	        " NULL)";
    	
    	 stmt.executeUpdate(ScouterStr);
    	 
    	 String brandStr = "INSERT INTO Μάρκα_Οχήματος VALUES (" +
    			 	RegNUm + ", '" +
    	            brand + "', '" +
    	            model + "')";
    	 stmt.executeUpdate(brandStr);
    	 
         System.out.println("scouter with registration Number:"  + RegNUm + " registered successfully");
    	
    }
    
    
    public static void registerRent(Connection con, int RentNum,  int rentDay, int rentMonth, int rentYear, int rentTime,
    		int returnDay, int returnMonth, int returnYear, int returnTime, String insurance, String CardNum, String CarNum, String driverFName, String driverLName, String lisenceNum, String vechicleType) throws SQLException {
    	
    	//if the driver has no license he is now 18 so he can no rend car or bike
    	if(lisenceNum == null && (vechicleType == CAR_STR || vechicleType == BIKE_STR)) {
    		System.err.println("The driver you are registering doesn't have a license so he cant rent cars and motorsycles");
    		return;
    	}
    	
    	
    	
    	double cost = calculateCost(con, CarNum, rentDay, rentMonth, rentYear, returnDay, returnMonth, returnYear, insurance == "YES" ? true : false);
    	
    	Statement stmt = con.createStatement();
    	
    	String RentStr= "INSERT INTO Ενοικίαση VALUES (" +
                RentNum + ", " +
                cost + ", " +
                rentDay + ", " +
                rentMonth + ", " +
                rentYear + ", " +
                rentTime + ", " +
                returnDay + ", " +
                returnMonth + ", " +
                returnYear + ", " +
                returnTime + ", '" +
                insurance + "', '" +
                CardNum + "', '" +
                CarNum + "', '" +
                vechicleType + "', '" +
                RENT_PENDING + "')";
    	
    	 stmt.executeUpdate(RentStr);
    	 
    	 String driverString = "INSERT INTO Οδηγός (Αριθμος_Ενοικίασης, Όνομα_Οδηγού, Επώνυμο_Οδηγού, Άδεια_Οδήγησης_Οδηγού) VALUES (" +
    	            RentNum + ", '" +
    	            driverFName + "', '" +
    	            driverLName + "', '" +
    	            lisenceNum + "')";
 	 stmt.executeUpdate(driverString);
 	 
 	 //uptate the cad number in the vehicle
 	String updateQuery = "UPDATE "+ vechicleType +" SET Αρ_Πιστωτικής_Κάρτας = "+ CardNum +" , Ενοικιασμενο = '"+YES_STR+"' " +"WHERE Αναγνωριστικός_αριθμός_οχήματος = " +CarNum;
    int rowsUpdated = stmt.executeUpdate(updateQuery);

    if (rowsUpdated > 0) {
        System.out.println("Credit card number updated successfully.");
    } else {
        System.err.println("Vehicle ID not found to uptate the forain key in vechicle table");
    }
 	 
    	 
         System.out.println("rent with rent Number:"  + RentNum + " registered successfully");
    	
    }
    
    public static double calculateCost(Connection con, String CarNum, int rentDay, int rentMonth, int rentYear, int returnDay, int returnMonth, int returnYear, boolean insurance) throws SQLException {
    	LocalDate startDate = LocalDate.of(rentYear, rentMonth, rentDay);
        LocalDate endDate = LocalDate.of(returnYear, returnMonth, returnDay);
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        
        double carCost = 0.0;
        double insuranceCost = 0.0;

        
        String selectQuery = "SELECT Κόστος_ενοικίασης FROM Αυτοκίνητο WHERE Αναγνωριστικός_αριθμός_οχήματος = ? " +
                "UNION " +
                "SELECT Κόστος_ενοικίασης FROM Μηχανή WHERE Αναγνωριστικός_αριθμός_οχήματος = ? " +
                "UNION " +
                "SELECT Κόστος_ενοικίασης FROM Ποδηλατο WHERE Αναγνωριστικός_αριθμός_οχήματος = ? " +
                "UNION " +
                "SELECT Κόστος_ενοικίασης FROM Πατινι WHERE Αναγνωριστικός_αριθμός_οχήματος = ?";
        
        PreparedStatement preparedStatement = con.prepareStatement(selectQuery);
        preparedStatement.setString(1, CarNum);
        preparedStatement.setString(2, CarNum);
        preparedStatement.setString(3, CarNum);
        preparedStatement.setString(4, CarNum);

        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            carCost = resultSet.getDouble("Κόστος_ενοικίασης");
        }
        
        if(insurance) {
	         selectQuery = "SELECT Κόστος_Ασφάλισης FROM Αυτοκίνητο WHERE Αναγνωριστικός_αριθμός_οχήματος = ? " +
	                "UNION " +
	                "SELECT Κόστος_Ασφάλισης FROM Μηχανή WHERE Αναγνωριστικός_αριθμός_οχήματος = ? " +
	                "UNION " +
	                "SELECT Κόστος_Ασφάλισης FROM Ποδηλατο WHERE Αναγνωριστικός_αριθμός_οχήματος = ? " +
	                "UNION " +
	                "SELECT Κόστος_Ασφάλισης FROM Πατινι WHERE Αναγνωριστικός_αριθμός_οχήματος = ?";
	        
	         preparedStatement = con.prepareStatement(selectQuery);
	        preparedStatement.setString(1, CarNum);
	        preparedStatement.setString(2, CarNum);
	        preparedStatement.setString(3, CarNum);
	        preparedStatement.setString(4, CarNum);
	
	         resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            insuranceCost = resultSet.getDouble("Κόστος_Ασφάλισης");
	        }
        }
  
                   
        return daysBetween * (carCost + insuranceCost);
       
    }
    
    
    public static void registerReturn(Connection con, int returnDay, int returnMonth, int returnYear, int returnHour, String reason, int rendNum) throws SQLException {
    	int ProperreturnDay;
        int ProperreturnMonth;
        int ProperreturnYear;
        int ProperreturnHour;
        double originalRentCost;
        
        double extraCost = 0;
        
        String vehicleType = "";
        String vehicleNumber = "";
        String insurance = "";
        String clientCardNum = "";
        
    	Statement stmt = con.createStatement();
    	
    	
    	LocalDate returnDate = LocalDate.of(returnYear, returnMonth, returnDay);
    	
    	//find when he should have return it
    	 String selectQuery = "SELECT Μέρα_δηλωμένης_επιστροφής, Μήνας_δηλωμένης_επιστροφής, " +
                 "Έτος_δηλωμένης_επιστροφής, Ώρα_δηλωμένης_επιστροφής, Κόστος " +
                 "FROM Ενοικίαση WHERE Αριθμος_Ενοικίασης = " + rendNum;

    	 ResultSet resultSet = stmt.executeQuery(selectQuery);
    	 if (resultSet.next()) {
    	         ProperreturnDay = resultSet.getInt("Μέρα_δηλωμένης_επιστροφής");
    	         ProperreturnMonth = resultSet.getInt("Μήνας_δηλωμένης_επιστροφής");
    	         ProperreturnYear = resultSet.getInt("Έτος_δηλωμένης_επιστροφής");
    	         ProperreturnHour = resultSet.getInt("Ώρα_δηλωμένης_επιστροφής");
    	         originalRentCost = resultSet.getDouble("Κόστος");
    	    } else {
    	        System.err.println("Rent with ID " + rendNum + " not found.");
    	        return;
    	    }
    	 
        LocalDate properDate = LocalDate.of(ProperreturnYear, ProperreturnMonth, ProperreturnDay);
        
        long extraDays = ChronoUnit.DAYS.between(properDate, returnDate);
        
        //calculate extra cost
        if(extraDays >= 0) {
        	//check days
        	extraCost += 24*extraDays * (PERCENTAGE_FOR_PENALDY_PER_HPUR * originalRentCost);
        	
        	//check the hours
        	int extraHours = returnHour - ProperreturnHour;
        	if(extraHours > 0)extraCost += extraHours * (PERCENTAGE_FOR_PENALDY_PER_HPUR * originalRentCost);
        }
        
        selectQuery = "SELECT Ειδος_Οχηματος, Αν_Αριθμός_Οχήματος, Ασφάλιση, Αρ_Πιστωτικής_Κάρτας FROM Ενοικίαση WHERE Αριθμος_Ενοικίασης = " + rendNum;
	    resultSet = stmt.executeQuery(selectQuery);

	    //find vehicle characteristics
	    while (resultSet.next()) {
	        vehicleType = resultSet.getString("Ειδος_Οχηματος");
	        vehicleNumber = resultSet.getString("Αν_Αριθμός_Οχήματος");
	        insurance = resultSet.getString("Ασφάλιση");
	        clientCardNum = resultSet.getString("Αρ_Πιστωτικής_Κάρτας");
	    }
	    
	    //makrk the car a non rented
	    String updateStr;
	    updateStr = "UPDATE "+vehicleType+" SET Ενοικιασμενο = '" + NO_STR + "', Αρ_Πιστωτικής_Κάρτας = NULL WHERE Αναγνωριστικός_αριθμός_οχήματος = " + "'" +vehicleNumber+ "'";

        stmt.executeUpdate(updateStr);
        
        //check reson of return
        if(reason != NORMAL_RETURN_STR){
        	   	     
        	updateStr = "UPDATE " + vehicleType + " SET Βλάβη = '" + "ΝΑΙ" + "' WHERE Αναγνωριστικός_αριθμός_οχήματος = " + vehicleNumber;

            stmt.executeUpdate(updateStr);
            System.out.println("Broken value updated successfully for vehicle ID: " + vehicleNumber + " in table: " + vehicleType);
            
            if(reason == ACCIDENT_RETURN_STR) {
            	if(insurance.compareTo(NO_STR) == 0) {
            		extraCost += 3 * originalRentCost;
            	}
            }  
            
            if(reason == VEHICLE_FAIL_RETURN_STR || (reason == ACCIDENT_RETURN_STR && insurance == YES_STR)){
            	String replacementID = null;
            	
            	if(!(vehicleType.compareTo(CAR_STR) == 0)) {
            		selectQuery = "SELECT Αναγνωριστικός_αριθμός_οχήματος FROM "+ vehicleType +" WHERE Βλάβη IS NULL AND Ενοικιασμενο = '" + NO_STR + "'";                      
            		resultSet = stmt.executeQuery(selectQuery);

            	    while (resultSet.next()) {
            	        replacementID = resultSet.getString("Αναγνωριστικός_αριθμός_οχήματος");
            	        System.out.println("Your " + vehicleType+ " is being replaced with the " + vehicleType + " with id = " + replacementID);
            	    }
            	}else {
            		ResultSet resultSet2;
            		String ogCarType = null;
            		int ogNumOfPassengers = 0;
            		
            		//get the car type pasengers#
            		selectQuery = "SELECT Τύπος_Αυτοκινητου, Αρ_επιβατών FROM "+ vehicleType +" WHERE Αναγνωριστικός_αριθμός_οχήματος = " + "'" +vehicleNumber+ "'";                      
            		resultSet2 = stmt.executeQuery(selectQuery);

            	    while (resultSet2.next()) {
            	    	ogCarType = resultSet2.getString("Τύπος_Αυτοκινητου");
            	    	ogNumOfPassengers = resultSet2.getInt("Αρ_επιβατών");
            	    }
            	    
            	    //find suitable replacment
            		selectQuery = "SELECT Αναγνωριστικός_αριθμός_οχήματος FROM "+ vehicleType +" WHERE Βλάβη IS NULL AND Ενοικιασμενο = '" + NO_STR + "' AND Τύπος_Αυτοκινητου = '" +ogCarType+ "' AND Αρ_επιβατών = " + ogNumOfPassengers;                      
            		resultSet2 = stmt.executeQuery(selectQuery);

            	    while (resultSet2.next()) {
            	        replacementID = resultSet2.getString("Αναγνωριστικός_αριθμός_οχήματος");
            	        System.out.println("Your " + vehicleType+ " is being replaced with the " + vehicleType + " with id = " + replacementID);
            	    }
            	}
            	
            	if(replacementID == null){
            		System.out.println("No suitable vehicle available for replacement of vehicle:" +vehicleNumber+". Dont charge the client for anything sent him to his marry way."); 
            		updateStr = "UPDATE Ενοικίαση SET Κατασταση = '" + RENT_FINALIZED + "' WHERE Αριθμος_Ενοικίασης = " + rendNum;

                    stmt.executeUpdate(updateStr);
                    System.out.println("Rend: " + rendNum + " finalized as we have no suitable vehicle to replace the broken one");
                  //create new return entry
            		creteNewReturnEntry( returnDay,  returnMonth,  returnYear,  returnHour,  reason,  extraCost,  rendNum,  stmt);
                    return;
            	}
            	
            	//Update rent table with replacement
            	updateStr = "UPDATE Ενοικίαση SET Αν_Αριθμός_Οχήματος = '" + replacementID + "' WHERE Αριθμος_Ενοικίασης = " + rendNum;
                stmt.executeUpdate(updateStr);
                
                //update table of replacement vehicle
                updateStr = "UPDATE "+vehicleType+" SET Ενοικιασμενο = '" + YES_STR + "' , Αρ_Πιστωτικής_Κάρτας = '"+clientCardNum+"' WHERE Αναγνωριστικός_αριθμός_οχήματος = " + replacementID;

                stmt.executeUpdate(updateStr);
            	
              ///create new return entry
        		creteNewReturnEntry( returnDay,  returnMonth,  returnYear,  returnHour,  reason,  extraCost,  rendNum,  stmt);
        	    
            	return;
            }
        }
		
		if(reason == NORMAL_RETURN_STR) {
			updateStr = "UPDATE Ενοικίαση SET Κατασταση = '" + RENT_FINALIZED + "' WHERE Αριθμος_Ενοικίασης = " + rendNum;

            stmt.executeUpdate(updateStr);
            System.out.println("Rend: " + rendNum + " finalized");
            
            //set that the vehicle is not rented anymore
            System.out.println(vehicleType);
            updateStr = "UPDATE "+vehicleType+" SET Ενοικιασμενο = '" + NO_STR + "' WHERE Αναγνωριστικός_αριθμός_οχήματος = " + "'" +vehicleNumber+ "'";

            stmt.executeUpdate(updateStr);
            
		}
		
		
		if( !(reason == VEHICLE_FAIL_RETURN_STR || (reason == ACCIDENT_RETURN_STR && insurance == YES_STR)) ) {
        	System.out.println("The rent:" +rendNum+ " was finalized and the cosumer has to pay: normal rent cost = " + originalRentCost + "€ and extra cost of " + extraCost + "€ Final price: " +  (originalRentCost + extraCost) + "€");
        }
		
		//create new return entry
		creteNewReturnEntry( returnDay,  returnMonth,  returnYear,  returnHour,  reason,  extraCost,  rendNum,  stmt);
       
    	
    }
    
    
    public static void creteNewReturnEntry(int returnDay, int returnMonth, int returnYear, int returnHour, String reason, double extraCost, int rendNum, Statement stmt) throws SQLException {
    	//create new return entry
		String insertReturnStr = "INSERT INTO Επιστροφή VALUES (" +
	            returnDay + ", " +
	            returnMonth + ", " +
	            returnYear + ", " +
	            returnHour + ", '" +
	            reason + "', " +
	            extraCost + ", " +
	            rendNum + ")";

	    stmt.executeUpdate(insertReturnStr);
	    System.out.println("incested entry into return table regarding the rent: " + rendNum + " with reason: " + reason);
    }
}
