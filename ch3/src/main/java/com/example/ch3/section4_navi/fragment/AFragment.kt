package com.example.ch3.section4_navi.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.ch3.R
import com.example.ch3.databinding.FragmentABinding

class AFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentABinding.inflate(inflater, container, false)

        binding.btnGoB.setOnClickListener {
            val controller = Navigation.findNavController(it)
            //case 1 이동 대상의 destination id 등록으로...
            //controller.navigate(R.id.BFragment, savedInstanceState)

            //case2 nav_graph의 action id로
            //controller.navigate(R.id.action_AFragment_to_BFragment)

            val bundle = Bundle()
            bundle.putString("aArg", "hello")
            bundle.putInt("bArg", 20)
            controller.navigate(R.id.action_AFragment_to_BFragment, bundle)

        }

        return binding.root
    }
}