package com.tr1nks.safevault.activities.fragments.fields;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tr1nks.safevault.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditTextFieldFragment extends Field {
    public EditTextFieldFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_text_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        //        view.findViewById(R.id.deleteFieldImageButton).setOnClickListener(super.fieldDeleteButtonHandler(view));
//        int inpTypeId = getArguments().getInt("type");
//        int inpTypeId = InputType.TYPE_CLASS_NUMBER;
//        final TextBytes tb = getArguments().getParcelable("textBytes");
//        tb.setTitle(Encoder.encode(getArguments().getByteArray("password"), getArguments().getString("title").getBytes()));
//        int inpType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME;
//        switch (inpTypeId) {
//            case R.id.numberFieldMenuItem: {
//                inpType = InputType.TYPE_CLASS_NUMBER;
//                break;
//            }
//            case R.id.loginFieldMenuItem: {
//                inpType = InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD;
//                break;
//            }
//            case R.id.urlFieldMenuItem: {
//                inpType = InputType.TYPE_TEXT_VARIATION_URI;
//                break;
//            }
//            case R.id.emailFieldMenuItem: {
//                inpType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
//                break;
//            }
//            default: {
//                break;
//            }
//        }
//        ((EditText) view.findViewById(R.id.fragmentEditText)).setInputType(inpType);
//        //        ((EditText) view.findViewById(R.id.fragmentMultilineEditText)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
////            @Override
////            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
////                if (actionId == EditorInfo.IME_ACTION_DONE) {
////                    // the user is done typing.
////                    tb.setData(Encoder.encode(getArguments().getByteArray("password"), ((EditText) v).getText().toString().getBytes()));
////                    return true; // consume.
////                }
////                return false; // pass on to other listeners.
////            }
////        });
//        view.findViewById(R.id.fragmentMultilineEditText).setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                if (!hasFocus) {
//                    tb.setData(Encoder.encode(getArguments().getByteArray("password"), ((EditText) view).getText().toString().getBytes()));
//                }
//            }
//        });

//        ((EditText) view.findViewById(R.id.fragmentEditText)).setOnKeyListener(
//                new View.OnKeyListener() {
//                    @Override
//                    public boolean onKey(View view, int keyCode, KeyEvent event) {
//                        if ((keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_ENTER)) {
//                            tb.setData(Encoder.encode(getArguments().getByteArray("password"), ((EditText) view).getText().toString().getBytes()));
//                            return true;
//                        } else {
//                            return false;
//                        }
//                    }
//                }
//        );

//        public boolean onKeyPreIme(int keyCode, KeyEvent event) {
//            if (keyCode == KeyEvent.KEYCODE_BACK &&
//                    event.getAction() == KeyEvent.ACTION_UP) {
//                // Do your thing here
//                return false;
//            }
//            return super.dispatchKeyEvent(event);
//        }

//        ((EditText) view.findViewById(R.id.fragmentEditText)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
//                if (actionId == EditorInfo.IME_ACTION_DONE) {
//                    // the user is done typing.
//
//                    return true; // consume.
//                }
//                return false; // pass on to other listeners.
//            }
//        });

//        ((TextView) view.findViewById(R.id.fragmentTitleForTextEditText)).setText(getArguments().getString("title"));
        return view;
    }
    @Override
    public void onParentPauseAction() {

    }
}
