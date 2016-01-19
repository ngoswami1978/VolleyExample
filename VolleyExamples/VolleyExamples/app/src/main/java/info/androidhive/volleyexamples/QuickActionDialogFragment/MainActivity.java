package info.androidhive.volleyexamples.QuickActionDialogFragment;

/**
 * Created by Admin on 07/01/2016.
 */

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import info.androidhive.volleyexamples.CustomDialog;

import info.androidhive.volleyexamples.R;

public class MainActivity extends AppCompatActivity {
    private Context mContext;

    private CustomDialog dialog ;
    private String fareRules;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quickactiondialogactivity_main);

        dialog  = new CustomDialog(this);

        final View buttonShow = findViewById(R.id.btnShow);
        final View buttonShow1 = findViewById(R.id.btnNavigation);
        final View buttonShow2 = findViewById(R.id.btnNavigation1);
        fareRules="SEP 2015";


        buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mySampleDialogFragment = new MySampleDialogFragment();
                mySampleDialogFragment.setAnchorView(buttonShow);
                mySampleDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                mySampleDialogFragment.show(getSupportFragmentManager(), null);

            }
        });

        buttonShow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mySampleDialogFragment = new MySampleDialogFragment();
                mySampleDialogFragment.setAnchorView(buttonShow1);
                mySampleDialogFragment.setAligmentFlags(AlignmentFlag.ALIGN_ANCHOR_VIEW_LEFT | AlignmentFlag.ALIGN_ANCHOR_VIEW_BOTTOM);
                mySampleDialogFragment.show(getSupportFragmentManager(), null);
            }
        });

        buttonShow2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
//                dialog.showDialogWithMsg("New Information from Main");
                dialog.show();
            }
        });
    }

    private MySampleDialogFragment mySampleDialogFragment;

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        // must dismiss this dialog before orientation change to avoid AnchorView is deleted!
        if (mySampleDialogFragment != null && mySampleDialogFragment.isVisible()) {
            mySampleDialogFragment.dismiss();
        }
        super.onSaveInstanceState(outState);
    }


    public static class MySampleDialogFragment extends QuickActionDialogFragment {

        @Override
        protected int getArrowImageViewId() {
            return R.id.ivArrow;
//            return 0; that mean you donot have an arrow
        }

        @Override
        protected int getLayout() {
            return R.layout.dialog_sample_view;
        }

        @Override
        protected boolean isStatusBarVisible() {
            return true;
        }


        @Override
        protected boolean isCanceledOnTouchOutside() {
            return true;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = super.onCreateView(inflater, container, savedInstanceState);

            TextView myAwesomeTextView = (TextView)view.findViewById(R.id.btnSampleTitle);
            //in your OnCreate() method
            myAwesomeTextView.setText("My Awesome Text");

            view.findViewById(R.id.btnSample).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "Button inside Dialog!!", Toast.LENGTH_SHORT).show();

                }
            });

            return view;
        }
    }

}