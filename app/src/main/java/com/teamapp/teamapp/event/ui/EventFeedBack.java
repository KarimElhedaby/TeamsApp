package com.teamapp.teamapp.event.ui;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.teamapp.teamapp.R;
import com.teamapp.teamapp.event.adapter.EventCommentAdapter;
import com.teamapp.teamapp.event.model.Comment_FeedbackData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventFeedBack extends AppCompatActivity {

    @BindView(R.id.graph)
    GraphView graphView;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.event_feedback_commentRV)
    RecyclerView event_feedback_commentRV;
    @BindView(R.id.feedbacksend_comment_IB)
    ImageButton feedbacksend_comment_IB;
    @BindView(R.id.eventcomment_ET)
    EditText eventcomment_ET;


    private EventCommentAdapter eventCommentAdapter;
    private List<Comment_FeedbackData> comment_feedbackData;
    private Comment_FeedbackData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_feed_back);
        ButterKnife.bind(this);
        comment_feedbackData = new ArrayList<>();
        event_feedback_commentRV.setLayoutManager
                (new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        eventCommentAdapter = new EventCommentAdapter(comment_feedbackData, R.layout.comment_feedback_item, this);
        event_feedback_commentRV.setAdapter(eventCommentAdapter);


        GraphView graph = (GraphView) findViewById(R.id.graph);
        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                new DataPoint(0, -1),
                new DataPoint(1, 5),
                new DataPoint(2, 3),
                new DataPoint(3, 2),
                new DataPoint(4, 6)
        });
        graph.addSeries(series);

// styling
        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX() * 255 / 4, (int) Math.abs(data.getY() * 255 / 6), 100);
            }
        });
        graph.getViewport().setScalable(true);
        graph.getViewport().setScalableY(true);
        series.setSpacing(50);

// draw values on top
        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.RED);
        addListenerOnRatingBar();


    }

    @OnClick(R.id.feedbacksend_comment_IB)
    public void addCommentData() {
        String comment_content = eventcomment_ET.getText().toString();
        data = new Comment_FeedbackData(comment_content);
        eventCommentAdapter.addData(data);
        eventcomment_ET.setText(" ");
    }

    public void addListenerOnRatingBar() {

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);


        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {
                String rating_value = String.valueOf(rating);

            }
        });
    }


}
