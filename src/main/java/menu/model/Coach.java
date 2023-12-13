package menu.model;

import java.util.ArrayList;
import java.util.List;
import menu.model.dto.RecommendMenuSummary;
import menu.model.enums.Category;

public class Coach {
    private final String name;
    private final List<String> recommendedMenus;
    private List<String> menusDontEat;

    public Coach(final String name) {
        this.name = name;
        validateName();
        recommendedMenus = new ArrayList<>();
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
        }

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

    public void recommendMenu(Recommend recommend, Category category) {
        try {
            String recommendedMenu = recommend.recommendMenuByCategory(category);
            validateRecommendedMenu(recommendedMenu);
            recommendedMenus.add(recommendedMenu);
        } catch (IllegalStateException e) {
            recommendMenu(recommend, category);
        }
    }

    private void validateRecommendedMenu(String recommendedMenu) {
        validateRecommendedMenuDontEat(recommendedMenu);
        validateRecommendedMenuDuplicate(recommendedMenu);
    }

    private void validateRecommendedMenuDontEat(String recommendedMenu) {
        if (menusDontEat.contains(recommendedMenu)) {
            throw new IllegalStateException("못 먹는 메뉴는 추천할 수 없습니다.");
        }
    }

    private void validateRecommendedMenuDuplicate(String recommendedMenu) {
        if (recommendedMenus.contains(recommendedMenu)) {
            throw new IllegalStateException("이미 추천된 메뉴입니다.");
        }
    }

    public RecommendMenuSummary captureRecommendSummary() {
        return new RecommendMenuSummary(name, recommendedMenus);
    }
}
