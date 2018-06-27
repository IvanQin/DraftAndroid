package cn.ivanqin.githubhelper.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import cn.ivanqin.githubhelper.R;

public class YesNoDialog extends DialogFragment {
    public static final String ARG_TITLE = "cn.ivanqin.githubhelper.ui.YesNoDialog.Title";
    public static final String ARG_MESSAGE = "cn.ivanqin.githubhelper.ui.YesNoDialog.Message";
    public static final String TAG = "cn.ivanqin.githubhelper.ui.YesNoDialog";

    private Callback callback;

    public static YesNoDialog newInstance(String title, String message, Callback callback) {

        Bundle args = new Bundle();
        YesNoDialog fragment = new YesNoDialog();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        fragment.callback = callback;
        return fragment;
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = getArguments().getString(ARG_TITLE);
        String message = getArguments().getString(ARG_MESSAGE);
        return new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // this is for fragment that calls this dialog, not for activities
//                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_OK,null);
                        // (https://stackoverflow.com/questions/13733304/callback-to-a-fragment-from-a-dialogfragment?utm_medium=organic&utm_source=google_rich_qa&utm_campaign=google_rich_qa)
                        // this is for activities ( not very expandable)
//                        ((ContactActivity)getActivity()).doPostivieClick();
                        callback.positiveCallBack();

                    }
                })
                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//                        getTargetFragment().onActivityResult(getTargetRequestCode(), Activity.RESULT_CANCELED,null);
//                        ((ContactActivity)getActivity()).doNegativeClick();
                        callback.negativeCallBack();

                    }
                }).create();

    }

    public interface Callback {
        void positiveCallBack();

        void negativeCallBack();
    }
}
