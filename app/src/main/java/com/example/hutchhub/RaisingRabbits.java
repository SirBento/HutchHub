package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class RaisingRabbits extends AppCompatActivity {
    private PDFView pdfView;
    private int pageNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_raising_rabbits);


        pdfView = findViewById(R.id.pdfViewRaisingRabbits);

        pdfView.fromAsset("RaisingRabbits1.pdf")
                .defaultPage(pageNumber)
                .scrollHandle(new DefaultScrollHandle(this))
                .pageFitPolicy(FitPolicy.BOTH)
                .load();
    }
}