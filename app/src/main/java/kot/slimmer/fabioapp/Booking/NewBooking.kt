package kot.slimmer.fabioapp.Booking

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import khronos.Dates
import khronos.day
import khronos.plus
import kot.slimmer.fabioapp.Cut
import kot.slimmer.fabioapp.R
import kot.slimmer.fabioapp.toDate
import kotlinx.android.synthetic.main.activity_new_booking.*
import java.util.*
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog.OnTimeSetListener as OnTimeListner
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog.OnDateSetListener as OnDateListner
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog as DateDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog as TimeDialog



class NewBooking : AppCompatActivity(), OnTimeListner, OnDateListner{

    var currentDay : Date = Dates.today
    val range : IntRange = 0..7
    var dayList : MutableList<Date> = mutableListOf()

    var y : Int = 0
    var M : Int = 0
    var d : Int = 0
    var h : Int = 0
    var min : Int = 0


    override fun onDateSet(view: DateDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        y = 0
        M = 0
        d = 0

        y = year
        M = monthOfYear
        d = dayOfMonth
    }

    override fun onTimeSet(view: TimeDialog?, hourOfDay: Int, minute: Int, second: Int) {
        h = 0
        min = 0

        h = hourOfDay
        min = minute
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_booking)
        range.forEach { i -> dayList.add(currentDay+i.day) }
        dayList.forEachIndexed { index, date -> Log.d(index.toString(), date.time.toDate()) }

        // recyclerView
        bookingRc.layoutManager = LinearLayoutManager(this)
        bookingRc.adapter = CutAdapter(emptyList(), this)

        // buttons
        datePicker.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = DateDialog.newInstance(
                    this@NewBooking,
                    now.get(Calendar.YEAR),
                    now.get(Calendar.MONTH),
                    now.get(Calendar.DAY_OF_MONTH)
            )
            dpd.vibrate(false)
            dpd.show(fragmentManager, "Datepickerdialog")
        }
        timePicker.setOnClickListener {
            val now = Calendar.getInstance()
            val dpd = TimeDialog.newInstance(
                    this@NewBooking,
                    now.get(Calendar.HOUR_OF_DAY),
                    now.get(Calendar.MINUTE),
                    true
            )
            dpd.vibrate(false)
            dpd.show(fragmentManager, "TimePickerDialog")
        }
        submit.setOnClickListener {
            val date = Dates.of(year = y, month = M+1, day = d, minute = min, hour = h)
            showConfirmDialog(name.text.toString(), date.time)
        }
    }

    private fun showConfirmDialog(name : String , millis : Long) {
        val fm = supportFragmentManager
        val editNameDialogFragment = BookingConfirmDialog.newInstance(name , millis)
        editNameDialogFragment.show(fm, "fragment_edit_name")
    }

    class CutAdapter(cutList: List<Cut>, val ctx: Context) : RecyclerView.Adapter<CutAdapter.CutViewholder>(){

        var list : List<Cut> = cutList

        // viewHolder per i tagli
        inner class CutViewholder(itemView : View) : RecyclerView.ViewHolder(itemView){
            fun bindView(cut : Cut){
                // fai qualosa
            }
        }

        override fun onBindViewHolder(holder: CutViewholder, position: Int) {
            holder?.bindView(list[position])
        }


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CutViewholder {
            val view = LayoutInflater.from(ctx).inflate(R.layout.cut_card_small, parent, false)
            return CutViewholder(view)
        }

        override fun getItemCount(): Int = list.size


        fun updateList(newList : List<Cut>){
            this.list = newList
            notifyDataSetChanged()
        }

    }

}
