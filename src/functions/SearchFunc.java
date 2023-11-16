package functions;

import java.time.LocalDateTime;

import javax.swing.JOptionPane;

import network.Search;

public class SearchFunc {

	public void searchCar() {
		String searchCarNum = JOptionPane.showInputDialog("검색할 차량번호를 입력하세요");
		Search search = new Search(LocalDateTime.now(), searchCarNum);

		String result = search.processOut();

		if (result.equals("Fail")) {
			JOptionPane.showMessageDialog(null, "해당 차는 존재하지 않습니다.", "검색", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, result, "검색", JOptionPane.INFORMATION_MESSAGE);
		}

	}

}
