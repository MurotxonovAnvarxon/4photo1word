package uz.gita.lesson8.mvp.views;

import static uz.gita.lesson8.Constants.MAX_ANSWER;
import static uz.gita.lesson8.Constants.MAX_IMAGES;
import static uz.gita.lesson8.Constants.MAX_VARIANT;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import uz.gita.lesson8.R;
import uz.gita.lesson8.data.localStorge.LocalStorage;
import uz.gita.lesson8.data.model.QuestionData;
import uz.gita.lesson8.mvp.contracts.GameContract;
import uz.gita.lesson8.mvp.presenters.GamePresenter;
import uz.gita.lesson8.repository.Repository;

public class GameActivity extends AppCompatActivity implements GameContract.View {
    private GameContract.Presenter presenter;


    private List<ImageView> questionImages;
    private List<TextView> answers;
    private ConstraintLayout images;
    private LinearLayout ansveButtons;
    private Vibrator vibrator;
    private RelativeLayout variantButtons;
    private List<TextView> variants;
    private ImageView back;
    private TextView coins;
    private LottieAnimationView lotti;
    private ImageView clean;
    private AppCompatButton backHome;
    private AppCompatButton friends;
//    private LottieAnimationView helpbtn;
    private AppCompatButton next;
    private LocalStorage mypref;
    private int coin;
    private static Dialog dialog;

    private MediaPlayer music;


    private ImageView image1;
    private ImageView image2;
    private ImageView image3;
    private ImageView image4;
    private ImageView selectImage;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        presenter = new GamePresenter(this);
        findViews();
        presenter.loadCurrentQuestion();
        back = findViewById(R.id.back);
        friends = findViewById(R.id.friends);
//        helpbtn = findViewById(R.id.help);
        back.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
        });
        music = MediaPlayer.create(this, R.raw.music);
        clean = findViewById(R.id.clean);
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        selectImage = findViewById(R.id.selectImage);


        clean.setOnClickListener(v2 -> {
            presenter.loadCurrentQuestion();
            for (int i = 0; i < MAX_ANSWER.value; i++) {
                answers.get(i).setTextColor(getResources().getColor(R.color.white));
            }
        });
        clickImages();


        friends.setOnClickListener(v -> {

            Bitmap bitmap = takeScreenshot();
            saveBitmap(bitmap);
            shareImage();

        });

    }

    public Bitmap takeScreenshot() {
        View rootView = getWindow().getDecorView().getRootView();
        rootView.setDrawingCacheEnabled(true);
        return rootView.getDrawingCache();
    }

    public void saveBitmap(Bitmap bitmap) {
        File imagePath = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "screenshot.png");
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(imagePath);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            Log.e("ShareActivity", "Error saving screenshot", e);
        }
    }

    public void shareImage() {
        File imagePath = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "screenshot.png");
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("image/*");

        // Use a FileProvider to get a content URI
        String authority = getPackageName() + ".provider";
        Uri contentUri = FileProvider.getUriForFile(this, authority, imagePath);

        sharingIntent.putExtra(Intent.EXTRA_STREAM, contentUri);
        startActivity(Intent.createChooser(sharingIntent, "Share using"));
    }


    private void clickImages() {
        image1.setOnClickListener(v -> {
            selectImage.setImageDrawable(image1.getDrawable());
        });
    }

    private void findViews() {
        questionImages = new ArrayList<>(MAX_IMAGES.value);

        questionImages.add(findViewById(R.id.image1));
        questionImages.add(findViewById(R.id.image2));
        questionImages.add(findViewById(R.id.image3));
        questionImages.add(findViewById(R.id.image4));

        showCoins();
        answers = new ArrayList<>(MAX_ANSWER.value);
        LinearLayout parentOfAnswerButtons = findViewById(R.id.answers);

        for (int i = 0; i < MAX_ANSWER.value; i++) {
            TextView btn = (TextView) parentOfAnswerButtons.getChildAt(i);
            btn.setTag(i);
            answers.add(btn);
        }

        variants = new ArrayList<>(MAX_VARIANT.value);
        RelativeLayout parentOfVariantButtons = findViewById(R.id.variants);

        for (int i = 0; i < MAX_VARIANT.value; i++) {
            TextView btn = (TextView) parentOfVariantButtons.getChildAt(i);
            btn.setTag(i);
            variants.add(btn);
        }
        clickEvents();


    }


    @Override
    public void showCoins() {
//        coins.setText(mypref.getCoin());
    }

    @Override
    public void showQuestionImagesToView(List<Integer> images) {
        for (int i = 0; i < MAX_IMAGES.value; i++) {
            questionImages.get(i).setImageResource(images.get(i));
        }
    }

    @Override
    public void showVariantsToView(String variant) {
        for (int i = 0; i < MAX_VARIANT.value; i++) {
            variants.get(i).setText(String.valueOf(variant.charAt(i)));
        }
    }


    @Override
    public void setVisibilityToAnswer(int answerLength) {
        for (int i = 0; i < answerLength; i++) {
            answers.get(i).setVisibility(View.VISIBLE);
        }

        for (int i = answerLength; i < MAX_ANSWER.value; i++) {
            answers.get(i).setVisibility(View.GONE);
        }
    }

    @Override
    public void clickHelp() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            for (int j = 0; j < MAX_VARIANT.value; j++) {
                if (variants.get(j) != answers.get(i)) {
                    variants.get(j).setText("");
                }
            }
        }
    }

    @Override
    public void clearOldData() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setText("");
        }

        for (int i = 0; i < MAX_VARIANT.value; i++) {
            variants.get(i).setEnabled(true);
        }
    }

    @Override
    public void closeActivity() {
        finish();
    }

    @Override
    public void showDialog() {
        music.start();
        coin += 20;

        friends.setVisibility(View.INVISIBLE);
//        helpbtn.setVisibility(View.INVISIBLE);

        lotti = findViewById(R.id.lottie);
        lotti.setVisibility(View.VISIBLE);

        backHome = findViewById(R.id.back2);
        backHome.setVisibility(View.VISIBLE);

        next = findViewById(R.id.next);
        next.setVisibility(View.VISIBLE);

        images = findViewById(R.id.constraintLayout);
        images.setVisibility(View.INVISIBLE);

        ansveButtons = findViewById(R.id.answers);
        ansveButtons.setVisibility(View.INVISIBLE);

        variantButtons = findViewById(R.id.variants);
        variantButtons.setVisibility(View.INVISIBLE);


        next.setOnClickListener(v -> {
            presenter.loadNextQuestion();

            lotti.setVisibility(View.INVISIBLE);
            backHome.setVisibility(View.INVISIBLE);
            next.setVisibility(View.INVISIBLE);
            images.setVisibility(View.VISIBLE);
            ansveButtons.setVisibility(View.VISIBLE);
            variantButtons.setVisibility(View.VISIBLE);
            friends.setVisibility(View.VISIBLE);
//            helpbtn.setVisibility(View.VISIBLE);
        });
        backHome.setOnClickListener(v -> {
            presenter.finishActivity();
        });

    }

    @Override
    public void setTextToAnswer(int pos, String letter) {
        answers.get(pos).setText(letter);
    }

    @Override
    public void setEnabledVariantByPos(int pos, boolean state) {
        if (state)
            variants.get(pos).setVisibility(View.VISIBLE);
        else variants.get(pos).setVisibility(View.INVISIBLE);
    }

    @Override
    public void wrongAnswerAnimation() {
        YoYo.with(Techniques.Shake).duration(600).playOn(findViewById(R.id.answers));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(
                    VibrationEffect.createOneShot(
                            500,
                            VibrationEffect.DEFAULT_AMPLITUDE
                    ));
        } else {
            vibrator.vibrate(500);
        }
    }

    @Override
    public void setWrongColorToAnswers() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setTextColor(getResources().getColor(R.color.red));
        }
    }

    @Override
    public void setDefaultColorToAnswers() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setTextColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onBackPressed() {
        presenter.finishActivity();
    }

    private void clickEvents() {
        for (int i = 0; i < MAX_ANSWER.value; i++) {
            answers.get(i).setOnClickListener(v -> {
                TextView btn = (TextView) v;
                presenter.answerBtnClick((int) btn.getTag());
            });
        }

        for (int i = 0; i < MAX_VARIANT.value; i++) {
            variants.get(i).setOnClickListener(v -> {
                TextView btn = (TextView) v;
                presenter.variantBtnClick((int) btn.getTag());
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }

    @Override
    protected void onStop() {
        super.onStop();
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_IMMERSIVE | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
    }


}