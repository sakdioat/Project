/* Copyright 2015 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
==============================================================================*/

package com.example.user.myapplication;

import android.graphics.Bitmap;
import android.graphics.RectF;
import java.util.List;
import java.util.Objects;

public interface Classifier {

    class Recognition {
        private final String id;        //"อันดับ"
        private String thaiName;        //thaiName
        private final String title;     //lebel
        private final Float confidence; //confidence

        private RectF location;

        public Recognition( final String id, final String title, final Float confidence, final RectF location){
            this.id = id;                   //checking model
            this.title = title;             //leBel
            this.confidence = confidence;   //confidence
            this.location = location;
        }

        public String getId() { return id; }
        public String getTitle() {
            return title;
        }
        public Float getConfidence() { return confidence; }
        public RectF getLocation() {
            return new RectF(location);
        }
        public void setLocation(RectF location) {
            this.location = location;
        }

        //get data
        public String getThaiName() { return thaiName; }

        @Override
        public String toString() {
            String resultString = "";

            if (id != null) {
                if (Objects.equals(id, "0")){
                    thaiName ="A1";
                }
                else if (Objects.equals(id, "1"))
                    thaiName = "A2";
                else if (Objects.equals(id, "2"))
                    thaiName = "A3";
                else if (Objects.equals(id, "3"))
                    thaiName = "A4";
                else if (Objects.equals(id, "4"))
                    thaiName = "A5";
                else if (Objects.equals(id, "5"))
                    thaiName = "A6";
                else if (Objects.equals(id, "6"))
                    thaiName = "A7";
//                else if (Objects.equals(id, "7"))
//                    thaiName = "A8";
//                else if (Objects.equals(id, "8"))
//                    thaiName = "A9";
//                else if (Objects.equals(id, "9"))
//                    thaiName = "A10";
//                else if (Objects.equals(id, "10"))
//                    thaiName = "A11";
//                else if (Objects.equals(id, "11"))
//                    thaiName = "A12";
//                else if (Objects.equals(id, "12"))
//                    thaiName = "A13";
//                else if (Objects.equals(id, "13"))
//                    thaiName = "A14";
//                else if (Objects.equals(id, "14"))
//                    thaiName = "A15";
//                else if (Objects.equals(id, "15"))
//                    thaiName = "A16";

                resultString += "[" + id + "] ";
                resultString += "ชื่อไทย : " + thaiName + "\n";
            }

//            if (title != null) {
//                resultString +=     "ชื่อวิทยาศาสตร์ : " + title + "\n";
//            }

            if (confidence != null) {
                resultString +=     "ค่าความเชื่อมั่น : " + String.format("(%.1f%%) ", confidence * 100.0f) + "\n";
            }

            if (location != null) {
                //resultString += location + " ";
            }

            return resultString.trim();
        }
    }

    List<Recognition> recognizeImage(Bitmap bitmap);

    void enableStatLogging(final boolean debug);
    String getStatString();
    void close();
}
