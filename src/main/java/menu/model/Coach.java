package menu.model;

import java.util.List;
import menu.model.enums.Category;

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

    public void updateMenusDontEat(final List<String> menusDontEat) {
        this.menusDontEat = menusDontEat;
        validateMenusDontEat();
    }

    private void validateMenusDontEat() {
        validateMenusDontEatExist();
        validateMenusDontEatSize();
    }

    private void validateMenusDontEatExist() {
        if (menusDontEat.size() == 1 && menusDontEat.get(0).isEmpty()) {
            return;
        } // TODO

        menusDontEat.stream()
                .filter(menu -> !Category.isExistMenuName(menu))
                .findAny()
                .ifPresent(menu -> {
                    throw new IllegalArgumentException("존재하지 않는 메뉴입니다.");
                });
    }

    private void validateMenusDontEatSize() {
        if (menusDontEat.size() > 3) {
            throw new IllegalArgumentException("못 먹는 메뉴는 0~2개 입력해야 합니다.");
        }
    }

    public String getName() {
        return name;
    }

    public boolean isSameName(String coachName) {
        return name.equals(coachName);
    }
}
