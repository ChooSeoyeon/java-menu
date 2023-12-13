package menu.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.model.dto.RecommendCategorySummary;
import menu.model.dto.RecommendMenuSummary;
import menu.model.enums.Day;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printRecommendStartAnnounce() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printRecommendSummary(List<RecommendMenuSummary> recommendSummaries,
                                      List<RecommendCategorySummary> recommendCategorySummaries) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | " + formatDayList() + " ]");
        System.out.println("[ 카테고리 | " + formatCategoryList(recommendCategorySummaries) + " ]");
        for (RecommendMenuSummary recommendMenuSummary : recommendSummaries) {
            String menusString = String.join(" | ", recommendMenuSummary.getMenus());
            System.out.println("[ " + recommendMenuSummary.getCoachName() + " | " + menusString + " ]");
        }
        System.out.println("\n추천을 완료했습니다.");
    }

    public String formatDayList() {
        return Arrays.stream(Day.values())
                .map(Day::getDay)
                .collect(Collectors.joining(" | "));
    }

    public String formatCategoryList(List<RecommendCategorySummary> recommendCategorySummaries) {
        return recommendCategorySummaries.stream()
                .map(RecommendCategorySummary::getCategory)
                .collect(Collectors.joining(" | "));
    }
}
