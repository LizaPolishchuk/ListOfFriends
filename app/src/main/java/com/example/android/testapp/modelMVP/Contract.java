package com.example.android.testapp.modelMVP;

import com.example.android.testapp.datamodels.Person;

import java.util.List;

public class Contract {

    public interface Model {
        void getPersonList(OnFinishedListener onFinishedListener);

        interface OnFinishedListener {
            void onFinish(List<Person> personList);

            void onFailure(Throwable throwable);
        }
    }

    public interface View {
        void setDataToRecyclerView(List<Person> personList);

        void onResponseFailure(Throwable t);
    }

    public interface Presenter {
        void onDestroy();

        void getDataFromServer();
    }
}
