package servlets;

package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.BufferedReader;
import java.sql.Connection;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import EVOL.EVOL; // Import your EVOL class

import static EVOL.EVOL.connectToDataBase;


public class ReturnServlet extends HttpServlet{

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            StringBuilder buffer = new StringBuilder();
            String line;
            try (BufferedReader reader = request.getReader()) {
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
            }

            String data = buffer.toString();

            // Parse the JSON data using Gson
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(data, JsonObject.class);

            // Extract data from JSON
            int rentNum = jsonObject.get("RentNum").getAsInt();
            int returnDay = jsonObject.get("returnDay").getAsInt();
            int returnMonth = jsonObject.get("returnMonth").getAsInt();
            int returnYear = jsonObject.get("returnYear").getAsInt();
            int returnTime = jsonObject.get("returnTime").getAsInt();
            String CardNum = jsonObject.get("CardNum").getAsString();
            String driverFName = jsonObject.get("driverFName").getAsString();

            // Database interaction
            try {
                // Call your EVOL.registerRent method with the extracted data
                Connection conection = EVOL.Connection connectToDataBase(string url, string user, string password);
                EVOL.registerReturn(conection, rentNum, returnDay,
                        returnMonth, returnYear, returnTime,
                        CardNum, driverFName);
                // Handle successful registration
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write("Return successful!");
            } catch (Exception e) {
                e.printStackTrace();
                // Handle failure
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                response.getWriter().write("Return failed: " + e.getMessage());
            }
        }
}
