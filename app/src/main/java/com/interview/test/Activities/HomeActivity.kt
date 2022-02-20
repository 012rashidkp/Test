package com.interview.test.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.interview.test.Adapter.UsersListAdapter
import com.interview.test.Network.Apiclient
import com.interview.test.Network.Apihelper
import com.interview.test.Network.Status
import com.interview.test.R
import com.interview.test.Repository.UsersRepository
import com.interview.test.Utils.Apputils
import com.interview.test.ViewModel.UsersViewModel
import com.interview.test.ViewModel.UsersViewModelFactory
import com.interview.test.databinding.ActivityHomeBinding
import com.jobs.taskbook.Repository.PagingRepository
import com.jobs.taskbook.ViewModels.PagingViewModel
import com.jobs.taskbook.ViewModels.PagingViewmodelFactory
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeActivity : AppCompatActivity() {
    private var binding: ActivityHomeBinding?=null
    private val viewbinding get() = binding!!
    lateinit var viewmodel:PagingViewModel
    lateinit var _viewmodel:UsersViewModel

    val page:Int=1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        setuppagingViewModel()
        setupList()
        setdataviewmodel()
        setobservers()

    }



    private fun setdataviewmodel() {
        _viewmodel= ViewModelProvider(
                this,
                UsersViewModelFactory(Apihelper(Apiclient.invoke()))
        )[UsersViewModel::class.java]
    }


    private fun setuppagingViewModel() {
        viewmodel= ViewModelProvider(
                this,
                PagingViewmodelFactory(PagingRepository(Apiclient.invoke()))
        )[PagingViewModel::class.java]
    }

    private fun setupList() {
val madapter=UsersListAdapter(this)
        rvusers.layoutManager = LinearLayoutManager(this)
        rvusers.setHasFixedSize(true)
        rvusers.adapter = madapter
        lifecycleScope.launch {

            viewmodel.passengers.collectLatest { pagedData ->
                System.out.println("items "+pagedData)
                madapter.submitData(pagedData)
            }
        }
    }
    private fun setobservers() {
        _viewmodel.getitems(1).observe(this, Observer {
            it?.let { response ->
                when(response.status){
                    Status.SUCCESS->{
                      Loading()
                        response.data?.let { datas ->
                           mytext.text=datas.support.text
                            myurl.text=datas.support.url



                        }
                    }
                    Status.LOADING->{

                    }
                    Status.ERROR->{

                    }
                }
            }
        })
    }
    private fun Loading(){
        Handler().postDelayed({
            rvusers.visibility=View.VISIBLE
            supportlayout.visibility=View.VISIBLE
            rvusers.layoutManager=LinearLayoutManager(this)
            rvusers.layoutManager
            usersprogressbar.visibility=View.GONE
            val decoration=DividerItemDecoration(this,DividerItemDecoration.VERTICAL)
            rvusers.addItemDecoration(decoration)
        },2100)
    }
}