package kot.slimmer.fabioapp

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import junit.framework.Test
import kotlinx.android.synthetic.main.activity_pictures.*
import kotlinx.android.synthetic.main.picture_card.view.*
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import kot.slimmer.fabioapp.Booking.NewBooking


class PicturesActivity : AppCompatActivity() {

    var list = arrayListOf("https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F14583402_1287961394581488_7504352140165382144_n.jpg?alt=media&token=247783f8-ef4f-42d1-a926-7e792471ad5f",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F17076926_1455287141202173_5363108985157189632_n.jpg?alt=media&token=2dbf325f-c0cc-45ec-8359-383da097c2c5",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F18011803_1884360045171828_4314249405646503936_n.jpg?alt=media&token=e3a14e05-102f-437c-b73f-107fcfb305e2",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F18722128_310876862699946_1407526873855950848_n.jpg?alt=media&token=760afb3e-cfeb-48d1-8cff-b6a6fb0a596a",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F18949988_1941857355839802_5906038412384665600_n.jpg?alt=media&token=0fcba679-63f5-4696-b9eb-71cf7673e4dc",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F19436259_142057626350036_2549262604291801088_n.jpg?alt=media&token=8dfa1f9d-fa2c-4b39-a1ef-db0739ea4981",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F19955631_481731355512848_7550866356808187904_n.jpg?alt=media&token=bb0bbe84-0207-4330-9f86-ff4a504b5bb6",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F25023318_1925928150992003_7485036903436124160_n.jpg?alt=media&token=97096335-f39f-4374-aa17-9ae08ecbcd59",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F25025811_1924081504507766_9041834734304362496_n%20(1).jpg?alt=media&token=af9da72a-9443-49be-b98d-8c2958ea4585",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F26319712_186339618768513_4195467102913560576_n.jpg?alt=media&token=e66f0050-cb0e-4d1c-9083-0f65c9dce759",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F27881255_1603358499699916_3431496462242938880_n.jpg?alt=media&token=3cb5da39-6ff9-4683-bd49-7efd8b01debf",
            "https://firebasestorage.googleapis.com/v0/b/fabio-2e829.appspot.com/o/Pics%2F28433180_2042383442649529_3798367754008920064_n.jpg?alt=media&token=cc2609b3-706d-4015-85a3-c490fd87ef65")

    var isHorizontal = false

    val query = FirebaseFirestore.getInstance()
            .collection("Pics")
            .orderBy("time")

    val options = FirestoreRecyclerOptions.Builder<CutPicture>()
            .setQuery(query, CutPicture::class.java)
            .build()

    val adapter = object : FirestoreRecyclerAdapter<CutPicture, PicViewHolder>(options) {
        override fun onBindViewHolder(holder: PicViewHolder, position: Int, model: CutPicture) {
            holder.bindView(model)
        }

        override fun onCreateViewHolder(group: ViewGroup, i: Int): PicViewHolder {
            // Create a new instance of the ViewHolder, in this case we are using a custom
            // layout called R.layout.message for each item
            val view = LayoutInflater.from(group.context)
                    .inflate(R.layout.picture_card, group, false)

            return PicViewHolder(view)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pictures)
        picRecycler.setHasFixedSize(true)
        //populateRecyclerView()
        picRecycler.layoutManager = LinearLayoutManager(this)
        picRecycler.hasFixedSize()
        picRecycler.adapter = adapter
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()

    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()

    }

    private fun buildDatabaseFromStorage(){
        list.forEachIndexed { index, s ->
            val newCut = CutPicture(s, System.currentTimeMillis()+100L, "Test foto $index", "#hastag1#hashtag2")

            FirebaseFirestore.getInstance().collection("Pics").add(newCut)
        }
    }


    private fun populateRecyclerView(){
        FirebaseFirestore.getInstance().collection("Pics").get().addOnCompleteListener {
            task ->
            var picList = task.result.toObjects(CutPicture::class.java)
            var adapter = picRecycler.adapter as PictureAdapter
            adapter.updateList(picList)
        }
    }

    inner class PicViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(data : CutPicture){
            Glide.with(this@PicturesActivity).load(data.path)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(itemView.cut_card_pic)

            itemView.hashtags.text = data.hashTags
            itemView.time.text = data.time?.toSmallDate()
            itemView.title.text = data.title
            itemView.options.setOnClickListener{
                if(!isHorizontal) {
                    // verticale
                    isHorizontal = true
                    picRecycler.layoutManager = LinearLayoutManager(this@PicturesActivity, LinearLayoutManager.HORIZONTAL, false)
                    picRecycler.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
                    itemView.cut_card_pic.layoutParams.height = 450f.toDPI(this@PicturesActivity)
                }else{
                    // orizzontale
                    isHorizontal = false
                    picRecycler.layoutManager = LinearLayoutManager(this@PicturesActivity, LinearLayoutManager.VERTICAL, false)
                    picRecycler.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                    itemView.cut_card_pic.layoutParams.height = 300f.toDPI(this@PicturesActivity)

                }
            }
        }
    }

    class PictureAdapter(picList : List<CutPicture>, val ctx : Context) : RecyclerView.Adapter<PictureAdapter.PicViewHolder>(){

        var list : List<CutPicture> = picList

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PicViewHolder {
            val view = LayoutInflater.from(ctx).inflate(R.layout.picture_card, parent, false)
            return PicViewHolder(view)        }

        override fun getItemCount(): Int = list.size

        override fun onBindViewHolder(holder: PicViewHolder, position: Int) {
            holder?.bindView(list[position])
        }

        fun updateList(newList : List<CutPicture>){
            this.list = newList
            notifyDataSetChanged()
        }

        inner class PicViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
            fun bindView(data : CutPicture){
                Glide.with(ctx).load(data.path)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(itemView.cut_card_pic)

                itemView.title.text = data.title
            }
        }
    }
}
