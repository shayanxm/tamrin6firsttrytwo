package com.example.shayanmoradi.tamrin6firsttry.DetailView;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.example.shayanmoradi.tamrin6firsttry.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class AreYouSureToDeleteFragment extends DialogFragment {


    Context mContext;

    public AreYouSureToDeleteFragment() {
        mContext = getActivity();
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_are_you_sure_to_delete, null);
        return new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle("are you sure to delete this task")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        sendResult(true);

                    }
                })
                .setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult(false);
                    }
                })
                .create();
    }

//    }

    private void sendResult(boolean date) {
        Intent intent = new Intent();
        intent.putExtra("test", date);
        getTargetFragment().
                onActivityResult(getTargetRequestCode(), Activity.RESULT_OK, intent);

        getTargetFragment().onResume();
    }
}


