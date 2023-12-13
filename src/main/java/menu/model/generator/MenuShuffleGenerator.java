package menu.model.generator;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;

public class MenuShuffleGenerator {
    public List<String> generate(List<String> menuNames) {
        return Randoms.shuffle(menuNames);
    }
}
