package uz.gita.lesson8.mvp.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import uz.gita.lesson8.R;
import uz.gita.lesson8.data.model.QuestionData;
import uz.gita.lesson8.mvp.contracts.MainContract;
import uz.gita.lesson8.mvp.presenters.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private MainContract.Presenter presenter;
    private AppCompatTextView questionPosTV;
    private List<ImageView> questionImages;
    private AppCompatButton playBtn;
    private AppCompatButton aboutBtn;
    private AppCompatButton exitBtn;

    private final int MAX_IMAGES = 4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new MainPresenter(this);
        findViews();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    private void findViews() {
        questionImages = new ArrayList<>(MAX_IMAGES);

        questionImages.add(findViewById(R.id.image1));
        questionImages.add(findViewById(R.id.image2));
        questionImages.add(findViewById(R.id.image3));
        questionImages.add(findViewById(R.id.image4));

        questionPosTV = findViewById(R.id.textQuestionPos);

        playBtn = findViewById(R.id.buttonPlay);
        aboutBtn = findViewById(R.id.buttonAbout);
        exitBtn = findViewById(R.id.exit);

        clickEvents();


    }

    private void clickEvents() {

        aboutBtn.setOnClickListener(v -> {
            startActivity(new Intent(this, InfoActivity.class));
        });
        playBtn.setOnClickListener(v -> presenter.openPlayActivity());

        exitBtn.setOnClickListener(v -> {
            finishAffinity();
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.loadQuestion();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    public void setImageQuestionsToView(QuestionData data) {
        for (int i = 0; i < MAX_IMAGES; i++) {
            questionImages.get(i).setImageResource(data.getImageQuestions().get(i));
        }
    }

    @Override
    public void setCurrentQuestionPosToView(int pos) {
        questionPosTV.setText(String.valueOf(pos + 1));
    }

    @Override
    public void goToPlayActivity() {
        startActivity(new Intent(this, GameActivity.class));
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }


    @Override
    protected void onStop() {
        super.onStop();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }
}

