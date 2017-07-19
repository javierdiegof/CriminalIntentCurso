package com.cursoslicad.android.criminalintent;

import android.support.v4.app.Fragment;

/**
 * Created by javier on 7/18/17.
 */
public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment(){
        return new CrimeListFragment();
    }
}
