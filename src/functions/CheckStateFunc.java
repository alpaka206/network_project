package functions;
import components.StateLabel;
import java.util.List;

public class CheckStateFunc {
    public static String checkFloorState(Boolean[] parkingLots) {
        int totalSpaces = parkingLots.length;
        int filledSpaces = 0;

        for (int i = 0; i < totalSpaces; i++) {
            if (parkingLots[i]) {
                filledSpaces++;
            }
        }

        double occupancyRate = (double) filledSpaces / totalSpaces;

        if (filledSpaces == totalSpaces) { 
        	return "만차";
        } else if (occupancyRate >= 0.5) {
        	return "혼잡";
        } else {
        	return "여유";
        }
    }
}
