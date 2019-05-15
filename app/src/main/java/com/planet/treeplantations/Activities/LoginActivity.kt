package com.planet.treeplantations.Activities

import android.app.AlertDialog
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import com.planet.treeplantations.R
import com.planet.treeplantations.models.Login_Model
import com.planet.treeplantations.rest.API_Interface
import retrofit2.Call
import retrofit2.Response
import com.planet.treeplantations.rest.API_Client
import retrofit2.Callback
import android.widget.Toast
import android.widget.EditText
import com.planet.treeplantations.models.Login_Responce
import android.graphics.Movie
import android.util.Log
import com.planet.treeplantations.Sherd_pref.Pref
import com.planet.treeplantations.utils.ProgressLoading
import com.planet.treeplantations.utils.Util.isNetworkAvailable


import dmax.dialog.SpotsDialog


/**
 * A login screen that offers login via Name/password.
 */
class LoginActivity : AppCompatActivity(){
    private var pref_user: Pref? = null
    private var myContext: Context? = null
    private var password_str: String? = ""
    private var loginName: String? = ""
    private var email: String? = ""
    private var mobile: String? = ""
    private var userRole: String? = ""
    private var stateName: String?  = ""
   //  private var stateName = String ?: ""
    private var id: String? = ""
    private var name: String? = ""
    private var str_usre: String? = ""
    private var str_pass: String? = ""
    private var user_id: EditText? = null
    private var password: EditText? = null
    private var loading: ProgressLoading?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

         pref_user = Pref(applicationContext)
         loading = ProgressLoading(applicationContext);
         user_id = findViewById<EditText>(R.id.user_name) as EditText
         password= findViewById<EditText>(R.id.password) as EditText
         val button: Button = findViewById<Button>(R.id.email_sign_in_button) as Button
         button.setOnClickListener { view ->
             if(validateAllFields()){
             if(isNetworkAvailable(applicationContext)){
                 call_LoginAPI();
              }else {
                 Toast.makeText(applicationContext, "Please check your internet connectivity.", Toast.LENGTH_SHORT).show()
             }
             } }
    }
    private fun call_LoginAPI() {
        val apiService = API_Client.getClient().create(API_Interface::class.java)

        val call = apiService.login(str_usre,str_pass)

        val dialog: AlertDialog = SpotsDialog.Builder().setContext(this@LoginActivity).build()
        dialog.show()

        call.enqueue(object : Callback<Login_Model> {
            override fun onResponse(call: Call<Login_Model>, response: Response<Login_Model>) {


                dialog.dismiss()
                val userList = response.body()
                val status = userList.status
                var user_res = response.body().data
                for (i in 0 until user_res.size) {
//                    name = user_res.get(i).name
//                    id = user_res.get(i).id
//                    password_str = user_res.get(i).password
//                    loginName = user_res.get(i).loginName
//                    email = user_res.get(i).email
//                    mobile = user_res.get(i).mobile
//                    userRole = user_res.get(i).userRole
//                    stateName = user_res.get(i).stateName

                    println("size of arrayList1 = "+user_res.get(i).name)
                    pref_user!!.savePreferences("Id", user_res.get(i).id)
                    pref_user!!.savePreferences("name", user_res.get(i).name)
                    pref_user!!.savePreferences("loginName", user_res.get(i).loginName)
                    pref_user!!.savePreferences("email", user_res.get(i).email)
                    pref_user!!.savePreferences("mobile", user_res.get(i).email)
                    pref_user!!.savePreferences("stateName", user_res.get(i).stateName)
                    pref_user!!.savePreferences("userRole", user_res.get(i).userRole)
                    pref_user!!.savePreferences("password", user_res.get(i).password)
                    pref_user!!.savePreferences("pid", user_res.get(i).projectCodeId)
                    pref_user!!.savePreferences("sid", user_res.get(i).stateID)
                    pref_user!!.savePreferences("rid", user_res.get(i).regionId)

                }

                if(status.equals("1")){
                             val intent = Intent(this@LoginActivity, MainActivity::class.java)
                             startActivity(intent)
                              finish()
                   Toast.makeText(applicationContext, "Login successfully" , Toast.LENGTH_SHORT).show()
                }else{
                   Toast.makeText(applicationContext, "Please Enter Valid Credential!" , Toast.LENGTH_SHORT).show()
               }
            }
            override fun onFailure(call: Call<Login_Model>, t: Throwable) {
                dialog.dismiss()
            }
        })
    }

    private fun validateAllFields(): Boolean {
        str_usre = user_id?.text.toString().trim()
         if (str_usre.isNullOrBlank()) {
            Toast.makeText(applicationContext, "Please Enter User Name!" , Toast.LENGTH_SHORT).show()
            return false
        }
         str_pass = password?.text.toString().trim()
        if (str_pass.isNullOrBlank()) {
            Toast.makeText(applicationContext, "Please Enter Password!" , Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
