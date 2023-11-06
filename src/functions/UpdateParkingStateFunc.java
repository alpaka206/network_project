package functions;
import javax.swing.JLabel;

import components.StateLabel;

import java.util.List;

public class UpdateParkingStateFunc {
    private List<JLabel> stateList;

    public UpdateParkingStateFunc(List<JLabel> stateList) {
        this.stateList = stateList;
    }

    public void updateParkingStateLabels(Boolean[][] parkingLots) {
        for (int floor = 1; floor < parkingLots.length; floor++) {
            String state = CheckStateFunc.checkFloorState(parkingLots[floor]);
            JLabel stateLabel = stateList.get(floor-1);
            stateLabel.setText("B" + (floor) + ": " + state);
            
            ((StateLabel) stateLabel).setColor(state);
        }
    }
}
