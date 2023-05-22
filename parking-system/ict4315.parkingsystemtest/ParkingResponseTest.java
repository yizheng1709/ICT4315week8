import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParkingResponseTest {

    @Test
    public void testCreateAndPrintParkingResponse() {
        // Create a sample ParkingResponse object
        int statusCode = 200;
        String message = "Success";
        ParkingResponse response = new ParkingResponse(statusCode, message);

        // Print the ParkingResponse object
        System.out.println(response);

        // Validate the fields of the ParkingResponse object
        assertEquals(statusCode, response.getStatusCode());
        assertEquals(message, response.getMessage());
    }
}
