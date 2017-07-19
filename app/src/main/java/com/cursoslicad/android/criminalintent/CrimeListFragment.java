package com.cursoslicad.android.criminalintent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by javier on 7/18/17.
 */

public class CrimeListFragment extends Fragment {
    public static final String TAG = "CrimeListFragment";
    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_crime_list, container, false);
        mCrimeRecyclerView = (RecyclerView) view.
                findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        updateUI();
        return view;
    }

    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Crime mCrime;
        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mSolvedCheckBox;


        public CrimeHolder(View itemView){
            super(itemView);
            mTitleTextView = (TextView)
                    itemView.findViewById(R.id.list_item_crime_title_text_view);
            mDateTextView = (TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);
            mSolvedCheckBox = (CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);
        }

        public void bindCrime(Crime crime){
            mCrime = crime;
            mTitleTextView.setText(mCrime.getTitle());
            mDateTextView.setText(mCrime.getDate().toString());
            mSolvedCheckBox.setChecked(mCrime.isSolved());
        }

        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(),
                    mCrime.getTitle() + "click!" , Toast.LENGTH_SHORT).show();
        }


    }

    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;
        public CrimeAdapter(List<Crime> crimes){
            mCrimes = crimes;
        }

        /*
       * Llamado por el RecyclerView cuando necesita una nueva vista para desplegar un elemento
       * (como RecyclerView no trabaja con las vistas directamente, necesita "envolver"
       * la vista en un ViewHolder)*/
        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int ViewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater
                    .inflate(R.layout.list_item_crime, parent, false);
            Log.d(TAG, "llamada a onCreateViewHolder()");
            return new CrimeHolder(view);
        }


        /*
        * Une la vista con el modelo mCrimes (de nuevo, la vista se trabaja indirectamente a partir de
        * un ViewHolder)
        * */
        @Override
        public void onBindViewHolder(CrimeHolder holder, int position){
            Crime crime = mCrimes.get(position);
            holder.bindCrime(crime);
            Log.d(TAG, "llamada a onBindViewHolder()");
        }

        /*
         * El adapter necesita saber con cuantos elementos está trabajando para hacer el reciclado
         * de las vistas.
         * */
        @Override
        public int getItemCount(){
            Log.d(TAG, "llamada a getItemCount()");
            return mCrimes.size();
        }
    }

    private void updateUI(){
        CrimeLab crimelab = CrimeLab.get(getActivity());
        List<Crime> crimes = crimelab.getCrimes();

        mAdapter = new CrimeAdapter(crimes);
        mCrimeRecyclerView.setAdapter(mAdapter);
    }

}
