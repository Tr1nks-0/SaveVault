package com.tr1nks.safevault.activities.fragments.fields;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import com.tr1nks.safevault.R;

import java.util.Calendar;

public class DateFieldFragment extends Field {
    public DateFieldFragment() {
        // Required empty public constructor
    }

    //Calendar calendar = Calendar.getInstance();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_field, container, false);
//        view.findViewById(R.id.deleteFieldImageButton).setOnClickListener(super.fieldDeleteButtonHandler(view));
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
                        dateEditText.setText(new StringBuilder().append(dayOfMonth+1).append("/").append(monthOfYear).append("/").append(year));
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.show();
            }
        });
        ((TextView) view.findViewById(R.id.fragmentTitleForDateText)).setText(getArguments().getString("title"));
//        editText.setInputType(inpType);
        return view;
    }
}
