package com.example.hutchhub;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.scroll.DefaultScrollHandle;
import com.github.barteksc.pdfviewer.util.FitPolicy;

public class RabbitHusbandry extends AppCompatActivity {

    private PDFView pdfView;
    private int pageNumber = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rabbit_husbandry);


        pdfView = findViewById(R.id.pdfViewRabbitHusbandry);

        pdfView.fromAsset("RabbitHusbandry.pdf")
                .defaultPage(pageNumber)
                .scrollHandle(new DefaultScrollHandle(this))
                .pageFitPolicy(FitPolicy.BOTH)
                .load();
    }
    }
