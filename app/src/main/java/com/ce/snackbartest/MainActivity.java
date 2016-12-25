package com.ce.snackbartest;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.os.AsyncTask;
import android.os.Build;
import android.support.design.widget.Snackbar;
import android.support.design.widget.Snackbar.Callback;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListenerAdapter;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button _ShowSnackBar = (Button) findViewById(R.id.show_snackbar);
        _ShowSnackBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "我就是Snackbar!", Snackbar.LENGTH_LONG)
                        .setAction("Action", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        })
                        .setCallback(new Callback() {
                            @Override
                            public void onDismissed(Snackbar snackbar, int event) {
                                Toast.makeText(MainActivity.this, "onDismissed", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onShown(Snackbar snackbar) {
                                Toast.makeText(MainActivity.this, "onShown", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .show();*/


                Snackbar _Snackbar = Snackbar.make(view, "我是从左到右的Snackbar!", Snackbar.LENGTH_INDEFINITE);
                _Snackbar.setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                View _RootView = _Snackbar.getView();
                snackbarInAnim(_Snackbar, _RootView);
                TextView _Text = (TextView) _Snackbar.getView().findViewById(R.id.snackbar_text);
                Button _Action = (Button) _Snackbar.getView().findViewById(R.id.snackbar_action);
                _RootView.setBackgroundColor(ColorStateList.valueOf(0xFF99CCFF).getDefaultColor());
                _Text.setTextColor(ColorStateList.valueOf(0xFFFFFFFF));
                _Action.setTextColor(ColorStateList.valueOf(0xFFFFFF66));
                _Snackbar.show();
            }
        });
    }

    private void snackbarInAnim(final Snackbar pSnackbar, final View pView) {
        Animation _Anim = AnimationUtils.loadAnimation(pView.getContext(),
                R.anim.snackbar_in_anim);
        _Anim.setInterpolator(new FastOutSlowInInterpolator());
        _Anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                snackbarOutAnim(pSnackbar, pView);
            }
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        pView.startAnimation(_Anim);
    }

    private void snackbarOutAnim(final Snackbar pSnackbar, final View pView) {
        Animation _Anim = AnimationUtils.loadAnimation(pView.getContext(),
                R.anim.snackbar_out_anim);
        _Anim.setInterpolator(new FastOutSlowInInterpolator());
        _Anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation animation) {
                pView.setVisibility(View.GONE);
                pSnackbar.dismiss();
            }
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });
        pView.startAnimation(_Anim);
    }

}
