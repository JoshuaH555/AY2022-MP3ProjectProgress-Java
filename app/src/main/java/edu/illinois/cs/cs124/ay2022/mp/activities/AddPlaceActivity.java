package edu.illinois.cs.cs124.ay2022.mp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.concurrent.CompletableFuture;
import edu.illinois.cs.cs124.ay2022.mp.R;
import edu.illinois.cs.cs124.ay2022.mp.application.FavoritePlacesApplication;
import edu.illinois.cs.cs124.ay2022.mp.models.Place;
import edu.illinois.cs.cs124.ay2022.mp.models.ResultMightThrow;


public class AddPlaceActivity extends AppCompatActivity {
  private static final String TAG = AddPlaceActivity.class.getSimpleName();
  @Override
  protected void onCreate(@Nullable final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_addplace);
    Intent returnToMain = new Intent(this, MainActivity.class);
    returnToMain.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    Button cancelButton = findViewById(R.id.cancel_button);
    cancelButton.setOnClickListener(v -> {
      startActivity(returnToMain);
    });
    Button saveButton = findViewById(R.id.save_button);
    saveButton.setOnClickListener(v -> {
      String id = "fdbc8e24-e17f-4a38-9b9e-71ad0e960714";
      String name = "Joshua Hernandez";
      EditText desc = findViewById(R.id.description);
      String newDesc = desc.getText().toString();
      Bundle extra = getIntent().getExtras();
      String lats = extra.getString("latitude");
      String longs = extra.getString("longitude");
      CompletableFuture<ResultMightThrow<Boolean>> completableFuture =
          new CompletableFuture<>();
      Place newFavePlace =
          new Place(id, name, Double.parseDouble(lats), Double.parseDouble(longs), newDesc);
      FavoritePlacesApplication favoritePlacesApplication = (FavoritePlacesApplication) getApplication();
      favoritePlacesApplication
          .getClient()
          .postFavoritePlace(newFavePlace, completableFuture::complete);
      startActivity(returnToMain);
    });
  }
}
