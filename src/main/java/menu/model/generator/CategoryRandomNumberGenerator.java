package menu.model.generator;

import camp.nextstep.edu.missionutils.Randoms;

public class CategoryRandomNumberGenerator implements CategoryNumberGenerator {
    @Override
    public int generate() {
        return Randoms.pickNumberInRange(1, 5);
    }
}
