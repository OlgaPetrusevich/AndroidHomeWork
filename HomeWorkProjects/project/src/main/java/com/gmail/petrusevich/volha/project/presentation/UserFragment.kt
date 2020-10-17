package com.gmail.petrusevich.volha.project.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gmail.petrusevich.volha.project.R
import com.gmail.petrusevich.volha.project.SaveDataSettings
import com.gmail.petrusevich.volha.project.data.HistorySetsDatabaseModel
import kotlinx.android.synthetic.main.fragment_profile_tab.*

private const val BACK_MUSCLE = "Спина"
private const val CHEST_MUSCLE = "Грудь"
private const val LEGS_MUSCLE = "Ноги"
private const val SHOULDER_MUSCLE = "Плечи"
private const val ARM_MUSCLE = "Руки"

@SuppressLint("SetJavaScriptEnabled")
class UserFragment : Fragment(), View.OnClickListener {

    private val historyExercisesViewModel by lazy { ViewModelProvider(this).get(HistoryExercisesViewModel::class.java) }

    private val saveDataSettings by lazy { SaveDataSettings.getInstance(activity?.applicationContext!!)}
    private var countBackMuscle: Int = 0
    private var countChestMuscle: Int = 0
    private var countLegsMuscle: Int = 0
    private var countShoulderMuscle: Int = 0
    private var countArmMuscle: Int = 0


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_profile_tab, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewLifecycleOwner) {
            historyExercisesViewModel.setsLiveData.observe(this, Observer { item ->
                setSumMuscle(item)
            })
            historyExercisesViewModel.historyErrorLiveData.observe(this, Observer { throwable ->
                Log.d("Error", throwable.message!!)
            })
        }
        historyExercisesViewModel.getSumSets()
        viewProgressButton.setOnClickListener(this)
        saveDataSettings.loadWeight(viewWeightText)
        saveDataSettings.loadHeight(viewHeightText)
        showIndex()
        viewWeightText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(text: Editable?) {
                saveDataSettings.saveWeight(viewWeightText)
                showIndex()
            }

            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        viewHeightText.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(text: Editable?) {
                saveDataSettings.saveHeight(viewHeightText)
                showIndex()
            }

            override fun beforeTextChanged(text: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    private fun setSumMuscle(itemList: List<HistorySetsDatabaseModel>) {
        for (i in itemList.indices) {
            when (itemList[i].muscleName) {
                BACK_MUSCLE -> countBackMuscle = itemList[i].setId
                CHEST_MUSCLE -> countChestMuscle = itemList[i].setId
                LEGS_MUSCLE -> countLegsMuscle = itemList[i].setId
                SHOULDER_MUSCLE -> countShoulderMuscle = itemList[i].setId
                ARM_MUSCLE -> countArmMuscle = itemList[i].setId
            }
        }
    }

    private fun getIndexWeight(){
        val height = (viewHeightText.text.toString().toDouble())/100
        val weight = viewWeightText.text.toString().toInt()
        val index = weight / (height*height)
        val indexText = String.format("%.1f", index)
        viewIndexWeightText.text = indexText
    }

    private fun showIndex(){
        if (viewWeightText.text.toString().isNotEmpty() && viewHeightText.text.toString().isNotEmpty()){
            getIndexWeight()
        }
    }

    companion object {
        const val TAG = "UserFragment"
        fun getInstance() = UserFragment()
    }

    override fun onClick(view: View?) {
        when (view) {
            viewProgressButton -> {
                webView.visibility = View.VISIBLE
                webView.addJavascriptInterface(WebAppImpl(countBackMuscle, countChestMuscle, countLegsMuscle, countShoulderMuscle, countArmMuscle), "Android")
                webView.settings.javaScriptEnabled = true
                webView.loadUrl("file:///android_asset/chart.html")
            }
        }
    }
}