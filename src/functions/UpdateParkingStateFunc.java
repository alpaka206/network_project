package functions;
import javax.swing.JLabel;

import components.StateLabel;

import java.util.List;

public class UpdateParkingStateFunc {
        public void updateParkingStateLabels(List<StateLabel> stateList, Boolean[][] parkingLots) {
        for (int floor = 1; floor < parkingLots.length; floor++) {
            String state = CheckStateFunc.checkFloorState(parkingLots[floor]);
            StateLabel stateLabel = stateList.get(floor - 1);
            stateLabel.setText("B" + (floor) + ": " + state);
            stateLabel.setColor(state);
        }
    }
}