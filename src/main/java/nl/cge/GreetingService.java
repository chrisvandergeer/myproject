package nl.cge;

import jakarta.enterprise.context.ApplicationScoped;
import java.time.LocalTime;

@ApplicationScoped
public class GreetingService {

    public GreetingResponse greet(String name) {
        if (name == null || !name.matches("[A-Za-z\\- ]{2,}")) {
            throw new IllegalArgumentException(
                "Name must contain at least 2 characters and may only consist of letters, spaces and '-'"
            );
        }
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
