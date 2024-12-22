package ru.alphadrow.gb.myweather.view
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.alphadrow.gb.myweather.R
import ru.alphadrow.gb.myweather.vie.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, MainFragment.newInstance()).commit()

        }
    }
}

