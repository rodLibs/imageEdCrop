

# ImageEdCrop
This library allows you to add background to images and leave it with square appearance. 
It has some features with increase the size of the image, rotate, change the background color.
</br>
</br>

# Sample Project
You can download the latest sample APK from this repo here: https://github.com/rodLibs/imageZoom/blob/master/Sample/ImageEdCrop.apk 
</br>
</br>


# Requirements
imageEdCrop requires at minimum Android 4.0 (API level 14).
</br>
</br>


# Gradle Dependency

## Repository
The Gradle dependency is available via Maven. Maven is the default Maven repository used by Android Studio.
</br>

## Add repository
<pre><code>
repositories {
    maven {
          url  "https://dl.bintray.com/rod120/imageEdCrop"
        }
}
</code></pre>



## Add dependency

#### Gradle:
<pre><code>
    compile 'com.github.rodlibs:imageD_crop:1.0'
</code></pre>


#### Maven:
```xml
 <dependency>
  <groupId>com.github.rodlibs</groupId>
  <artifactId>imageD_crop</artifactId>
  <version>1.0</version>
  <type>pom</type>
</dependency>
```
</br>
</br>


# Sample usage
#### .java

###### Add bitmap, background color, release background color change and open activity for image editing.
<pre><code>
  ImageEdCrop.targetBitmap(myBitmap)
                .photoBackgroundColor(Color.parseColor("#000000"))
                .changeBackgroundColor(true)
                .start(MyActivity.this);
</code></pre>


###### Receives the return of the edited image.
<pre><code>
@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Constants.REQUEST_IMAGE && resultCode == RESULT_OK){
              byte[] byteArray = data.getByteArrayExtra(Constants.RETURN_IMAGE_BITMAP);
              Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
        }
   }
</code></pre>


#### Activity - Manifest
```xml
  <activity android:name="com.github.rodlibs.imagedcrop.ImageEdCropActivity"
            android:screenOrientation="portrait"/>
```
</br>






# Api Methods
<pre><code>
  targetBitmap(myBitmap)  -->> Receives a Bitmap.
</code></pre>

<pre><code>
  photoBackgroundColor(myColor)  -->> You receive an int. This method will set the background color of the image.
</code></pre>

<pre><code>
  changeBackgroundColor(true)  -->> You receive a boolean. This method will allow if user can change the background color.
</code></pre>

<pre><code>
  start();  -->> Receive an Activity. This method will send the data to the edit image screen.
</code></pre>
</br>





# License
<pre><code>
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
</code></pre>
