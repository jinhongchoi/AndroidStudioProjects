package com.jh.hongdroid9_camera

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission//gradle에 implementation 'io.github.ParkSangGwon:tedpermission:2.3.0' 이걸사용할 경우 앞에와 같이 import
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_IMAGE_CAPTURE=1 //카메라 사진 촬영 요청 코드
    lateinit var curPhotoPath: String // 문자열 형태의 사진 경로 값 -> 초기값 형태를 그냥 선언으로 한 것!(초기값 null 로 선언!)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_camera : Button = findViewById(R.id.btn_camera)

        setPermission() // 최초의 권한을 체크하는 메소드 수행! -> 밑에 있음

        btn_camera.setOnClickListener(){
            takeCapture()// 기본 카메라 앱을 실행하여 사진활영하는 메소드
        }
    }

    private fun takeCapture() { //카메라 앱을 실행하여 사진촬영하는 메소드
        //기본 카메라 앱 실행

        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                val photofile: File? = try {
                    createImageFile()
                }catch (ex:IOException){
                    null
                }
                photofile?.also {
                    val photoURI : Uri =FileProvider.getUriForFile(
                        this,
                        "com.jh.hongdroid9_camera.fileprocider",//보안관련 파일들의 서명
                        it
                    )
                    takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)// 찍은 사진의 결과값을 받는다
                }
            }
        }
    } // 20분부터 다시보면서 이해할 것!


    /*
    * 이미지 파일 생성 -> 시간("yyyyMMdd__HH")이 다르게 저장시켜 파일마다 구분할 수 있게 하려는 의도
    *
    * File? -> 물음표에 의미 : 파일을 가져왔을때 null 일 수도 있다는 것을 의미
    * JPEG_${timestamp} 는 자바에서 "JPEG_" + timestamp 랑 같은 의미! 코틀린에서 바뀜!
    * */
    private fun createImageFile(): File? {
        val timestamp: String = SimpleDateFormat("yyyyMMdd__HH").format(Date())
        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_${timestamp}",".jpg",storageDir)
            .apply { curPhotoPath = absolutePath }
    } // 이미지뷰에 세팅하기 위한 작업들


    private fun setPermission() { // 테드 퍼미션 설정
        val permission = object  : PermissionListener {
            override fun onPermissionGranted() { // 설정해놓은 위험 권한들이 허용 되었을 경우 이 곳을 수행
                Toast.makeText(this@MainActivity,"권한이 허용 되었습니다.",Toast.LENGTH_SHORT).show()
            }

            override fun onPermissionDenied(deniedPermissions: MutableList<String>?) { //설정해놓은 위험권한 들 중 거부한 경우 이 곳을 수행함
                Toast.makeText(this@MainActivity,"권한이 거부 되었습니다.",Toast.LENGTH_SHORT).show()
            }

        }//end PermissionListener

        TedPermission.with(this) //implementation 'io.github.ParkSangGwon:tedpermission:2.3.0' 이걸 사용해야 .with(this) 사용 가능! 아니면 에러뜸!
            .setPermissionListener(permission)
            .setRationaleMessage("카메라 앱을 사용하시려면 권한을 허용해주세요.") // 권한을 허용했을 경우
            .setDeniedMessage("권한을 거부하셨습니다.") // 권한을 거부했을 경우
            .setPermissions(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,android.Manifest.permission.CAMERA) // 카메라 사용권한을 부여하는 것!
            .check()

    }//end setPermission

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) { // startActivityForResult 를 통해서 기본 카메라 앱으로 부터 받아온 사진 결과값
        super.onActivityResult(requestCode, resultCode, data)
        val iv_profile : ImageView =findViewById(R.id.iv_profile)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == Activity.RESULT_OK){ //이미지를 성공적으로 가져왔으면
            var bitmap : Bitmap
            val file =File(curPhotoPath)
            if(Build.VERSION.SDK_INT < 28){ //안드로이드 9.8(pie) 버전보다 낮을 경우
                bitmap =MediaStore.Images.Media.getBitmap(contentResolver,Uri.fromFile(file))
                iv_profile.setImageBitmap(bitmap) //촬영했을때 iv_profile 에 들어간다!
            }else{ // 안드로이드 9.8 보다 높을 경우
                val decode =ImageDecoder.createSource(
                    this.contentResolver,
                    Uri.fromFile(file)
                )
                bitmap = ImageDecoder.decodeBitmap(decode)
                iv_profile.setImageBitmap(bitmap)
            }
            savePhoto(bitmap)
        }

        
    }

    /*
    * 갤러리에 저장
    * */
    private fun savePhoto(bitmap: Bitmap) {
        val folderPath = Environment.getExternalStorageDirectory().absolutePath + "/Pictures/" //사진 폴더로 저장히기 위한 경로 선언
        val timestamp: String = SimpleDateFormat("yyyyMMdd__HH").format(Date())
        val fileName = "${timestamp}.jpeg"
        val folder=File(folderPath)
        if (!folder.isDirectory){ // 현재 해당 경로에 폴더가 존재하는지 않는다면
            folder.mkdirs() //make directory 줄임말로 해당경로에 폴더 자동으로 새로 만들어 저장!
        }

        // 실제적인 저장 처리
        val out = FileOutputStream(folderPath + fileName)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out)
        Toast.makeText(this,"사진이 앨범에 저장되었습니다.", Toast.LENGTH_SHORT).show()

        //이후 manifest 에서 provider 작성!

    }//end savePhoto
}


// 카메라 입은 흐름알기! -> developer 사진촬영 부분에 코드 보면서 이해하기!



