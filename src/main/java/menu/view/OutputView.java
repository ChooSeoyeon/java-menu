package menu.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import menu.model.dto.RecommendSummary;
import menu.model.enums.Category;
import menu.model.enums.Day;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printRecommendStartAnnounce() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printRecommendSummary(List<RecommendSummary> recommendSummaries) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | " + formatDayList() + " ]");
        System.out.println("[ 카테고리 | 한식 | 양식 | 일식 | 중식 | 아시안 ]");
        for (RecommendSummary recommendSummary : recommendSummaries) {
            String menusString = String.join(" | ", recommendSummary.getMenus());
            System.out.println("[ " + recommendSummary.getCoachName() + " | " + menusString + " ]");
        }
        System.out.println("\n추천을 완료했습니다.");
    }

    public String formatDayList() {
        return Arrays.stream(Day.values())
                .map(Day::getDay)
                .collect(Collectors.joining(" | "));
    }

    public String formatCategoryList() {
        return Arrays.stream(Category.values())
                .map(Category::getCategory)
                .collect(Collectors.joining(" | "));
    }
}
