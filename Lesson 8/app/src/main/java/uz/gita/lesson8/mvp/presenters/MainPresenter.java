package uz.gita.lesson8.mvp.presenters;

import uz.gita.lesson8.mvp.contracts.MainContract;
import uz.gita.lesson8.mvp.models.MainModel;

public class MainPresenter implements MainContract.Presenter {
    private MainContract.Model mainModel;
    private MainContract.View mainView;

    public MainPresenter(MainContract.View view) {
        mainView = view;
        mainModel = new MainModel();
    }

    @Override
    public void loadQuestion() {
        mainView.setCurrentQuestionPosToView(mainModel.getCurrentQuestionPos());
        mainView.setImageQuestionsToView(mainModel.getCurrentQuestionData());
    }

    @Override
    public void openPlayActivity() {
        mainView.goToPlayActivity();
    }
}
