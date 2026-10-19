package com.example.truefalsequiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        LinearLayout mainLayout = findViewById(R.id.main);
        TextView tvResult = findViewById(R.id.tvResult);
        TextView tvExplanation = findViewById(R.id.tvExplanation);

        boolean isCorrect = getIntent().getBooleanExtra("isCorrect", false);
        String explanation = getIntent().getStringExtra("explanation");
        boolean fromSecondQuestion = getIntent().getBooleanExtra("fromSecondQuestion", false);

        if (isCorrect) {
            tvResult.setText("You Win!");
            mainLayout.setBackgroundColor(getColor(R.color.result_correct));
        } else {
            tvResult.setText("Game Over");
            mainLayout.setBackgroundColor(getColor(R.color.result_wrong));
        }

        tvExplanation.setText(explanation);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button btnNextQuestion = findViewById(R.id.btnNextQuestion);
        if (fromSecondQuestion) {
            btnNextQuestion.setVisibility(View.GONE);
        }

        btnNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, SecondQuestionActivity.class);
                startActivity(intent);
            }
        });
    }
}
