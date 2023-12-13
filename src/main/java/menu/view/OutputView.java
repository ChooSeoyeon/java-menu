package menu.view;

import java.util.List;
import menu.model.dto.RecommendSummary;

public class OutputView {
    public void printErrorMessage(String message) {
        System.out.println("[ERROR] " + message);
    }

    public void printRecommendStartAnnounce() {
        System.out.println("점심 메뉴 추천을 시작합니다.");
    }

    public void printRecommendSummary(List<RecommendSummary> recommendSummaries) {
        System.out.println("메뉴 추천 결과입니다.");
        System.out.println("[ 구분 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 ]");
        System.out.println("[ 카테고리 | 한식 | 양식 | 일식 | 중식 | 아시안 ]");
        for (RecommendSummary recommendSummary : recommendSummaries) {
            String menusString = String.join(" | ", recommendSummary.getMenus());
            System.out.println("[ " + recommendSummary.getCoachName() + " | " + menusString + " ]");
        }
        System.out.println("\n추천을 완료했습니다.");
    }
}
