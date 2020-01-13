package xyz.walk8243.httpserverapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SpringApplication.run(MainActivity::class.java, "")
    }
}
