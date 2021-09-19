package com.interview.test.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.interview.test.Adapter.UsersListAdapter
import com.interview.test.Models.Users
import com.interview.test.Network.Apiclient
import com.interview.test.Network.Apihelper
import com.interview.test.Network.Status
import com.interview.test.R
import com.interview.test.Utils.Apputils
import com.interview.test.ViewModel.UsersViewModel
import com.interview.test.ViewModel.UsersViewModelFactory
import com.interview.test.databinding.ActivityHomeBinding
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    private var binding: ActivityHomeBinding?=null
    private val viewbinding get() = binding!!
    lateinit var viewmodel:UsersViewModel
    lateinit var items:List<Users>
    val page:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        SetupViewModel()
        SetupObservers()


    }




    private fun SetupViewModel() {
        viewmodel = ViewModelProviders.of(
                this,
                UsersViewModelFactory(Apihelper(Apiclient.invoke()))
        ).get(UsersViewModel::class.java)
    }
    private fun SetupObservers() {
        viewmodel.getitems().observe(this, Observer {
            it?.let { response ->
                when(response.status){
                    Status.SUCCESS ->{

                        response.data?.let { datas ->
                            binding!!.supportlayout.visibility=View.VISIBLE
                            binding!!.mytext.text=datas.support.text
                            binding!!.myurl.text=datas.support.url
                            items=it.data!!.data
                            Setuplistitems(items)

                        }
                    }
                    Status.LOADING->{

                    }
                    Status.ERROR->{
                    Apputils.DisplayMessage(homelayout,"not available",R.drawable.ic_baseline_error_24)
                    usersprogressbar.visibility=View.GONE
                    }
                }
            }
        })
    }

    private fun Setuplistitems(items: List<Users>) {
      Handler().postDelayed({
          binding!!.usersprogressbar.visibility = View.GONE
          rvusers.visibility = View.VISIBLE
          binding!!.rvusers.layoutManager = LinearLayoutManager(this)
          val madapter=UsersListAdapter(this, items)
         // view.productlist.adapter=madapter
          rvusers.adapter=madapter
          madapter?.notifyDataSetChanged()
          val decoration= DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
          rvusers.addItemDecoration(decoration)
      },2300)
    }

}