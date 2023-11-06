package components;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

public class StateLabel extends JLabel {
	public StateLabel(String msg) {
		this.setText(msg);
		this.setForeground(new Color(0, 255, 0));
		this.setFont(new Font("맑은 고딕", Font.BOLD, 17));
	}
	
	// Parking State의 상태에 따른 글씨 색상 변경 코드
	public void setColor(String state) {
        if (state.equals("만차")) {
            this.setForeground(Color.RED); // "만차"일 때 글씨색을 빨간색으로 설정
        } else if (state.equals("혼잡")) {
            this.setForeground(Color.ORANGE); // "혼잡"일 때 글씨색을 주황색으로 설정
        } else {
            this.setForeground(new Color(0, 255, 0)); // 다른 경우는 초기 설정대로 설정
        }
    }
}
