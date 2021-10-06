package edu.byu.cs.tweeter.client.util;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

/*
 *
 */
public class SpanningLinksUtils {
    public static void makeLinks(TextView textView, List<Pair<String, View.OnClickListener>> links) {
        SpannableString spannableString = new SpannableString(textView.getText().toString());
        int startIndexState = -1;

        for (Pair<String, View.OnClickListener> link : links) {
            ClickableSpan clickableSpan = new ClickableSpan() {
                @Override
                public void onClick(@NonNull View widget) {
                    widget.invalidate();
                    assert link.second != null;
                    link.second.onClick(widget);
                }
            };
            assert link.first != null;
            int startIndexOfLink = textView.getText().toString().indexOf(link.first, startIndexState + 1);
            spannableString.setSpan(clickableSpan, startIndexOfLink, startIndexOfLink + link.first.length(), Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
        }
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setText(spannableString, TextView.BufferType.SPANNABLE);
    }
}
