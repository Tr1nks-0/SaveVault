package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.bytes.TextBytes;
import com.tr1nks.safevault.util.Encoder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MultilineTextFieldFragment extends Field {

    private TextBytes textBytes;

    public MultilineTextFieldFragment() {
        // Required empty public constructor
    }

    public void setTextBytes(TextBytes textBytes) {
        this.textBytes = textBytes;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_multiline_text_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        ((TextView) view.findViewById(R.id.titleForMultilineText)).setText(getArguments().getString("title"));
        final TextBytes tb = getArguments().getParcelable("textBytes");
        tb.setTitle(Encoder.encode(getArguments().getByteArray("password"), getArguments().getString("title").getBytes()));
//        ((EditText) view.findViewById(R.id.fragmentMultilineEditText)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    // the user is done typing.
//                    tb.setData(Encoder.encode(getArguments().getByteArray("password"), ((EditText) v).getText().toString().getBytes()));
//                    return true; // consume.
//                }
//                return false; // pass on to other listeners.
//            }
//        });
        view.findViewById(R.id.fragmentMultilineEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    tb.setData(Encoder.encode(getArguments().getByteArray("password"), ((EditText) view).getText().toString().getBytes()));
                }
            }
        });
        return view;
    }

    @Override
    public void onParentPauseAction() {

    }
}
