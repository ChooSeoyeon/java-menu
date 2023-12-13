package menu;

import menu.controller.MenuRecommendController;
import menu.view.InputView;
import menu.view.OutputView;

public class Application {
    public static void main(String[] args) {
        new MenuRecommendController(new InputView(), new OutputView()).run();
    }
}
