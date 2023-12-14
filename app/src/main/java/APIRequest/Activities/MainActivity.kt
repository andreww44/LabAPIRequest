package APIRequest.Activities

import APIRequest.APIManager.APICallback
import APIRequest.APIManager.ApiTask
import APIRequest.Identities.Item
import APIRequest.Identities.ItemAdapter
import android.content.ClipData
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.labapirequest.R
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException


class MainActivity : AppCompatActivity(), APICallback {

    private lateinit var button_1 : Button
    //private lateinit var adapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listDataFromJson : ListView
    private lateinit var getRequestButton : Button
    private lateinit var adapter : ArrayAdapter<String>
    private lateinit var listData : MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button_1 = findViewById(R.id.button)

        val apiUrl = "https://huachitos.cl/api/animales"

        listDataFromJson = findViewById(R.id.listview)
        getRequestButton = findViewById(R.id.button)


        listData = mutableListOf(

        )

        adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            listData
        )

        listDataFromJson.adapter = adapter

        button_1.setOnClickListener(){
            try {
                val apiRequestTask = ApiTask(this)
                apiRequestTask.execute(apiUrl)

            } catch (e: IOException) {
                Toast.makeText(this, "ERRO", Toast.LENGTH_SHORT).show()

            }
        }



    }
    override fun onSuccess(data: String) {
        listData = processingData(data)
        Log.i("APIRestActivity",listData.toString())
        adapter.clear()
        adapter.addAll(listData)
        adapter.notifyDataSetChanged()

    }

    fun processingData(result: String) : MutableList<String> {
        var list : MutableList<String> = mutableListOf()
        try {
            // Parse the JSON string into a JSONObject
            val jsonObject = JSONObject(result)

            val dataObject = jsonObject.getJSONArray("data")


            // Access the "data" array
            val dataArray = jsonObject.getJSONArray("data")

            // Iterate through the array and access individual objects
            for (i in 0 until dataArray.length()) {
                val itemObject = dataObject.getJSONObject(i)
                val name = itemObject.getString("nombre")
                list.add(name)
            }



        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list.toMutableList()
    }




}