package com.interview.test.Fragments

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.interview.test.Activities.HomeActivity
import com.interview.test.Preference.TinyDB
import com.interview.test.R
import com.interview.test.Utils.Apputils
import com.interview.test.databinding.FragmentLoginBinding
import com.interview.test.databinding.FragmentSignupBinding
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_signup.*


class LoginFragment : Fragment() {
lateinit var tinyDB: TinyDB
private var binding: FragmentLoginBinding?=null
    private val _binding get() = binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentLoginBinding.inflate(inflater, container, false)
        tinyDB= TinyDB(requireActivity())

        binding!!.loginbtn.setOnClickListener {
          if (Loginvalidated()){
              logintext.visibility=View.GONE
              loginprogress.visibility=View.VISIBLE
              Loading()
              Apputils.hideSoftKeyBoard(requireActivity(),loginlayout)
          }
            else{
                Apputils.DisplayMessage(loginlayout,"please complete your login credetials",R.drawable.ic_baseline_error_24)
                Apputils.hideSoftKeyBoard(requireActivity(),loginlayout)
          }
        }

        return binding!!.root

    }


    private fun Loginvalidated():Boolean{
     if (loginemailedit.text.toString().equals("")){
         loginemailedit.setError("please complete your email address")
         return false
     }
   else if (!loginemailedit.text.toString().equals(tinyDB.getString("email"))){
       loginemailedit.setError("invalid emeil address")
        return false
    }
    else if (loginpasswordedit.text.toString().equals("")){
        loginpasswordedit.setError("please complete your password")
         return false
     }
    else if (!loginpasswordedit.text.toString().equals(tinyDB.getString("password"))){
       loginpasswordedit.setError("invalid password")
        return false
    }
    return true
}
    private fun Loading() {
        Handler().postDelayed({
            Apputils.DisplayMessage(loginlayout,"Login success",R.drawable.ic_baseline_done_24)
            val intent = Intent(requireActivity(), HomeActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
            requireActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right)

        }, 2000)
    }

}