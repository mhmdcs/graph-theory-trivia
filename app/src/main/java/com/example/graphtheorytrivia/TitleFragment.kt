package com.example.graphtheorytrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graphtheorytrivia.databinding.FragmentTitleBinding

class TitleFragment : Fragment() {
    //might need to rebuild after creating a binding
    private lateinit var binding: FragmentTitleBinding;
    //In activities, we use DataBindingUtil.setContentView to get the binding class from a layout,
    //but in fragments we need to call DataBindingUtil.inflate in onCreateView  to get the binding class from a layout
    //container: ViewGroup is the ViewGroup object that the layout will be hosted by
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        //false prevents the layout from being attached to the ViewGroup
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title, container,false)
        return binding.root
    }
}