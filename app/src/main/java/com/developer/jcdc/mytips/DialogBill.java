package com.developer.jcdc.mytips;

import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.InputFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

/*
 * Created by jcac_ on 12/06/2016.
 */
public class DialogBill extends DialogFragment {

    EditText et_EnterBill, et_EnterPerson;
    Button btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9, btn0, btnClear, btnDel, btnCancel, btnOk;
    String number;

    static DialogBill newInstance(String txt) {
        DialogBill fragment = new DialogBill();
        Bundle args = new Bundle();
        args.putString("txt", txt);
        fragment.setArguments(args);
        return fragment;
    }

    public DialogBill() {
    }

    public interface dataBill {
        void onFinishEditDialogBill(String text);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialog_values, container, false);

        et_EnterBill = (EditText) view.findViewById(R.id.et_EnterValueBill);
        et_EnterPerson= (EditText) view.findViewById(R.id.et_EnterValuePersons);
        et_EnterPerson.setVisibility(View.GONE);
        et_EnterBill.setVisibility(View.VISIBLE);

        EditText et = new EditText(getActivity());
        int maxLength = 9;
        et.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});

        btn1 = (Button) view.findViewById(R.id.btn1);
        btn2 = (Button) view.findViewById(R.id.btn2);
        btn3 = (Button) view.findViewById(R.id.btn3);
        btn4 = (Button) view.findViewById(R.id.btn4);
        btn5 = (Button) view.findViewById(R.id.btn5);
        btn6 = (Button) view.findViewById(R.id.btn6);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn8 = (Button) view.findViewById(R.id.btn8);
        btn9 = (Button) view.findViewById(R.id.btn9);
        btn0 = (Button) view.findViewById(R.id.btn0);
        btnClear = (Button) view.findViewById(R.id.btnClear);
        btnDel = (Button) view.findViewById(R.id.btnDel);
        btnCancel = (Button) view.findViewById(R.id.btn_Cancel);
        btnOk = (Button) view.findViewById(R.id.btn_Ok);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "1";
                } else {
                    number = number + "1";
                }
                et_EnterBill.setText(number);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "2";
                } else {
                    number = number + "2";
                }
                et_EnterBill.setText(number);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "3";
                } else {
                    number = number + "3";
                }
                et_EnterBill.setText(number);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "4";
                } else {
                    number = number + "4";
                }
                et_EnterBill.setText(number);
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "5";
                } else {
                    number = number + "5";
                }
                et_EnterBill.setText(number);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "6";
                } else {
                    number = number + "6";
                }
                et_EnterBill.setText(number);
            }
        });
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "7";
                } else {
                    number = number + "7";
                }
                et_EnterBill.setText(number);
            }
        });
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "8";
                } else {
                    number = number + "8";
                }
                et_EnterBill.setText(number);
            }
        });
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (number.equalsIgnoreCase("0")) {
                    number = "9";
                } else {
                    number = number + "9";
                }
                et_EnterBill.setText(number);
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (!number.equalsIgnoreCase("0")) {
                    number = number + "0";
                }
                et_EnterBill.setText(number);
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_EnterBill.setText("0");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = et_EnterBill.getText().toString();
                if (!number.equalsIgnoreCase("0")) {
                    number = number.substring(0, number.length() - 1);
                }
                if (number.equals("")) {
                    et_EnterBill.setText("0");
                } else {
                    et_EnterBill.setText(number);
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataBill activity = (dataBill) getActivity();
                activity.onFinishEditDialogBill(et_EnterBill.getText().toString());
                dismiss();
            }
        });
        String txt = getArguments().getString("txt");
        et_EnterBill.setText(txt);

        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }
}
