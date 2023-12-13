package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;

public class InputView {
    public List<String> readNames() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        String inputNames = Console.readLine();
        return parseInputNamesToStringList(inputNames);
    }

    private List<String> parseInputNamesToStringList(String inputNames) {
        try {
            return List.of(inputNames.split(","));
        } catch (Exception e) {
            throw new IllegalArgumentException("코치 이름은 쉼표로 구분해서 입력해야 합니다.");
        }
    }
}
