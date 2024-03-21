package com.sam.permission

import androidx.fragment.app.FragmentActivity

/**
@ClassName PermissionX
@Author zsm
@Date 2024/3/20
@Description
 **/
object PermissionX {
    private const val TAG = "InvisibleFragment"
    fun request(activity: FragmentActivity,
                vararg permissions: String,
                callback: PermissionCallBack) {
        val fragmentManager = activity.supportFragmentManager
        val existsFragment = fragmentManager.findFragmentByTag(TAG)
        val fragment = if (existsFragment!=null){
            existsFragment as InvisibleFragment
        }else{
            val invisibleFragment = InvisibleFragment()
            fragmentManager.beginTransaction().add(invisibleFragment, TAG).commitNow()
            invisibleFragment
        }
        fragment.requestNow(callback,*permissions)
    }
}
