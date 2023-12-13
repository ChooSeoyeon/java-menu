package menu.view;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputView {
    public List<String> readCoachNames() {
        System.out.println("코치의 이름을 입력해 주세요. (, 로 구분)");
        String inputCoachNames = Console.readLine();
        return convertInputCoachNamesToList(inputCoachNames);
    }

    private List<String> convertInputCoachNamesToList(String inputCoachNames) {
        List<String> carNames = Arrays.stream(inputCoachNames.split(","))
                .map(String::trim)
                .peek(this::validateCoachNameNotEmpty)
                .collect(Collectors.toList());
        validateCoachNamesNotEmpty(carNames);
        return carNames;
    }

    private void validateCoachNameNotEmpty(String coachName) {
        if (coachName.isEmpty()) {
            throw new IllegalArgumentException("코치 이름은 앞뒤 공백을 제외한 1자 이상으로 입력해야 합니다.");
        }
    }

    private void validateCoachNamesNotEmpty(List<String> coachNames) {
        if (coachNames.isEmpty()) {
            throw new IllegalArgumentException("코치 이름을 하나 이상 입력해야 합니다");
        }
    }
}
