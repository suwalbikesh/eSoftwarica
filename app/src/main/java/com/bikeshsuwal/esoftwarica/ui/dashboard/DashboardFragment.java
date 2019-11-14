package com.bikeshsuwal.esoftwarica.ui.dashboard;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bikeshsuwal.esoftwarica.DashboardActivity;
import com.bikeshsuwal.esoftwarica.R;
import com.bikeshsuwal.esoftwarica.model.StudentsData;

public class DashboardFragment extends Fragment {
    private EditText etName, etAge, etAddress;
    private RadioButton rdoMale, rdoFemale, rdoOthers;
    private Button btnSave;
    private String gender;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        etName = root.findViewById(R.id.etStudentName);
        etAge = root.findViewById(R.id.etStudentAge);
        etAddress = root.findViewById(R.id.etStudentAddress);
        rdoMale = root.findViewById(R.id.rdoMale);
        rdoFemale = root.findViewById(R.id.rdoFemale);
        rdoOthers = root.findViewById(R.id.rdoOthers);
        btnSave = root.findViewById(R.id.btnSave);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                int age = Integer.parseInt(etAge.getText().toString());
                String address = etAddress.getText().toString();

                if (TextUtils.isEmpty(etName.getText().toString())){
                    etName.setError("Please enter Student Full name");
                    etName.requestFocus();
                }
                else if (TextUtils.isEmpty(etAge.getText().toString())){
                    etAge.setError("Please enter Student Age");
                    etAge.requestFocus();
                }
                else if (TextUtils.isEmpty(etAddress.getText().toString())){
                    etAddress.setError("Please enter Student Address");
                    etAddress.requestFocus();
                }

                else {
                    if (rdoMale.isChecked()){
                        gender = "Male";
                    }
                    else if (rdoFemale.isChecked()){
                        gender = "Female";
                    }
                    else if (rdoOthers.isChecked()){
                        gender = "Others";
                    }
                    else {
                        Toast.makeText(getActivity(), "Please Select Student Gender", Toast.LENGTH_SHORT).show();
                    }

                    DashboardActivity.dataList.add(new StudentsData(name,age,gender,address));
                    Toast.makeText(getActivity(), "New Student added", Toast.LENGTH_SHORT).show();

                    etName.getText().clear();
                    etAddress.getText().clear();
                    etAge.getText().clear();
                    rdoFemale.setChecked(false);
                    rdoMale.setChecked(false);
                    rdoOthers.setChecked(false);
                    etName.requestFocus();
                }
            }
        });
        return root;
    }
}