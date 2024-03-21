package com.sam.permissionsam

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.sam.permission.PermissionX
import com.sam.permissionsam.databinding.ActivityMainBinding
import java.lang.String

class MainActivity : AppCompatActivity() {
    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater) //
        setContentView(binding!!.root)
        binding!!.callPhone.setOnClickListener{
            val permission = "android.permission.CALL_PHONE"
            PermissionX.request(this,permission){
                    allGranted,deniedList ->
                if (allGranted){
                    call()
                }else{
                    val tip = String.join(" ", deniedList)
                    binding!!.showPermission.text = "请申请$tip 权限"
                    Toast.makeText(this,"请先申请$tip 权限",Toast.LENGTH_LONG).show()
                }
            }
        }

    }
    private fun call(){
        try {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:10086")
            startActivity(intent)
        }catch (e:SecurityException){
            e.printStackTrace()
        }
    }
}
