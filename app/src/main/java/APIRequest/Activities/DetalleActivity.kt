package APIRequest.Activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labapirequest.R

class DetalleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_activuty)


        val title = intent.getStringExtra("nombre")
        val region = intent.getStringExtra("region")
        val age = intent.getStringExtra("age")
        val type = intent.getStringExtra("type")
        val state = intent.getStringExtra("state")
        val gender = intent.getStringExtra("gender")
        val description = intent.getStringExtra("description")

        //textViewDetail.text = "$title\n\n$detail"
    }
}