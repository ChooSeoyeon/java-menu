package menu.controller;

import java.util.List;
import java.util.function.Supplier;
import menu.model.Coaches;
import menu.model.Recommend;
import menu.model.enums.Category;
import menu.model.enums.Day;
import menu.model.generator.CategoryRandomNumberGenerator;
import menu.model.generator.MenuShuffleGenerator;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuRecommendController {
    private final InputView inputView;
    private final OutputView outputView;
    private final Recommend recommend;
    private Coaches coaches;

    public MenuRecommendController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.recommend = new Recommend(new CategoryRandomNumberGenerator(), new MenuShuffleGenerator());
    }

    public void run() {
        prepare();
        start();
        end();
    }

    private void prepare() {
        outputView.printRecommendStartAnnounce();
        coaches = repeatUntilSuccessWithReturn(this::prepareCoach);
        for (String coachName : coaches.getCoachNames()) {
            repeatUntilSuccess(() -> updateCoachMenuDontEat(coachName));
        }
    }

    private void start() {
        for (Day day : Day.values()) {
            Category recommendedCategory = repeatUntilSuccessWithReturn(recommend::recommendCategory);
            repeatUntilSuccess(() -> coaches.recommendMenu(recommend, recommendedCategory));
        }
    }

    public void end() {
        outputView.printRecommendSummary(coaches.collectRecommendSummaries(),
                recommend.captureRecommendCategorySummaries());
    }

    private void updateCoachMenuDontEat(String coachName) {
        List<String> menus = inputView.readMenusBy(coachName);
        coaches.updateCoachMenusDontEat(coachName, menus);
    }

    private Coaches prepareCoach() {
        List<String> coachNames = inputView.readCoachNames();
        return new Coaches(coachNames);
    }

    private <T> T repeatUntilSuccessWithReturn(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void repeatUntilSuccess(Runnable action) {
        while (true) {
            try {
                action.run();
                return;
            } catch (IllegalArgumentException | IllegalStateException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
