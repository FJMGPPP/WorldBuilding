package com.fjmg.worldbuilding.data.base;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class BaseDialogFragment extends DialogFragment
{
    public static final String KEY_BUNDLE = "result";
    public static final String REQUEST = "requesDialog";
    public static final String TITLE = "title";
    public static final String MESSAGE = "message";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if (getArguments() != null) {
            String title = getArguments().getString(TITLE);
            String message = getArguments().getString(MESSAGE);
            final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle(title);
            builder.setMessage(message);
            final EditText input = new EditText(getContext());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            input.setLayoutParams(lp);
            builder.setView(input);
            builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Bundle result = new Bundle();
                    result.putBoolean(KEY_BUNDLE, true);
                    result.putString("nombre",input.getText().toString());
                    getActivity().getSupportFragmentManager().setFragmentResult(REQUEST, result);
                }
            });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dismiss();

                }
            });
            AlertDialog build = builder.create();
            build.show();
            return build;
        }
        return null;
    }
}
