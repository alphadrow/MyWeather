package ru.alphadrow.gb.myweather.view.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import ru.alphadrow.gb.myweather.R
import ru.alphadrow.gb.myweather.databinding.FragmentMainBinding
import ru.alphadrow.gb.myweather.domain.Weather
import ru.alphadrow.gb.myweather.view.OnItemViewClickListener
import ru.alphadrow.gb.myweather.viewmodel.AppState
import ru.alphadrow.gb.myweather.viewmodel.MainViewModel

class MainFragment : Fragment(), OnItemViewClickListener {
    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() {
            return _binding!!
        }

    private var isDataSetRus: Boolean = true
    private var adapter = MainFragmentAdapter()
    private lateinit var viewModel: MainViewModel


    companion object { //памятка: это нужно для объявления статичных вещей в kotlin
        fun newInstance(): Fragment {
            return MainFragment()
        }

        const val REGION_SET_KEY = "REGION_SET_KEY"
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (savedInstanceState != null) {
            isDataSetRus = savedInstanceState.getBoolean(REGION_SET_KEY)
        }
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.mainFragmentRecyclerView.adapter = adapter
        adapter.setOnItemClickListener(this)
        setFABImage()
        binding.mainFragmentFAB.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                isDataSetRus = !isDataSetRus
                viewModel.getDataFromLocalSource(isDataSetRus)
                setFABImage()
            }

        })
        //ViewModelProvider позволяет "переживать" смерти фрагментов
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getLiveData().observe(viewLifecycleOwner, Observer<AppState> {
            renderData(it)
        })
        viewModel.getDataFromLocalSource(isDataSetRus)
    }

    private fun setFABImage() {
        if (isDataSetRus)
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        else binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
    }

    fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Error -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val throwable = appState.error
                Snackbar.make(binding.root, "ERROR $throwable", Snackbar.LENGTH_LONG).show()

            }

            AppState.Loading -> {
                binding.mainFragmentLoadingLayout.visibility = View.VISIBLE
            }

            is AppState.Success -> {
                binding.mainFragmentLoadingLayout.visibility = View.GONE
                val weather = appState.weatherData
                adapter.setWeather(weather)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(REGION_SET_KEY, isDataSetRus)
    }

    override fun onItemClick(weather: Weather) {
        val bundle = Bundle()
        bundle.putParcelable(DetailsFragment.BUNDLE_WEATHER_KEY, weather)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, DetailsFragment.newInstance(bundle))
            .addToBackStack("").commit()
    }
}