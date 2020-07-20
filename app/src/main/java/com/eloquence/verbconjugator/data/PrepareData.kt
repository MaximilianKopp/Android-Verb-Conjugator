package com.eloquence.verbconjugator.data

import android.app.Application
import android.content.Context
import android.os.Environment
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.eloquence.verbconjugator.model.Root
import com.eloquence.verbconjugator.model.VerbViewModel
import com.google.gson.Gson
import java.io.*
import java.util.zip.GZIPInputStream

class PrepareData {

    fun loadData(context: Context, application: Application) {
        val verbViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(application)
            .create(VerbViewModel::class.java)
        var root = Root()
        val gson = Gson()
        val verbFile = context.getFileStreamPath("verbenliste.json")
        val database =
            File(
                Environment
                    .getDataDirectory()
                    .path + "/data/com.eloquence.verbconjugator/databases/verb_database"
            )

        if (!verbFile.exists() && !database.exists() || verbViewModel.isEmpty() == 0) {
            decompressData(context)
        }

        if (!(database.exists()) || verbViewModel.isEmpty() == 0) {

            if (verbViewModel.isEmpty() == 0) {
                Toast.makeText(application, "Migration hat geklappt", Toast.LENGTH_SHORT).show()
            }

            try {
                val ims = BufferedInputStream(context.openFileInput("verbenliste.json"))
                val reader = BufferedReader(InputStreamReader(ims))
                root = gson.fromJson(reader, Root::class.java)
                ims.close()
                reader.close()
            } catch (e: IOException) {
                e.stackTrace
            }
            root.verben?.let { verbViewModel.bulkInsert(it) }
            context.deleteFile(verbFile.name)

        }
    }

    private fun decompressData(context: Context) {
        val buffer = ByteArray(1024)
        try {
            val assetManager = context.assets

            val gzipInputStream = GZIPInputStream(assetManager.open("kompletteverben.bin"))
            val fileOutputStream = FileOutputStream(File(context.filesDir, "verbenliste.json"))

            while (true) {
                val len = gzipInputStream.read(buffer)
                if (len < 0) break
                fileOutputStream.write(buffer, 0, len)
            }

            fileOutputStream.close()
            gzipInputStream.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}