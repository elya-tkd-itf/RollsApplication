package com.dasonick.rollsapplication.ui.rolls

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.dasonick.rollsapplication.R
import com.dasonick.rollsapplication.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class RollsFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val viewModel: RollsViewModel by activityViewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.rollsRecycler.apply {
            layoutManager = GridLayoutManager(context, 2)
        }

        viewModel.getData().observe(viewLifecycleOwner) {
            if (it != null) {
                Log.wtf("hui", it.size.toString())
                binding.rollsRecycler.adapter =
                    RollsRecyclerAdapter(it, findNavController())
            }
        }

        binding.goToHell.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}