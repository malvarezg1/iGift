package com.example.igift


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.igift.data.Datasource
import com.example.igift.databinding.FragmentProfileBinding
import com.example.igift.model.User


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private var _user: User? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val user get() = _user!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Retrieve data and inflate the layout for this fragment
        _user = Datasource().loadUser()
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val nameTextView = binding.profileName
        val cityTextView = binding.profileCity
        val profileImageView = binding.profileImage

        nameTextView.text = resources.getString(user.nameResourceId)
        cityTextView.text = resources.getString(user.cityResourceId)
        profileImageView.setImageResource(user.imageResouceId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}