package uz.gita.lesson8.mvp.contracts;

import java.util.List;

import uz.gita.lesson8.data.model.QuestionData;

public interface GameContract {
    interface Model {
        QuestionData getQuestionData();

        int getCurrentQuestionPos();

        void saveCurrentQuestionPos(int pos);
    }

    interface Presenter {
        void loadCurrentQuestion();

        void answerBtnClick(int clickPosition);
        void clickHelp();

        void variantBtnClick(int clickPosition);

        void finishActivity();

        void loadNextQuestion();
    }

    interface View {
        void showQuestionImagesToView(List<Integer> questionImages);

        void showVariantsToView(String variant);
        void showCoins();

        void setVisibilityToAnswer(int answerLength);
        void clickHelp();
        void clearOldData();
        void closeActivity();
        void showDialog();

        void setTextToAnswer(int pos, String letter);
        void setEnabledVariantByPos(int pos, boolean state);
        void wrongAnswerAnimation();
        void setWrongColorToAnswers();
        void setDefaultColorToAnswers();

    }
}
