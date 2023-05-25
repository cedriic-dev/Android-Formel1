package at.ac.htlperg.viewmodeldemo.activities;

import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import at.ac.htlperg.viewmodeldemo.R;
import at.ac.htlperg.viewmodeldemo.utils.Team;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Retrieve data from MainActivity intent
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String givenName = intent.getStringExtra("givenName");
        String familyName = intent.getStringExtra("familyName");
        String team = intent.getStringExtra("team");
        String image = intent.getStringExtra("image");
        String nationality = intent.getStringExtra("nationality");
        String highestRaceFinish = intent.getStringExtra("highest_race_finish");
        String grandsPrix = intent.getStringExtra("grands_prix");
        String points = intent.getStringExtra("points");

        ImageView driverImageView = findViewById(R.id.driverImageDetail);
        Picasso.get().load(image).into(driverImageView);

        TextView driverNameView = findViewById(R.id.driverNameDetail);
        driverNameView.setText(givenName + " " + familyName);

        TextView teamView = findViewById(R.id.driverTeamDetail);
        teamView.setText(team);

        // Set team color background
        Team teamEnum = Team.fromTeamName(team);
        if (teamEnum != null) {
            LinearLayout driverImageContainer = findViewById(R.id.driverImageContainer);
            int teamColor = getResources().getColor(teamEnum.getColorResourceId());
            GradientDrawable circleBackground = (GradientDrawable) driverImageContainer.getBackground();
            circleBackground.setColor(teamColor);
        }

        TextView highestRaceFinishView = findViewById(R.id.driverHighestRaceFinishDetail);
        highestRaceFinishView.setText(highestRaceFinish);

        TextView grandsPrixView = findViewById(R.id.driverGrandPrixDetail);
        grandsPrixView.setText(grandsPrix);

        TextView pointsView = findViewById(R.id.driverPointsDetail);
        pointsView.setText(points);
    }
}
