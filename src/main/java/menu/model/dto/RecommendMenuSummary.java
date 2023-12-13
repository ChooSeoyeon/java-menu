package menu.model.dto;

import java.util.List;

public class RecommendMenuSummary {
    private final String coachName;
    private final List<String> menus;

    public RecommendMenuSummary(String coachName, List<String> menus) {
        this.coachName = coachName;
        this.menus = menus;
    }

    public String getCoachName() {
        return coachName;
    }

    public List<String> getMenus() {
        return menus;
    }
}
