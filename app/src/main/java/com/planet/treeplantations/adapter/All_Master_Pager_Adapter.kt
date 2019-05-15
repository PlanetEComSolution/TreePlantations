package com.planet.treeplantations.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.planet.treeplantations.fragments.*

class All_Master_Pager_Adapter internal constructor(fm: FragmentManager) : FragmentPagerAdapter(fm) {

    private val COUNT = 6

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = Tree_Spicies_Fragment()
            1 -> fragment = PO_MO_Fragment()
            2 -> fragment = Land_Type_Fragment()
            3 -> fragment = Project_Code_Fragment()
            4 -> fragment = Budget_Fragment()
            5 -> fragment = Year_Maintence_Fragment()
        }

        return fragment
    }

    override fun getCount(): Int {
        return COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        var title: String? = null
        if (position == 0) {
            title = "TREE SPECIES"
        } else if (position == 1) {
            title = "PO/MPU NUMBER"
        }else if (position == 2) {
            title = "LAND TYPE NAME"
        }else if (position == 3) {
            title = "PROJECT CODE"
        }else if (position == 4) {
            title = "BUDGET HEAD"
        }else if (position == 5) {
            title = "YEAR(S) OF MAINTENANCE"
        }

        return title
    }

}
