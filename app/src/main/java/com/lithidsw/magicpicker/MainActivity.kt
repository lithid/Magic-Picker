package com.lithidsw.magicpicker

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var adapter: MyAdapter? = null
    private var toast: Toast? = null
    private val positionController = PositionsController((1..21).toList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pickLeft.setOnClickListener {view -> onButtonClick(view)}
        pickMiddle.setOnClickListener {view -> onButtonClick(view)}
        pickRight.setOnClickListener {view -> onButtonClick(view)}

        val layoutManager = GridLayoutManager(this, 3)
        recyclerView.layoutManager = layoutManager

        adapter = MyAdapter(positionController.shufflePositions())
        recyclerView.adapter = adapter
    }
    
    fun onButtonClick(view: View) {
        when(view.id) {
            R.id.pickLeft -> handleLeftButtonClick()
            R.id.pickMiddle -> handleMiddleButtonClick()
            R.id.pickRight -> handleRightButtonClick()
        }
    }

    fun handleLeftButtonClick() {
        adapter?.dataSet = positionController.valueInLeftPosition()
        handleClickCount()
    }

    fun handleMiddleButtonClick() {
        adapter?.dataSet = positionController.valueInMiddlePosition()
        handleClickCount()
    }

    fun handleRightButtonClick() {
        adapter?.dataSet = positionController.valueInRightPosition()
        handleClickCount()
    }

    fun handleClickCount() {
        positionController.getNumberGuess()?.let {
            makeToast("You choose number " + it, Toast.LENGTH_LONG)
            adapter?.dataSet = positionController.shufflePositions()
        }

        adapter?.notifyDataSetChanged()
    }

    fun makeToast(message: String, length: Int) {
        toast?.cancel()
        toast = Toast.makeText(this, message, length)
        toast?.show()
    }

    inner class MyAdapter(data: List<Int>) : RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        var dataSet = data

        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            var textView: AppCompatTextView? = view.findViewById(R.id.numberTextView)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.textView?.text = dataSet[position].toString()
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(this@MainActivity)
                    .inflate(R.layout.grid_item, parent, false))
        }

        override fun getItemCount(): Int = dataSet.size
    }
}
