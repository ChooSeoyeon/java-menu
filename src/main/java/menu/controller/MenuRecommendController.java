package menu.controller;

import java.util.List;
import java.util.function.Supplier;
import menu.view.InputView;
import menu.view.OutputView;

public class MenuRecommendController {
    private final InputView inputView;
    private final OutputView outputView;

    public MenuRecommendController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> coachNames = repeatUntilSuccessWithReturn(inputView::readCoachNames);
        for (String coachName : coachNames) {
            List<String> menus = repeatUntilSuccessWithReturn(() -> inputView.readMenusBy(coachName));
        }
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
