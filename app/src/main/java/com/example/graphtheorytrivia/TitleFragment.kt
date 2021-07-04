package com.example.graphtheorytrivia

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
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


        //Navigation Controller is a class that is used to manage navigation within the NavigationHostFragment
        //you will need safe-args dependencies in gradle project and apply them in gradle app to use NavDirections
        //if you don't have NavDirections, just use the good old resource action ids from navigation.xml
        binding.beginButton.setOnClickListener(){ view: View ->
                view.findNavController().navigate(TitleFragmentDirections.actionTitleFragmentToGameFragment())
        }

        return binding.root
    }
}