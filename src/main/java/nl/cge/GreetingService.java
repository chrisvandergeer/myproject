package nl.cge;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalTime;

@ApplicationScoped
public class GreetingService {

    public GreetingResponse greet(String name) {
        String partOfDay = calculatePartOfDay();
        String message = "Hello " + name + ", een hele goede " + partOfDay;
        return new GreetingResponse(message);
    }

    private String calculatePartOfDay() {
        int hour = LocalTime.now().getHour();
        if (hour < 12) {
            return "ochtend";
        }
        if (hour < 18) {
            return "middag";
        }
        return "avond";
    }
}
