package com.poly.duantotnghiep_jf.Fragment;

<<<<<<< HEAD
=======
import android.content.Intent;
>>>>>>> origin/dev
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

<<<<<<< HEAD
=======
import com.poly.duantotnghiep_jf.Activity.TaikhoanJob;
>>>>>>> origin/dev
import com.poly.duantotnghiep_jf.Helper.AuthHelper;
import com.poly.duantotnghiep_jf.R;

public class MenuFragment extends Fragment {

    Button btnLogOut;
    public MenuFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        btnLogOut = view.findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthHelper.signOutHelper();
<<<<<<< HEAD

=======
                Intent intent = new Intent(getActivity(), TaikhoanJob.class);
                startActivity(intent);
>>>>>>> origin/dev
            }
        });

        return view;
    }
}
