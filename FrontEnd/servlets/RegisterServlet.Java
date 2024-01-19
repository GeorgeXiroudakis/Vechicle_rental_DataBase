package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import EVOL.EVOL; // Import your EVOL class

public class RegisterServlet extends HttpServlet {

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

        // Parse the JSON data using javax.json
        JsonObject jsonObject;
        try (JsonReader jsonReader = Json.createReader(new StringReader(data))) {
            jsonObject = jsonReader.readObject();
        }

        // Extract data from JSON
        String firstName = jsonObject.getString("firstName");
        String lastName = jsonObject.getString("lastName");
        String street = jsonObject.getString("street");
        // ...extract other fields similarly

        // Database interaction
        try {
            // Ensure your EVOL.registerClient method accepts all necessary parameters
            EVOL.registerClient(/* parameters as per your method definition */);
            // Handle successful registration
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().write("Registration successful!");
        } catch (Exception e) {
            e.printStackTrace();
            // Handle failure
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().write("Registration failed: " + e.getMessage());
        }
    }
}
