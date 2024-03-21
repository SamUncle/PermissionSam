package com.sam.permission

import android.content.pm.PackageManager
import androidx.fragment.app.Fragment

/**
@ClassName InvisibleFragment
@Author zsm
@Date 2024/3/20
@Description
 **/
typealias PermissionCallBack = (Boolean, List<String>) -> Unit
class InvisibleFragment : Fragment() {
    private var callback: (PermissionCallBack)? = null

    fun requestNow(cb: PermissionCallBack, vararg permissions: String) {
        callback = cb
        requestPermissions(permissions, 1)
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == 1) {
            val deniedList = ArrayList<String>()
            for ((index, result) in grantResults.withIndex()) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    deniedList.add(permissions[index])
                }
            }
            val allGranted = deniedList.isEmpty()
            callback?.let { it(allGranted, deniedList) }
        }
    }
}
