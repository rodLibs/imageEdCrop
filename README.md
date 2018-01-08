
<img src="camcolor.gif" height="500" width="380">

# ImageEdCrop
This library allows you to add background to images and leave it with square appearance. 
It has some features with increase the size of the image, rotate, change the background color.
</br>
</br>

# Sample Project
You can download the latest sample APK from this repo here: http://link_apk
</br>
</br>


# Requirements
imageEdCrop requires at minimum Android 4.0 (API level 14).
</br>
</br>


# Gradle Dependency

## Repository
The Gradle dependency is available via maven. maven is the default Maven repository used by Android Studio.
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
                .start(MainActivity.this);
</code></pre>


###### Receives the return of the edited image.
<pre><code>
  if (requestCode == Constants.REQUEST_IMAGE && resultCode == RESULT_OK){
            byte[] byteArray = data.getByteArrayExtra(Constants.RETURN_IMAGE_BITMAP);
            Bitmap bitmap = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length));
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
  createCamera();  -->> create camera and set in frame layout.
</code></pre>

<pre><code>
  destroyCamera();  -->> destroy and release camera.
</code></pre>

<pre><code>
  setCameraBack();  -->> switch to rear camera.
</code></pre>

<pre><code>
  setCameraFront();  -->> switch to front camera.
</code></pre>

<pre><code>
  pause();   -->>  Pause the camera, freezing the image and the color values.
</code></pre>

<pre><code>
  resume(); -->>  resumes the camera, releasing the image and color values.
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
