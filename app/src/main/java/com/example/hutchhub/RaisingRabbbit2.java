package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class RaisingRabbbit2 extends AppCompatActivity {

    private PDFView pdfView;
    private int pageNumber = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raising_rabbbit2);


        pdfView = findViewById(R.id.pdfViewRaisingRabbit2);

        pdfView.fromAsset("RaisingRabbits1.pdf")
                .defaultPage(pageNumber)
                .scrollHandle(new DefaultScrollHandle(this))
                .pageFitPolicy(FitPolicy.BOTH)
                .load();
    }
}