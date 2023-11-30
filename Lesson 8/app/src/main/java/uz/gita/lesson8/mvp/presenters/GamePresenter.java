package uz.gita.lesson8.mvp.presenters;

import static uz.gita.lesson8.Constants.MAX_ANSWER;
import static uz.gita.lesson8.Constants.MAX_VARIANT;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import uz.gita.lesson8.data.localStorge.LocalStorage;
import uz.gita.lesson8.data.model.QuestionData;
import uz.gita.lesson8.mvp.contracts.GameContract;
import uz.gita.lesson8.mvp.models.GameModel;

public class GamePresenter implements GameContract.Presenter {
    private final GameContract.View gameView;
    private final GameContract.Model gameModel;

    private List<String> typedAnswers;
    private List<Boolean> typedVariants;
    private List<TextView> variants;
    private LocalStorage mypref;


    public GamePresenter(GameContract.View gameView) {
        this.gameView = gameView;
        gameModel = new GameModel();
    }

    @Override
    public void loadCurrentQuestion() {
        gameView.clearOldData();
        gameView.showQuestionImagesToView(gameModel.getQuestionData().getImageQuestions());
        gameView.showVariantsToView(gameModel.getQuestionData().getVariant());
        gameView.setVisibilityToAnswer(gameModel.getQuestionData().getAnswer().length());
        gameView.showCoins();
        initTypedArrays();
    }


    @Override
    public void answerBtnClick(int clickPosition) {
        QuestionData question = gameModel.getQuestionData();
        String variant = question.getVariant();
        String typedLetter = typedAnswers.get(clickPosition);

        for (int i = 0; i < MAX_VARIANT.value; i++) {
            if (String.valueOf(variant.charAt(i)).equals(typedLetter) && typedVariants.get(i)) {
                gameView.setEnabledVariantByPos(i, true);
                typedVariants.set(i, false);
                typedAnswers.set(clickPosition, null);
                gameView.setTextToAnswer(clickPosition, "");
                gameView.setDefaultColorToAnswers();
                return;
            }
        }
    }

    @Override
    public void clickHelp() {
        gameView.clickHelp();
    }

    @Override
    public void variantBtnClick(int clickPosition) {
        int positionAnswer = typedAnswers.indexOf(null);
        if (positionAnswer == -1) return;

        QuestionData question = gameModel.getQuestionData();

        String variant = question.getVariant();
        String letter = "" + variant.charAt(clickPosition);
        gameView.setTextToAnswer(positionAnswer, letter);
        typedAnswers.set(positionAnswer, letter);
        gameView.setEnabledVariantByPos(clickPosition, false);
        typedVariants.set(clickPosition, true);

        isWin();
    }

    @Override
    public void finishActivity() {
        gameView.closeActivity();
    }

    private void isWin() {
        QuestionData questionData = gameModel.getQuestionData();
        String answer = questionData.getAnswer();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < typedAnswers.size(); i++) {
            sb.append(typedAnswers.get(i));
        }
        String userAnswer = sb.toString();
        if (userAnswer.length() != answer.length()) return;
        if (userAnswer.equals(answer)) {
            saveQuestionPos(gameModel.getCurrentQuestionPos() + 1);
            gameView.showDialog();
        } else {
            gameView.setWrongColorToAnswers();
            gameView.wrongAnswerAnimation();
        }
    }


    private void saveQuestionPos(int questionPos) {
        gameModel.saveCurrentQuestionPos(questionPos);
    }

    @Override
    public void loadNextQuestion() {
        loadCurrentQuestion();
    }

    private void initTypedArrays() {
        int answerSize = gameModel.getQuestionData().getAnswer().length();
        typedAnswers = new ArrayList<>(answerSize);
        for (int i = 0; i < answerSize; i++) {
            typedAnswers.add(null);
        }
        typedVariants = new ArrayList<>();
        for (int i = 0; i < MAX_VARIANT.value; i++) {
            typedVariants.add(false);
            gameView.setEnabledVariantByPos(i, true);
        }
    }
}
