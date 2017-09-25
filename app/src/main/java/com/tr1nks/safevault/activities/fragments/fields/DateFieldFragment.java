package com.tr1nks.safevault.activities.fragments.fields;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.method.KeyListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.tr1nks.safevault.R;
import com.tr1nks.safevault.entities.bytes.TextBytes;
import com.tr1nks.safevault.util.Encoder;
import com.tr1nks.safevault.util.UserPasswordManager;

import java.util.Calendar;

public class DateFieldFragment extends Field {
    public DateFieldFragment() {
        // Required empty public constructor
    }

    //Calendar calendar = Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_field, container, false);
        super.addFieldDeleteButtonHandler(view.findViewById(R.id.deleteFieldImageButton));
        ((TextView) view.findViewById(R.id.fragmentTitleForDateText)).setText(new String(Encoder.decode(UserPasswordManager.getPassword(), ((TextBytes) bytes).getTitle())));
        final EditText dateEditText = view.findViewById(R.id.fragmentDateText);
//        int inpType = 129;
//        int inpTypeId = getArguments().getInt("type");
//        switch (inpTypeId) {
//            case R.id.passwordFieldMenuItem: {
//                inpType = 129 | InputType.TYPE_NUMBER_VARIATION_PASSWORD;
//                break;
//            }
//            default: {
//                break;
//            }
//        }
        view.findViewById(R.id.fragmentBrowseDateImageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        dateEditText.setText(new StringBuilder().append(dayOfMonth).append("/").append(monthOfYear+1).append("/").append(year));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
//        editText.setInputType(inpType);
        return view;
    }

    @Override
    public void onParentPauseAction() {

    }

    @Override
    public void setEditable(boolean b) {
        EditText editText = getView().findViewById(R.id.fragmentDateText);
        if (b) {
            editText.setTag(editText.getKeyListener());
            editText.setKeyListener(null);
        } else {
            editText.setKeyListener((KeyListener) editText.getTag());
        }
    }
}
