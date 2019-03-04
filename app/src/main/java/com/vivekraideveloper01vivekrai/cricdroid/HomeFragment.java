package com.vivekraideveloper01vivekrai.cricdroid;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.DefaultSliderView;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderLayout;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private InterstitialAd interstitialAd;
    private InterstitialAd interstitialAd2;
    private InterstitialAd interstitialAd3;
    private InterstitialAd interstitialAd4;
    private InterstitialAd interstitialAd5;
    private InterstitialAd interstitialAd6;
    private InterstitialAd interstitialAd7;
    private InterstitialAd interstitialAd8;
    private InterstitialAd interstitialAd9;

    private SliderLayout sliderLayout;
    private ProgressBar progressBar;


    //    First Section
    private RecyclerView iplRecyclerView;
    private IccHome iplHome;
    private List<Upload> iplUploads;
    private DatabaseReference iplDatabaseReference;
    private ValueEventListener iplValueEventListener;

    //    Second Section
    private RecyclerView iccRecyclerView;
    private IccHome iccHome;
    private List<Upload> iccUploads;
    private DatabaseReference iccDatabaseReference;
    private ValueEventListener iccValueEventListener;

    //        Third Section
    private RecyclerView bblRecyclerView;
    private BblHome bblHome;
    private List<Upload> bblUploads;
    private DatabaseReference bblDatabaseRefrence;
    private ValueEventListener bblValueEventListener;

    //        Fourth Section
    private RecyclerView cplRecyclerView;
    private CplHome cplHome;
    private List<Upload> cplUploads;
    private DatabaseReference cplDatabaseRefrence;
    private ValueEventListener cplValueEventListener;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        interstitialAd = new InterstitialAd(getContext());
        interstitialAd2 = new InterstitialAd(getContext());
        interstitialAd3 = new InterstitialAd(getContext());
        interstitialAd4 = new InterstitialAd(getContext());
        interstitialAd5 = new InterstitialAd(getContext());
        interstitialAd6 = new InterstitialAd(getContext());
        interstitialAd7 = new InterstitialAd(getContext());
        interstitialAd8 = new InterstitialAd(getContext());
        interstitialAd9 = new InterstitialAd(getContext());

        interstitialAd.setAdUnitId("ca-app-pub-2331594731643176/2795318376");
        interstitialAd2.setAdUnitId("ca-app-pub-2331594731643176/9352664464");
        interstitialAd3.setAdUnitId("ca-app-pub-2331594731643176/8175314163");
        interstitialAd4.setAdUnitId("ca-app-pub-2331594731643176/9544182254");
        interstitialAd5.setAdUnitId("ca-app-pub-2331594731643176/1474120548");
        interstitialAd6.setAdUnitId("ca-app-pub-2331594731643176/5788646363");
        interstitialAd7.setAdUnitId("ca-app-pub-2331594731643176/6718584651");
        interstitialAd8.setAdUnitId("ca-app-pub-2331594731643176/1282548857");
        interstitialAd9.setAdUnitId("ca-app-pub-2331594731643176/1382497493");

        AdRequest request = new AdRequest.Builder().build();
        interstitialAd.loadAd(request);
        interstitialAd2.loadAd(request);
        interstitialAd3.loadAd(request);
        interstitialAd4.loadAd(request);
        interstitialAd5.loadAd(request);
        interstitialAd6.loadAd(request);
        interstitialAd7.loadAd(request);
        interstitialAd8.loadAd(request);
        interstitialAd9.loadAd(request);

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        sliderLayout = view.findViewById(R.id.imageSlider);
        progressBar = view.findViewById(R.id.progressBar);

        sliderLayout.setIndicatorAnimation(IndicatorAnimations.SWAP); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderLayout.setSliderTransformAnimation(SliderAnimations.FADETRANSFORMATION);
        sliderLayout.setScrollTimeInSec(2); //set scroll delay in seconds :
        setSliderViews();
        iplRecyclerView = view.findViewById(R.id.recycler_view_course);
        courseSection();
        iccRecyclerView = view.findViewById(R.id.recycler_view_books);
        booksSection();
        bblRecyclerView = view.findViewById(R.id.recycler_view_questions);
        questionsSection();
        cplRecyclerView = view.findViewById(R.id.recycler_view_cpl);
        cplSection();
        return view;
    }

    private void courseSection(){
        //        First Section


        iplRecyclerView.setHasFixedSize(true);
        iplRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        iplUploads = new ArrayList<>();
        FirebaseApp.initializeApp(getContext());
        iplDatabaseReference = FirebaseDatabase.getInstance().getReference("Ipl");
        iplDatabaseReference.keepSynced(true);

        iplHome = new IccHome(getContext(), iplUploads);
        iplRecyclerView.setAdapter(iplHome);
        iplHome.setOnItemClickListener(new IccHome.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
                Intent courseDetails = new Intent(getContext(), VideoActivity.class);
                courseDetails.putExtra("name", iplUploads.get(position).getName());
                courseDetails.putExtra("videoId", iplUploads.get(position).getVideoId());
                courseDetails.putExtra("description", iplUploads.get(position).getDescription());
                startActivity(courseDetails);
            }

            @Override
            public void onWhatEverClick(int position) {
                if (interstitialAd2.isLoaded()) {
                    interstitialAd2.show();
                }
            }

            @Override
            public void onDeleteClick(int position) {
                if (interstitialAd3.isLoaded()) {
                    interstitialAd3.show();
                }
            }
        });

        iplValueEventListener = iplDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                iplUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setMkey(postSnapshot.getKey());
                    iplUploads.add(upload);

                }
                iplHome.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Unable to Retrieve , some error occured!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void booksSection(){
        //        Second Section


        iccRecyclerView.setHasFixedSize(true);
        iccRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        iccUploads = new ArrayList<>();
        FirebaseApp.initializeApp(getContext());
        iccDatabaseReference = FirebaseDatabase.getInstance().getReference("IccWorldCup");
        iccDatabaseReference.keepSynced(true);

        iccHome = new IccHome(getContext(), iccUploads);
        iccRecyclerView.setAdapter(iccHome);
        iccHome.setOnItemClickListener(new IccHome.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (interstitialAd4.isLoaded()) {
                    interstitialAd4.show();
                }

                Intent bookDetails = new Intent(getContext(), VideoActivity.class);
                bookDetails.putExtra("name", iccUploads.get(position).getName());
                bookDetails.putExtra("description", iccUploads.get(position).getDescription());
                bookDetails.putExtra("videoId", iccUploads.get(position).getVideoId());

                startActivity(bookDetails);
            }

            @Override
            public void onWhatEverClick(int position) {
                if (interstitialAd5.isLoaded()) {
                    interstitialAd5.show();
                }
            }

            @Override
            public void onDeleteClick(int position) {
                if (interstitialAd6.isLoaded()) {
                    interstitialAd6.show();
                }
            }
        });

        iccValueEventListener = iccDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                iccUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setMkey(postSnapshot.getKey());
                    iccUploads.add(upload);

                }
                iccHome.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Unable to Retrieve , some error occured!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void questionsSection(){
        //        Third Section


        bblRecyclerView.setHasFixedSize(true);
        bblRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        bblUploads = new ArrayList<>();
        FirebaseApp.initializeApp(getContext());
        bblDatabaseRefrence = FirebaseDatabase.getInstance().getReference("Bbl");
        bblDatabaseRefrence.keepSynced(true);

        bblHome = new BblHome(getContext(), bblUploads);
        bblRecyclerView.setAdapter(bblHome);
        bblHome.setOnItemClickListener(new BblHome.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (interstitialAd7.isLoaded()) {
                    interstitialAd7.show();
                }
                Intent questionsDetails = new Intent(getContext(), VideoActivity.class);
                questionsDetails.putExtra("name", bblUploads.get(position).getName());
                questionsDetails.putExtra("description", bblUploads.get(position).getDescription());
                questionsDetails.putExtra("videoId", bblUploads.get(position).getVideoId());
                startActivity(questionsDetails);
            }

            @Override
            public void onWhatEverClick(int position) {
                if (interstitialAd8.isLoaded()) {
                    interstitialAd8.show();
                }
            }

            @Override
            public void onDeleteClick(int position) {
                if (interstitialAd9.isLoaded()) {
                    interstitialAd9.show();
                }
            }
        });

        bblValueEventListener = bblDatabaseRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                bblUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setMkey(postSnapshot.getKey());
                    bblUploads.add(upload);

                }
                bblHome.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Unable to Retrieve , some error occured!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void cplSection(){
        //  Fourth Section


        cplRecyclerView.setHasFixedSize(true);
        cplRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        cplUploads = new ArrayList<>();
        FirebaseApp.initializeApp(getContext());
        cplDatabaseRefrence = FirebaseDatabase.getInstance().getReference("Cpl");
        cplDatabaseRefrence.keepSynced(true);

        cplHome = new CplHome(getContext(), cplUploads);
        cplRecyclerView.setAdapter(cplHome);
        cplHome.setOnItemClickListener(new CplHome.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (interstitialAd.isLoaded()) {
                    interstitialAd.show();
                }
                Intent courseDetails = new Intent(getContext(), VideoActivity.class);
                courseDetails.putExtra("name", cplUploads.get(position).getName());
                courseDetails.putExtra("videoId", cplUploads.get(position).getVideoId());
                courseDetails.putExtra("description", cplUploads.get(position).getDescription());
                startActivity(courseDetails);
            }

            @Override
            public void onWhatEverClick(int position) {
                if (interstitialAd2.isLoaded()) {
                    interstitialAd2.show();
                }
            }

            @Override
            public void onDeleteClick(int position) {
                if (interstitialAd3.isLoaded()) {
                    interstitialAd3.show();
                }
            }
        });

        cplValueEventListener = cplDatabaseRefrence.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                cplUploads.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Upload upload = postSnapshot.getValue(Upload.class);
                    upload.setMkey(postSnapshot.getKey());
                    cplUploads.add(upload);

                }
                cplHome.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Unable to Retrieve , some error occured!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void setSliderViews() {

        for (int i = 0; i <= 3; i++) {

            DefaultSliderView sliderView = new DefaultSliderView(getContext());

            switch (i) {
                case 0:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/cricdroid-e1eab.appspot.com/o/IccCWC%2FICC-CWC19-Public-Ballot_PR-Image.png?alt=media&token=5490cf8f-c5d6-4dc8-8f78-ce1ec009f692");
                    break;
                case 1:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/cricdroid-e1eab.appspot.com/o/BBL%2F3fadb-15447978489761-500.jpg?alt=media&token=7fc4f8d9-39c9-409b-897a-8f5653d86013");
                    break;
                case 2:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/cricdroid-e1eab.appspot.com/o/IPL%2Fa220301dbfa84edd4ffc6ce9bb528841.png?alt=media&token=c192341b-2ca8-472b-9195-3f93dbe6d9ab");
                    break;
                case 3:
                    sliderView.setImageUrl("https://firebasestorage.googleapis.com/v0/b/cricdroid-e1eab.appspot.com/o/Cpl%2FCPLT20PATCH_BYWORLDWIDECRICKETSTUDIO.jpg?alt=media&token=ec5b0450-59b1-4c14-af90-bd83eb6f996e");
                    break;
            }

            sliderView.setImageScaleType(ImageView.ScaleType.FIT_XY);
            sliderView.setDescription("");
            final int finalI = i;
            sliderView.setOnSliderClickListener(new SliderView.OnSliderClickListener() {
                @Override
                public void onSliderClick(SliderView sliderView) {
                    if(interstitialAd.isLoaded()){
                        interstitialAd.show();
                    }
                }
            });

            //at last add this view in your layout :
            sliderLayout.addSliderView(sliderView);
        }
    }
}

