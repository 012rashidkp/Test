package com.interview.test.Fragments

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Bundle
import android.os.Handler
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import com.interview.test.Preference.TinyDB
import com.interview.test.R
import com.interview.test.Utils.Apputils
import com.interview.test.databinding.FragmentSignupBinding
import kotlinx.android.synthetic.main.fragment_signup.*
import java.util.regex.Pattern


class SignupFragment : Fragment() {
    private var binding:FragmentSignupBinding?=null
    private val _binding get() = binding
    lateinit var tinyDB: TinyDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding= FragmentSignupBinding.inflate(inflater, container, false)
        tinyDB= TinyDB(requireActivity())

        binding!!.logintxtbtn.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).addToBackStack(null).replace(R.id.fragment_container, LoginFragment()).commit()
        }



        binding!!.signupbtn.setOnClickListener {
            if (Validated()) {
               Apputils.hideSoftKeyBoard(requireActivity(),layoutview)
                signuptext.visibility = View.GONE
                signupprogress.visibility = View.VISIBLE
                Loading()
                tinyDB.putString("username", usernameedit.text.toString())
                tinyDB.putString("email", emailedit.text.toString())
                tinyDB.putString("password", passwordedit.text.toString())
            }
            else{
                Apputils.hideSoftKeyBoard(requireActivity(),layoutview)
                Apputils.DisplayMessage(layoutview,"please complete your signup details",R.drawable.ic_baseline_error_24)
            }
        }

        return binding!!.root
    }

    private fun Loading() {
        Handler().postDelayed({
            Apputils.DisplayMessage(layoutview,"signup success",R.drawable.ic_baseline_done_24)
            requireActivity().supportFragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).addToBackStack(null).replace(R.id.fragment_container, LoginFragment()).commit()
        }, 2000)
    }

    private fun Validated():Boolean{
    if (usernameedit.text.toString().equals("")){
        usernameedit.setError("please enter your username")
        return false
    }
    else if (emailedit.text.toString().equals("")){
        emailedit.setError("please enter your email address")
        return false
    }
    else if (!isValidEmail(emailedit.text.toString())){
        emailedit.setError("please enter a valid email address")
        return false
    }
    else if (passwordedit.text.toString().equals("")){
        passwordedit.setError("please enter your password")
        return false
    }
    else if (passwordedit.text.toString().length<8){
        passwordedit.setError("please enter atlease 8 digit or character password")
        return false
    }
   return true
}

    private fun isValidEmail(email: String): Boolean {
        val pattern: Pattern = Patterns.EMAIL_ADDRESS
        return pattern.matcher(email).matches()
    }

}