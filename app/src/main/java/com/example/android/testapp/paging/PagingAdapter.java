package com.example.android.testapp.paging;

import android.arch.paging.PagedListAdapter;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.testapp.R;
import com.example.android.testapp.data.Person;
import com.example.android.testapp.utils.IntentAbout;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PagingAdapter extends PagedListAdapter<Person, PagingAdapter.MyViewHolder> {

    public PagingAdapter(@NonNull DiffUtil.ItemCallback<Person> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bindTo(getItem(position));
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name_person)
        TextView namePerson;
        @BindView(R.id.tv_gender_person)
        TextView genderPerson;
        @BindView(R.id.image_person)
        ImageView imagePerson;


        MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTo(final Person person) {
            if (person != null) {
                final String name = person.getName().getFirst() + " " + person.getName().getLast();

                /**Adding data to method that start AboutActivity*/
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IntentAbout.startActivity(view.getContext(), person);
                    }
                });

                /**Adding values to views activity_main*/
                genderPerson.setText(person.getGender());
                namePerson.setText(name);
                String url = person.getPicture().getMedium();
                Picasso.get().load(url).into(imagePerson);
            }
        }
    }
}
