package com.example.igift.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.example.igift.R
import com.example.igift.model.DataModel
import java.util.*
class PreferencesAdapter(private val dataSet: ArrayList<DataModel>, mContext: Context) : ArrayAdapter<DataModel>(mContext, R.layout.row_item, dataSet) {
    /*
    override fun getView(position: Int,convertView: View?,parent: ViewGroup): View{
        val inflater : LayoutInflater = LayoutInflater.from(context)
        val view : View = inflater.inflate(R.layout.row_item,null)

        val textName : TextView = view.findViewById(R.id.txtName)
        val checkBox : CheckBox = view.findViewById(R.id.checkBox)

        textName.text = dataSet[position].name
        checkBox.isChecked = dataSet[position].checked

        return view
    }
    */

    private class ViewHolder {
        lateinit var txtName: TextView
        lateinit var checkBox: CheckBox
    }
    override fun getCount(): Int {
        return dataSet.size
    }
    override fun getItem(position: Int): DataModel {
        return dataSet[position] as DataModel
    }
    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup
    ): View {
        var convertView = convertView
        val viewHolder: ViewHolder
        val result: View
        if (convertView == null) {
            viewHolder = ViewHolder()
            convertView =
                LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
            viewHolder.txtName =
                convertView.findViewById(R.id.txtName)
            viewHolder.checkBox =
                convertView.findViewById(R.id.checkBox)
            result = convertView
            convertView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as ViewHolder
            result = convertView
        }
        val item: DataModel = getItem(position)
        viewHolder.txtName.text = item.name
        viewHolder.checkBox.isChecked = item.checked
        return result
    }

}