package com.kotlin.jaesungchi.jetpack_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation

class ThirdFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_third_screen,container,false)
        view.findViewById<Button>(R.id.to_first_from_third).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_global_first_screen)
        }
        view.findViewById<Button>(R.id.to_second_from_third).setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_global_second_screen)
        }
        return view
    }
}