package com.mertadali.kotlinsqlite

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mertadali.kotlinsqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        // Create table, insert data, save data


            try{
                           //(CREATE DATABASE)
                val myDataBase = this.openOrCreateDatabase("Musicians", Context.MODE_PRIVATE,null)
                //if we want to run some code on the database

                          // (EXECUTE SQL TABLE)
                myDataBase.execSQL("CREATE TABLE IF NOT EXISTS musicians(id INTEGER PRIMARY KEY,name VARCHAR, age INT)")  /*Although we accidentally entered the name sql
                 as a meaningless expression, we use it in the try and catch method.*/

                         // (ADD VARIABLES)
               // myDataBase.execSQL("INSERT INTO musicians (name,age) VALUES ('Mert',23)")
                // myDataBase.execSQL("INSERT INTO musicians (name,age) VALUES ('Miray',23)")
                //myDataBase.execSQL("INSERT INTO musicians (name,age) VALUES ('Murat',68)")


                        //(CHANGE ANY SAVED DATA)
                //myDataBase.execSQL("UPDATE musicians SET age=24 WHERE name= 'Mert'")

                        //(DELETE  ANY DATA)
                myDataBase.execSQL("DELETE FROM musicians WHERE name ='Murat'")



                        // (SELECT AND FILTER PROCESS)
                val cursor = myDataBase.rawQuery("SELECT * FROM musicians ",null)
                //val cursor = myDataBase.rawQuery("SELECT * FROM musicians WHERE name ='Mert'",null)  // selectionArgs means filter
                //val cursor = myDataBase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%t'",null)  //bring the ones with the last letter 't'
                //val cursor = myDataBase.rawQuery("SELECT * FROM musicians WHERE name LIKE 'M%'",null)  //bring the ones with with the firs letter 'M'



                val nameIx = cursor.getColumnIndex("name")
                val ageIx = cursor.getColumnIndex("age")
                val idIx = cursor.getColumnIndex("id")

                while (cursor.moveToNext()){
                    println("Name: " +cursor.getString(nameIx))
                    println("Age: " +cursor.getInt(ageIx))         //will print after checking each cell
                    println("Id:" +cursor.getInt(idIx))
                }
                cursor.close()    //When we finish our last job, we give the finish command.




            }catch (e:Exception){
                e.printStackTrace()
            }
    }
}