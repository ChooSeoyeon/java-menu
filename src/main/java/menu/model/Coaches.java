package menu.model;

import java.util.List;
import java.util.stream.Collectors;
import menu.model.dto.RecommendSummary;
import menu.model.enums.Category;

public class Coaches {
    private final List<Coach> coaches;

    public Coaches(List<String> coaches) {
        this.coaches = coaches.stream()
                .map(Coach::new)
                .collect(Collectors.toList());
        validateCoachesSize();
    }

    private void validateCoachesSize() {
        if (coaches.size() < 2 || coaches.size() > 5) {
            throw new IllegalArgumentException("코치는 2~5명 이내로 입력해야 합니다.");
        }
    }

    public List<String> getCoachNames() {
        return coaches.stream()
                .map(Coach::getName)
                .collect(Collectors.toList());
    }

    public void updateCoachMenusDontEat(String coachName, List<String> menusDontEat) {
        Coach coach = findCoachByName(coachName);
        coach.updateMenusDontEat(menusDontEat);
    }

    private Coach findCoachByName(String coachName) {
        return coaches.stream()
                .filter(coach -> coach.isSameName(coachName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코치입니다."));
    }

    public void recommendMenu(Recommend recommend, Category category) {
        coaches.forEach(coach -> coach.recommendMenu(recommend, category));
    }

    public List<RecommendSummary> collectRecommendSummaries() {
        return coaches.stream()
                .map(Coach::captureRecommendSummary)
                .collect(Collectors.toList());
    }
}
