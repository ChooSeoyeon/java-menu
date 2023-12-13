package menu.model;

import java.util.List;

public class Coach {
    private final String name;
    private List<String> menusDontEat;

    public Coach(final String name) {
        this.name = name;
        validateName();
    }

    public void validateName() {
        if (name.length() < 2 || name.length() > 4) {
            throw new IllegalArgumentException("코치 이름은 2~4자 이내로 입력해야 합니다.");
        }
    }

    public void setMenusDontEat(final List<String> menusDontEat) {
        this.menusDontEat = menusDontEat;
        validateMenusDontEat();
    }

    public void validateMenusDontEat() {
        if (menusDontEat.size() > 3) {
            throw new IllegalArgumentException("못 먹는 메뉴는 0~2개 입력해야 합니다.");
        }
    }
}
