package com.ismailgemalmaz.fitnnessapp.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ismailgemalmaz.fitnnessapp.FitnessMove;
import com.ismailgemalmaz.fitnnessapp.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class PopupFragment extends Fragment {

    TextView nameText,descriptionText,calorieText;
    ImageView imageView;
    ProgressBar progressBar;
    private FitnessMove fitnessMove;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView=inflater.inflate(R.layout.fragment_popup,container,false);
        nameText=rootView.findViewById(R.id.fragment_popup_text_name);
        descriptionText=rootView.findViewById(R.id.fragment_popup_text_description);
        calorieText=rootView.findViewById(R.id.fragment_popup_text_calorie);
        imageView=rootView.findViewById(R.id.fragment_popup_imageview);
        progressBar=rootView.findViewById(R.id.fragment_popup_progresbar);

        nameText.setText(fitnessMove.getFitnessName());
        descriptionText.setText(fitnessMove.getFitnessDescription());
        calorieText.setText(""+fitnessMove.getFitnessCalorie());

        Picasso.get().load(fitnessMove.getFitnessPicture()).fit().centerCrop().into(imageView, new Callback() {
            @Override
            public void onSuccess() {
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

        return rootView;
    }

    public static PopupFragment newInstance(){
        return new  PopupFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fitnessMove=getActivity().getIntent().getParcelableExtra("INFO");
    }
}
