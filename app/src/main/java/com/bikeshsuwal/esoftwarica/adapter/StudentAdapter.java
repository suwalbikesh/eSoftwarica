package com.bikeshsuwal.esoftwarica.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bikeshsuwal.esoftwarica.R;
import com.bikeshsuwal.esoftwarica.model.StudentsData;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentsViewHolder>{
    private Context mContext;
    private List<StudentsData> studentsDataList;

    public StudentAdapter(Context mContext, List<StudentsData> studentsDataList) {
        this.mContext = mContext;
        this.studentsDataList = studentsDataList;
    }

    @NonNull
    @Override
    public StudentsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_students,parent,false);
        return new StudentsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsViewHolder holder, final int position) {
        final StudentsData studentsData = studentsDataList.get(position);
        holder.txtName.setText(studentsData.getName());
        holder.txtAge.setText(Integer.toString(studentsData.getAge()));
        holder.txtAddress.setText(studentsData.getAddress());
        holder.txtGender.setText(studentsData.getGender());
        if (studentsData.getGender().equals("Male")){
            holder.circledataimg.setImageResource(R.drawable.male1);
        }
        else if (studentsData.getGender().equals("Female")){
            holder.circledataimg.setImageResource(R.drawable.female);
        }
        else {
            holder.circledataimg.setImageResource(R.drawable.noimage);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentsDataList.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentsDataList.size();
    }

    public class StudentsViewHolder extends RecyclerView.ViewHolder {
        TextView txtName, txtAge, txtAddress, txtGender;
        CircleImageView circledataimg;
        ImageButton btnDelete;

        public StudentsViewHolder(@NonNull View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtGender = itemView.findViewById(R.id.txtGender);
            circledataimg = itemView.findViewById(R.id.circledataimg);
            btnDelete = itemView.findViewById(R.id.btndelete);

        }
    }
}
