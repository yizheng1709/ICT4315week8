import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ParkingRequestTest {

    @Test
    public void testToJson() {
        // Create a sample ParkingRequest object
        ParkingRequest request = new ParkingRequest("COMMAND_NAME", properties);
        
        // Convert the object to JSON string
        String json = request.toJson();
        
        // Validate the JSON string
        String expectedJson = "{\"commandName\":\"COMMAND_NAME\",\"properties\":{...}}";
        assertEquals(expectedJson, json);
    }
}
