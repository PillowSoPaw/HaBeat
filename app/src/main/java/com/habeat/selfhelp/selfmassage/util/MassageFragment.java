package com.habeat.selfhelp.selfmassage.util;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.habeat.MainActivity;
import com.habeat.R;
import com.habeat.selfhelp.selfmassage.Massage;


public class MassageFragment extends Fragment {
    private static final String ARG_TITLE = "fragment title";
    private static final String ARG_DESC = "massage description";
    private static final String ARG_VID_PATH = "massage video path";

    private String title;
    private String description;
    private String videoPath;
    private VideoView videoView;
    private MediaController mediaController;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param title Title of the massage.
     * @param massage Massage to be displayed.
     * @return A new instance of fragment MassageFragment.
     */
    public static MassageFragment newInstance(String title, Massage massage) {
        MassageFragment fragment = new MassageFragment();
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESC, massage.getDescription());
        args.putString(ARG_VID_PATH, "android.resource://"
                + MainActivity.PACKAGE_NAME + "/" + massage.getVideoId());
        fragment.setArguments(args);
        return fragment;
    }

    public MassageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESC);
            videoPath = getArguments().getString(ARG_VID_PATH);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_massage, container, false);

        TextView descriptionTextView = (TextView) v.findViewById(R.id.selfmassage_step_description);
        descriptionTextView.setText(description);

        // TODO - refactor this to make it cleaner
        videoView = (VideoView)v.findViewById(R.id.selfmassage_tutorial_video);
        videoView.setVideoURI(Uri.parse(videoPath));

        mediaController = new MediaController(getActivity());
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        // TODO - does not show preview
        // videoView.start();
        // videoView.seekTo(3000);
        // videoView.pause();

        return v;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (this.isVisible()) {
            if (!isVisibleToUser) {
                videoView.pause();
                mediaController.hide();
            }
        }
    }
}
