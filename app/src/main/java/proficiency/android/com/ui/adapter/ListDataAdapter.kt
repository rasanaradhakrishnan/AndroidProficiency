package proficiency.android.com.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import proficiency.android.com.di.executor.entitymodel.ListDataRowModel
import proficiency.android.com.ui.R

class ListDataAdapter(var dataModelList: List<ListDataRowModel>?) : BaseAdapter() {

    override fun getView(position: Int, p1: View?, p2: ViewGroup?): View {
        val holder: ViewHolder
        var view:View?= p1
        if (view == null) {
            view = LayoutInflater.from(p2!!.context)
                    .inflate(R.layout.list_row_layout, p2, false)

        }
        holder = ViewHolder(view!!)
        // set the view's size, margins, paddings and layout parameters
        holder.mName.text= dataModelList!!.get(position).title
        holder.mDescription.text = dataModelList!!.get(position).description
        Picasso.get().load(dataModelList!!.get(position).imageHref)
                .placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(holder.rowImage)
        return view
    }

    override fun getItem(p0: Int): ListDataRowModel {
        return dataModelList!!.get(p0)
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return dataModelList!!.size
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    class ViewHolder(v: View){

        internal val mName: TextView
        internal val mDescription: TextView
        internal val rowImage: ImageView

        init {
            mName = v.findViewById(R.id.rowHeading)
            mDescription = v.findViewById(R.id.rowDescription)
            rowImage = v.findViewById(R.id.rowImage)
        }
    }


}
