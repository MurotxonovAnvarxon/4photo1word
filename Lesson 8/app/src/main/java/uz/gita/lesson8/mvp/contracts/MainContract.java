package uz.gita.lesson8.mvp.contracts;

import uz.gita.lesson8.data.model.QuestionData;

public interface MainContract {
    interface Model {
        QuestionData getCurrentQuestionData();

        int getCurrentQuestionPos();
    }

    interface View {
        void setImageQuestionsToView(QuestionData data);

        void setCurrentQuestionPosToView(int pos);

        void goToPlayActivity();

    }

    interface Presenter {
        void loadQuestion();

        void openPlayActivity();
    }
}
